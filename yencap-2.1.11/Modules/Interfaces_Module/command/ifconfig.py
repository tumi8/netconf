###############################################################################
#                                                                             #
# YencaP software, LORIA-INRIA LORRAINE, MADYNES RESEARCH TEAM                #
# Copyright (C) 2005  Vincent CRIDLIG                                         #
#                                                                             #
# This program is free software; you can redistribute it and/or               #
# modify it under the terms of the GNU General Public License                 #
# as published by the Free Software Foundation; either version 2              #
# of the License, or (at your option) any later version.                      #
#                                                                             #
# This program is distributed in the hope that it will be useful,             #
# but WITHOUT ANY WARRANTY; without even the implied warranty of              #
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the               #
# GNU General Public License for more details.                                #
#                                                                             #
# You should have received a copy of the GNU General Public License           #
# along with this program; if not, write to the Free Software                 #
# Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA. #
#                                                                             #
# Author Info:                                                                #
#   Name : TUBITAK/UEKAE                                                      #
# Modified by:                                                                #
#   Vincent CRIDLIG                                                           #
#   Email: Vincent.Cridlig@loria.fr                                           #
#                                                                             #
###############################################################################

import array
import fcntl
import struct
import socket
import os


class Ifconfig:
    """ ioctl stuff """

    instance = None             # Singleton

    IFNAMSIZ = 16               # interface name size

    # From <bits/ioctls.h>

    SIOCGIFADDR = 0x8915        # get PA address
    SIOCGIFBRDADDR  = 0x8919    # get broadcast PA address
    SIOCGIFCONF = 0x8912        # get iface list
    SIOCGIFFLAGS = 0x8913       # get flags
    SIOCGIFMTU = 0x8921         # get MTU size
    SIOCGIFNETMASK  = 0x891b    # get network PA mask
    SIOCSIFADDR = 0x8916        # set PA address
    SIOCSIFBRDADDR  = 0x891a    # set broadcast PA address
    SIOCSIFFLAGS = 0x8914       # set flags
    SIOCSIFMTU = 0x8922         # set MTU size
    SIOCSIFNETMASK  = 0x891c    # set network PA mask

    # From <net/if.h>    

    IFF_UP = 0x1           # Interface is up.
    IFF_BROADCAST = 0x2    # Broadcast address valid.
    IFF_DEBUG = 0x4        # Turn on debugging.
    IFF_LOOPBACK = 0x8     # Is a loopback net.
    IFF_POINTOPOINT = 0x10 # Interface is point-to-point link.
    IFF_NOTRAILERS = 0x20  # Avoid use of trailers.
    IFF_RUNNING = 0x40     # Resources allocated.
    IFF_NOARP = 0x80       # No address resolution protocol.
    IFF_PROMISC = 0x100    # Receive all packets.
    IFF_ALLMULTI = 0x200   # Receive all multicast packets.
    IFF_MASTER = 0x400     # Master of a load balancer.
    IFF_SLAVE = 0x800      # Slave of a load balancer.
    IFF_MULTICAST = 0x1000 # Supports multicast.
    IFF_PORTSEL = 0x2000   # Can set media type.
    IFF_AUTOMEDIA = 0x4000 # Auto media select active.


    def __init__(self):
        # create a socket to communicate with system
        self.sockfd = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

    def _ioctl(self, func, args):
        return fcntl.ioctl(self.sockfd.fileno(), func, args)

    def _call(self, ifname, func, ip = None):

        if ip is None:
            data = (ifname + '\0'*32)[:32]
        else:
            ifreq = (ifname + '\0' * self.IFNAMSIZ)[:self.IFNAMSIZ]
            data = struct.pack("16si4s10x", ifreq, socket.AF_INET, socket.inet_aton(ip))

        try:
            result = self._ioctl(func, data)
        except IOError:
            return None

        return result

    def _readsys(self, ifname, f):
        try:
            fp = file(os.path.join("/sys/class/net", ifname, f))
            result = fp.readline().rstrip('\n')
            fp.close()
        except IOError:
            return None
            
        return result

    def getInterfaceList(self):
        """ Get all interface names in a list """
        # get interface list
        buffer = array.array('c', '\0' * 1024)
        ifconf = struct.pack("iP", buffer.buffer_info()[1], buffer.buffer_info()[0])
        result = self._ioctl(self.SIOCGIFCONF, ifconf)

        # loop over interface names
        iflist = []
        size, ptr = struct.unpack("iP", result)
        for idx in range(0, size, 32):
            ifconf = buffer.tostring()[idx:idx+32]
            name, dummy = struct.unpack("16s16s", ifconf)
            name, dummy = name.split('\0', 1)
            iflist.append(name)

        return iflist

    def getAddr(self, ifname):
        """ Get the inet addr for an interface """
        result = self._call(ifname, self.SIOCGIFADDR)
        return socket.inet_ntoa(result[20:24])

    def getNetmask(self, ifname):
        """ Get the netmask for an interface """
        result = self._call(ifname, self.SIOCGIFNETMASK)
        return socket.inet_ntoa(result[20:24])

    def getBroadcast(self, ifname):
        """ Get the broadcast addr for an interface """
        result = self._call(ifname, self.SIOCGIFBRDADDR)
        return socket.inet_ntoa(result[20:24])

    def getFlagUp(self, ifname):
        """ Check whether interface is UP """
        result = self._call(ifname, self.SIOCGIFFLAGS)
        flags, = struct.unpack('H', result[16:18])
        return (flags & self.IFF_UP) != 0

    def getMTU(self, ifname):
        """ Get the MTU size of an interface """
        data = self._call(ifname, self.SIOCGIFMTU)
        mtu = struct.unpack("16si12x", data)[1]
        return mtu

    def getMAC(self, ifname):
        """ Get MAC address of an interface """
        mac = self._readsys(ifname, "address")
        return mac

    def getRX(self, ifname):
        """ Get received bytes of an interface """
        rx = self._readsys(ifname, "statistics/rx_bytes")
        return int(rx)

    def getTX(self, ifname):
        """ Get transferred bytes of an interface """
        tx = self._readsys(ifname, "statistics/tx_bytes")
        return int(tx)

    def getFlagBroadcast(self, ifname):
        """ Check whether BROADCAST flag is set """
        result = self._call(ifname, self.SIOCGIFFLAGS)
        flags, = struct.unpack('H', result[16:18])
        return (flags & self.IFF_BROADCAST) != 0

    def getFlagMulticast(self, ifname):
        """ Check whether MULTICAST flag is set """
        result = self._call(ifname, self.SIOCGIFFLAGS)
        flags, = struct.unpack('H', result[16:18])
        return (flags & self.IFF_MULTICAST) != 0

    def getFlagRunning(self, ifname):
        """ Check whether RUNNING flag is set """
        result = self._call(ifname, self.SIOCGIFFLAGS)
        flags, = struct.unpack('H', result[16:18])
        return (flags & self.IFF_RUNNING) != 0

    def getFlagLoopback(self, ifname):
        """ Check whether LOOPBACK flag is set """
        result = self._call(ifname, self.SIOCGIFFLAGS)
        flags, = struct.unpack('H', result[16:18])
        return (flags & self.IFF_LOOPBACK) != 0

    def setAddr(self, ifname, ip):
        """ Set the inet addr for an interface """
        result = self._call(ifname, self.SIOCSIFADDR, ip)

        if socket.inet_ntoa(result[20:24]) is ip:
            return True
        else:
            return None

    def setNetmask(self, ifname, ip):
        """ Set the netmask for an interface """
        result = self._call(ifname, self.SIOCSIFNETMASK, ip)

        if socket.inet_ntoa(result[20:24]) is ip:
            return True
        else:
            return None

    def setBroadcast(self, ifname, ip):
        """ Set the broadcast addr for an interface """
        result = self._call(ifname, self.SIOCSIFBRDADDR, ip)

        if socket.inet_ntoa(result[20:24]) is ip:
            return True
        else:
            return None

    def setStatus(self, ifname, status):
        """ Set interface status (UP/DOWN) """
        ifreq = (ifname + '\0' * self.IFNAMSIZ)[:self.IFNAMSIZ]

        if status is "UP":
            # Set IFF_UP
            flags = self.IFF_UP
            # Set IFF_RUNNING by default
            flags |= self.IFF_RUNNING
            # Set IFF_BROADCAST by default
            flags |= self.IFF_BROADCAST
            # Set IFF_MULTICAST by default
            flags |= self.IFF_MULTICAST
            # Unset IFF_NOARP by default
            flags &= ~self.IFF_NOARP
            # Unset IFF_RUNNING by default
            flags &= ~self.IFF_PROMISC
        elif status is "DOWN":
            flags = ~self.IFF_UP
        else:
            return None

        data = struct.pack("16sh", ifreq, flags)
        result = self._ioctl(self.SIOCSIFFLAGS, data)
        return result

    def setMTU(self, ifname, mtu):
        """ Set the MTU size of an interface """
        ifreq = (ifname + '\0' * self.IFNAMSIZ)[:self.IFNAMSIZ]

        data = struct.pack("16si", ifreq, mtu)
        result = self._ioctl(self.SIOCSIFMTU, data)

        if struct.unpack("16si", result)[1] is mtu:
            return True
        else:
            return None


def getInstance():
	"""
		ifconfig is a Singleton
		Use getInstance method to get the unique ifconfig
		@rtype: ifconfig
		@return: ifconfig singleton instance
	"""

	if Ifconfig.instance == None:
		Ifconfig.instance = Ifconfig()
	return Ifconfig.instance

