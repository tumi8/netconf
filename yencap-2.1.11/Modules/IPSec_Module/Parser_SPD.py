from SP import SP

from string import *
import re
from yappsrt import *

class Parser_SPDScanner(Scanner):
    patterns = [
        ('[\\ \\t]', re.compile('[\\ \\t]')),
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
        ('PREFIXLEN_PORT', re.compile('(\\/[0-9]+)*((\\[[0-9]+\\])|(\\[any\\]))*')),
        ('UPPER_SPEC', re.compile('[0-9a-zA-Z\\-]+')),
        ('DIRECTION', re.compile('(out|in|fwd)')),
        ('PRIORITY', re.compile('(priority|prio)*')),
        ('BASE', re.compile('(low|def|high)*')),
        ('SIGNE_OFFSET', re.compile('(\\+|\\-)*')),
        ('OFFSET', re.compile('[0-9]*')),
        ('RULE_POLICY', re.compile('(discard|none|ipsec)')),
        ('SPLETTERS', re.compile('[\\/\\-]+')),
        ('LEVEL', re.compile('(default|use|require|unique)')),
    ]
    def __init__(self, str):
        Scanner.__init__(self,None,['[\\ \\t]'],str)

class Parser_SPD(Parser):
    def goal(self):
        spd_tab=[]
        SPD = self.SPD(spd_tab)
        EOF = self._scan('EOF')
        return spd_tab

    def SPD(self, spd_tab):
        while self._peek('EOF', 'IPV4', 'IPV6') != 'EOF':
            SP = self.SP()
            spd_tab.append(SP)

    def SP(self):
        ligne1 = self.ligne1()
        ligne2 = self.ligne2()
        lignes3 = self.lignes3()
        reste_SA_SP = self.reste_SA_SP()
        # print [ligne1[0],ligne1[1],ligne1[2],ligne2,lignes3]
        return SP(ligne1[0],ligne1[1],ligne1[2],ligne2+" "+lignes3)

    def ligne1(self):
        src_range = self.src_range()
        dst_range = self.dst_range()
        while self._peek('EOL', 'UPPER_SPEC') == 'UPPER_SPEC':
            upperspec = self.upperspec()
        EOL = self._scan('EOL')
        return [src_range, dst_range,upperspec]

    def src_range(self):
        adresse = self.adresse()
        while self._peek('PREFIXLEN_PORT', 'IPV4', 'IPV6', 'EOL', 'UPPER_SPEC') == 'PREFIXLEN_PORT':
            prefixlen_port = self.prefixlen_port()
        return adresse+prefixlen_port

    def dst_range(self):
        adresse = self.adresse()
        while self._peek('PREFIXLEN_PORT', 'EOL', 'UPPER_SPEC', 'IPV4', 'IPV6') == 'PREFIXLEN_PORT':
            prefixlen_port = self.prefixlen_port()
        return adresse+prefixlen_port

    def adresse(self):
        _token_ = self._peek('IPV4', 'IPV6')
        if _token_ == 'IPV4':
            IPV4 = self._scan('IPV4')
            return IPV4
        else:# == 'IPV6'
            IPV6 = self._scan('IPV6')
            return IPV6

    def prefixlen_port(self):
        PREFIXLEN_PORT = self._scan('PREFIXLEN_PORT')
        return PREFIXLEN_PORT

    def upperspec(self):
        UPPER_SPEC = self._scan('UPPER_SPEC')
        return UPPER_SPEC

    def ligne2(self):
        direction = self.direction()
        priority = self.priority()
        base = self.base()
        signe_offset = self.signe_offset()
        offset = self.offset()
        rule_policy = self.rule_policy()
        EOL = self._scan('EOL')
        return "-P "+direction+" "+priority+" "+base+" "+signe_offset+offset+" "+rule_policy

    def direction(self):
        DIRECTION = self._scan('DIRECTION')
        return DIRECTION

    def priority(self):
        PRIORITY = self._scan('PRIORITY')
        return PRIORITY

    def base(self):
        BASE = self._scan('BASE')
        return BASE

    def signe_offset(self):
        SIGNE_OFFSET = self._scan('SIGNE_OFFSET')
        return SIGNE_OFFSET

    def offset(self):
        OFFSET = self._scan('OFFSET')
        return OFFSET

    def rule_policy(self):
        RULE_POLICY = self._scan('RULE_POLICY')
        return RULE_POLICY

    def lignes3(self):
        les_lignes3=""
        # factorisation par (protocol sp_char1 mode sp_char2)
        while self._peek('PROTOCOL', 'RESTE_SA_SP') == 'PROTOCOL':
            protocol = self.protocol()
            sp_char1 = self.sp_char1()
            mode = self.mode()
            sp_char2 = self.sp_char2()
            _token_ = self._peek('LEVEL', 'IPV4', 'IPV6')
            if _token_ == 'LEVEL':
                level = self.level()
                une_ligne3=protocol+sp_char1+mode+sp_char2+level
            else:# in ['IPV4', 'IPV6']
                adresse_src = self.adresse_src()
                sp_char3 = self.sp_char3()
                adresse_dst = self.adresse_dst()
                sp_char4 = self.sp_char4()
                level = self.level()
                une_ligne3=protocol+sp_char1+mode+sp_char2+adresse_src+sp_char3+adresse_dst+sp_char4+level
            EOL = self._scan('EOL')
            les_lignes3+=" "+une_ligne3
        #print les_lignes3
        return les_lignes3

    def adresse_src(self):
        adresse = self.adresse()
        return adresse

    def adresse_dst(self):
        adresse = self.adresse()
        return adresse

    def protocol(self):
        PROTOCOL = self._scan('PROTOCOL')
        return PROTOCOL

    def mode(self):
        MODE = self._scan('MODE')
        return MODE

    def level(self):
        LEVEL = self._scan('LEVEL')
        return LEVEL

    def sp_char1(self):
        SPLETTERS = self._scan('SPLETTERS')
        return SPLETTERS

    def sp_char2(self):
        SPLETTERS = self._scan('SPLETTERS')
        return SPLETTERS

    def sp_char3(self):
        SPLETTERS = self._scan('SPLETTERS')
        return SPLETTERS

    def sp_char4(self):
        SPLETTERS = self._scan('SPLETTERS')
        return SPLETTERS

    def sp_char5(self):
        SPLETTERS = self._scan('SPLETTERS')
        return SPLETTERS

    def reste_SA_SP(self):
        RESTE_SA_SP = self._scan('RESTE_SA_SP')
        return RESTE_SA_SP


def parse(rule, text):
    P = Parser_SPD(Parser_SPDScanner(text))
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
