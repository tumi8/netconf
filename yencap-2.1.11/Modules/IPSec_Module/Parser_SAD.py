from SA import SA

from string import *
import re
from yappsrt import *

class Parser_SADScanner(Scanner):
    patterns = [
        ('"spi="', re.compile('spi=')),
        ('"mode="', re.compile('mode=')),
        ('[\\ \\t]+', re.compile('[\\ \\t]+')),
        ('EOF', re.compile('$')),
        ('EOL', re.compile('\\n')),
        ('IPV4', re.compile('((25[0-5]|2[0-4][0-9]|[0-1]?[0-9]?[0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[0-1]?[0-9]?[0-9]?)')),
        ('IPV6', re.compile('([0-9a-fA-F]{0,4}:){0,7}[0-9a-fA-F]{0,4}')),
        ('PROTOCOL', re.compile('(esp|ah|esp-old|ah-old|ipcomp)')),
        ('MODE', re.compile('(transport|tunnel|any)')),
        ('SPI', re.compile('(0x[0-9a-fA-F]+)|([0-9]+)')),
        ('RESTE_SPI', re.compile('([0-9a-zA-Z\\=\\)\\(\\ ]+)|(\\([0-9a-zA-Z\\=\\)\\(\\ ]+)')),
        ('ALGORITHM', re.compile('(\\bE:.*|\\bA:.*|\\C:.*)')),
        ('RESTE_SA_SP', re.compile('([0-9a-zA-Z\\=\\)\\(\\:\\_\\ \\t\\n]+)(?=refcnt)["refcnt"=0-9\\n]+\\n')),
    ]
    def __init__(self, str):
        Scanner.__init__(self,None,['[\\ \\t]+'],str)

class Parser_SAD(Parser):
    def goal(self):
        sad_tab=[]
        SAD = self.SAD(sad_tab)
        EOF = self._scan('EOF')
        return sad_tab

    def SAD(self, sad_tab):
        while self._peek('EOF', 'IPV4', 'IPV6') != 'EOF':
            SA = self.SA()
            sad_tab.append(SA)

    def SA(self):
        ligne1 = self.ligne1()
        ligne2 = self.ligne2()
        lignes3 = self.lignes3()
        reste_SA_SP = self.reste_SA_SP()
        #print [ligne1[0],ligne1[1],ligne2[0],ligne2[1],ligne2[2],lignes3]
        return SA(ligne1[0],ligne1[1],ligne2[0],ligne2[1],ligne2[2],lignes3)

    def ligne1(self):
        src = self.src()
        dst = self.dst()
        EOL = self._scan('EOL')
        return [src,dst]

    def src(self):
        adresse = self.adresse()
        return adresse

    def dst(self):
        adresse = self.adresse()
        return adresse

    def adresse(self):
        _token_ = self._peek('IPV4', 'IPV6')
        if _token_ == 'IPV4':
            IPV4 = self._scan('IPV4')
            return IPV4
        else:# == 'IPV6'
            IPV6 = self._scan('IPV6')
            return IPV6

    def ligne2(self):
        protocol = self.protocol()
        mode = self.mode()
        spi = self.spi()
        resteSPI = self.resteSPI()
        EOL = self._scan('EOL')
        return [protocol, mode,spi]

    def protocol(self):
        PROTOCOL = self._scan('PROTOCOL')
        return PROTOCOL

    def mode(self):
        self._scan('"mode="')
        MODE = self._scan('MODE')
        return "-m "+MODE

    def spi(self):
        self._scan('"spi="')
        SPI = self._scan('SPI')
        return SPI

    def resteSPI(self):
        RESTE_SPI = self._scan('RESTE_SPI')
        return RESTE_SPI

    def lignes3(self):
        les_algorithms=""
        while self._peek('ALGORITHM', 'RESTE_SA_SP') == 'ALGORITHM':
            algorithm = self.algorithm()
            EOL = self._scan('EOL')
            les_algorithms+=" "+algorithm
        return les_algorithms

    def algorithm(self):
        ALGORITHM = self._scan('ALGORITHM')
        return ALGORITHM

    def reste_SA_SP(self):
        RESTE_SA_SP = self._scan('RESTE_SA_SP')
        return RESTE_SA_SP


def parse(rule, text):
    P = Parser_SAD(Parser_SADScanner(text))
    return wrap_error_reporter(P, rule)

if __name__ == '__main__':
    from sys import argv, stdin
    if len(argv) >= 2:
        if len(argv) >= 3:
            f = open(argv[2],'r')
        else:
            f = stdin
        print parse(argv[1], f.read())
    else: print 'Args:  <rule> [<filename>]'
