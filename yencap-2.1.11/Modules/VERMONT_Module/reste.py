vermontcmd = popen2.Popen3(cmd,1)
		vermontcmd.tochild.close()

		outfile = vermontcmd.fromchild
		outfd = outfile.fileno()
		
		errfile = vermontcmd.childerr
		errfd = errfile.fileno()

		self.makeNonBlocking(outfd)            # don't deadlock!
    		self.makeNonBlocking(errfd)

		outdata = errdata = ""
		outeof = erreof = 0

		while 1:
			ready = select.select([outfd, errfd],[],[])
			if outfd in ready[0]:
				outchunk = outfile.read()
				if outchunk == "":
					outeof = 1
				outdata = outdata + outchunk

			if errfd in ready[0]:
				errchunk = errfile.read()
				if errchunk == "":
					erreof = 1
				errdata = errdata + errchunk
			if outeof and erreof: 
				break
			select.select([],[],[],.1)
			
		err = vermontcmd.wait()

		if err != 0:
			modulereply = ModuleReply(
			error_type=ModuleReply.APPLICATION,
			error_tag=ModuleReply.OPERATION_FAILED, 
			error_severity=ModuleReply.ERROR,
			error_message="Restart error: " + errdata)
			return modulereply

"""
		xmllintcmd = popen2.Popen3(cmd,1)
		xmllintcmd.tochild.close()
		outfile = xmllintcmd.fromchild
		outfd = outfile.fileno()
		
		errfile = xmllintcmd.childerr
		errfd = errfile.fileno()

		errdata = ""
		outeof = erreof = 0

		while 1:
			ready = select.select([outfd, errfd],[],[])
			if outfd in ready[0]:
				outchunk = outfile.read()
				if outchunk == "":
					outeof = 1
				outdata = outdata + outchunk

			if errfd in ready[0]:
				errchunk = errfile.read()
				if errchunk == "":
					erreof = 1
				errdata = errdata + errchunk
			if outeof and erreof: 
				break
			select.select([],[],[],.1)
			
		err = xmllintcmd.wait()
