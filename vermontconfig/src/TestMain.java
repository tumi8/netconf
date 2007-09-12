class TestMain {
	public static void main(String args[]) {
		if ( args.length != 6) {
			System.out.println("Program arguments are: hostname port username password role configfile");
			return;
		}

		String hostname = args[0];
		int port = Integer.parseInt( args[1] );
		String username = args[2];
		String password = args[3];
		String role = args[4];
		String configFile = args[5];


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
			reply = config.sendConfig();
			System.out.println("Got reply: \n" + reply + "\n\n");
			
			System.out.println("Trying to restart VERMONT...");
			reply = config.restart();
			System.out.println("Got reply: \n" + reply + "\n\n");
		} catch ( Exception e ) {
			System.out.println( "Error: " + e.getMessage() );
			return;
		}

		System.out.println("Sucess! Sent " + configFile + " to host " + hostname);
	}
}
