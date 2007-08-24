###############################################################################
#                                                                             #
# PYenca software, LORIA-INRIA LORRAINE, MADYNES RESEARCH TEAM                #
# Copyright (C) 2005  Humberto ABDELNUR                                       #
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
#   Name : Humberto ABDELNUR                                                  #
#   Email: Humberto.Abdelnur@loria.fr                                         #
#                                                                             #
###############################################################################

import sys,os
import yapps2lc.yapps2

yapps2.generate(os.path.join(os.path.dirname(globals()["__file__"]),"BGP_quagga_Parser.g"))
