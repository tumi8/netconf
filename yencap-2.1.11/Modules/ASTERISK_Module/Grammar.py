#!/usr/bin/python

from string import *
import re
from yapps2lc.yappsrt import *

class GrammarScanner(Scanner):
	patterns = [
		("'\\]'", re.compile('\\]')),
		("r'\\['", re.compile('\\[')),
		("'>[ \\t]?'", re.compile('>[ \t]?')),
		("'='", re.compile('=')),
		("'[ \\t]*'", re.compile('[ \t]*')),
		("''", re.compile('')),
		("'[+]'", re.compile('[+]')),
		("'[ \\t]?'", re.compile('[ \t]?')),
		('END', re.compile('$')),
		('ENDOFLINE', re.compile('[\r]?\n')),
		('SECTION', re.compile('[a-zA-Z0-9][-a-zA-Z0-9_:.]*')),
		('ATTRIBUTE', re.compile('[a-zA-Z0-9][-a-zA-Z0-9_.]*')),
		('VALUE', re.compile('[-<>"a-zA-Z0-9.%/_\\[\\]*+!:@, |()${}\\\'=\\\\n#&]*')),
		('COMMENTS', re.compile('[ \\t]*[;][^\\n]*')),
		('INCLUDE', re.compile('#[^\\n]*')),
		('OTHER', re.compile('[ \\t]?[ \\t]*')),
	]
	def __init__(self, str):
		Scanner.__init__(self,None,[],str)

class Grammar(Parser):
    def newgoal(self):
        lines = []
        while self._peek('END', "r'\\['", 'ATTRIBUTE', 'COMMENTS', 'INCLUDE', 'OTHER') != 'END':
            line = self.line()
            lines.append(line)
            _token_ = self._peek('ENDOFLINE', 'END')
            if _token_ == 'ENDOFLINE':
                ENDOFLINE = self._scan('ENDOFLINE')
            else:# == 'END'
                END = self._scan('END')
                return lines
        END = self._scan('END')
        return lines

    def goal(self):
        line = self.line()
        END = self._scan('END')
        return line

    def line(self):
        _token_ = self._peek("r'\\['", 'ATTRIBUTE', 'COMMENTS', 'INCLUDE', 'OTHER')
        if _token_ == "r'\\['":
            section = self.section()
            return section
        elif _token_ == 'ATTRIBUTE':
            attribute = self.attribute()
            return attribute
        elif _token_ == 'COMMENTS':
            comments = self.comments()
            return comments
        elif _token_ == 'INCLUDE':
            include = self.include()
            return include
        else:# == 'OTHER'
            other = self.other()
            return other

    def attribute(self):
        data = {}
        ATTRIBUTE = self._scan('ATTRIBUTE')
        data['attribute']=ATTRIBUTE
        self._scan("'[ \\t]?'")
        _token_ = self._peek("'[+]'", "''", "'[ \\t]*'")
        if _token_ == "'[+]'":
            self._scan("'[+]'")
        elif _token_ == "''":
            self._scan("''")
        else:# == "'[ \\t]*'"
            self._scan("'[ \\t]*'")
        self._scan("'='")
        _token_ = self._peek("'[ \\t]?'", "''")
        if _token_ == "'[ \\t]?'":
            self._scan("'[ \\t]?'")
        else:# == "''"
            self._scan("''")
        _token_ = self._peek("'>[ \\t]?'", "'[ \\t]?'")
        if _token_ == "'>[ \\t]?'":
            self._scan("'>[ \\t]?'")
        else:# == "'[ \\t]?'"
            self._scan("'[ \\t]?'")
        _token_ = self._peek('VALUE', "'[ \\t]?'")
        if _token_ == 'VALUE':
            VALUE = self._scan('VALUE')
            data['value']=VALUE
        else:# == "'[ \\t]?'"
            self._scan("'[ \\t]?'")
            data['value']=''
        self._scan("'[ \\t]?'")
        _token_ = self._peek('COMMENTS', 'OTHER')
        if _token_ == 'COMMENTS':
            comments = self.comments()
            data['info']=comments
        else:# == 'OTHER'
            other = self.other()
            data['other']=other
        return data

    def section(self):
        data = {}
        self._scan("r'\\['")
        SECTION = self._scan('SECTION')
        self._scan("'\\]'")
        data['section']=SECTION
        self._scan("'[ \\t]?'")
        _token_ = self._peek('COMMENTS', 'OTHER')
        if _token_ == 'COMMENTS':
            comments = self.comments()
            data['comment']=comments
        else:# == 'OTHER'
            other = self.other()
            data['other']=other
        return data

    def comments(self):
        data = {}
        COMMENTS = self._scan('COMMENTS')
        data['comment']=COMMENTS[1:]
        return data

    def include(self):
        data = {}
        INCLUDE = self._scan('INCLUDE')
        data['include']=INCLUDE[1:]
        return data

    def other(self):
        data = {}
        OTHER = self._scan('OTHER')
        data['other']=OTHER
        return data


def parse(rule, text):
	P = Grammar(GrammarScanner(text))
	return wrap_error_reporter(P, rule)



