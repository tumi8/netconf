class TestMain {

	private static void usage()
	{
		System.out.println("Program arguments are: hostname port username password role configfile [operation]");
		System.out.println("\thostname - dnsdomainname of the host running yencap");
		System.out.println("\tport - port to connect to");
		System.out.println("\tusername - username that is used for ssh authentification");
		System.out.println("\tpassword - password that is used for ssh authentification");
		System.out.println("\trole - role name that is used for RBAC authentification");
		System.out.println("\tconfigfile - filename of the configuration file that will be sent to vermont");
		System.out.println("\toperation (optional):");
		System.out.println("\t\tcopy-config - tell yencap that the configuration file is a complete configuration (default)");
		System.out.println("\t\tedit-config - tell yencap that the configuration file should be merged with the current configuration");
	}

	public static void main(String args[]) {
		if ( args.length != 6 && args.length != 7) {
			usage();
			return;
		}

		String hostname = args[0];
		int port = Integer.parseInt( args[1] );
		String username = args[2];
		String password = args[3];
		String role = args[4];
		String configFile = args[5];
		String operation = "copy-config";
		if ( args.length == 7) {
			operation = args[6];
		}


		netconf.VermontConfig config = new netconf.VermontConfig();
		try {
			System.out.println("Connecting ...");
			config.connectToVermont(hostname, port, username, password, role);

			System.out.println("Reading config file...");
			config.readConfig(configFile);
			
			System.out.println("Sending netconf role ...");
			String reply = config.sendRoleData();
			System.out.println("Got reply: \n" + reply + "\n\n");
			
			System.out.println("Sending configuration data ...");
			if ( operation.equals("copy-config")) {
				reply = config.copyConfig();
			} else {
				reply = config.editConfig("merge");
			}
			System.out.println("Got reply: \n" + reply + "\n\n");
			
			System.out.println("Trying to restart VERMONT...");
			reply = config.restart();
			System.out.println("Got reply: \n" + reply + "\n\n");
		} catch ( Exception e ) {
			System.out.println( "Error: " + e.getMessage() );
			return;
		}

		System.out.println("Sent " + configFile + " to host " + hostname);
	}
}
