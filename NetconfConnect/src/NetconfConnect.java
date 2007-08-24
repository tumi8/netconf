
import java.util.logging.*;
import java.util.List;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;


/*
 * A really simple GUI to send NetConf requests to a agent over SSH.
 */

public class NetconfConnect extends JFrame implements ActionListener {
    
    static final long serialVersionUID = 1234234;
    
    JTextArea messageInArea, messageOutArea;
    static private String AppTitle = "NetConf Connect";
    static private JButton connectButton, sendButton, exit;
    
    String hostInput, loginInput, passwdInput;
    JTextField hostName, portName, loginName, passwordName;
    boolean ipv6;
    private static Logger mylogger;
    NcConnector myconnection;
    
    public NetconfConnect() {
        super(AppTitle);
        mylogger = Logger.getLogger("NetConfManagerLog");
        JFrame main;
        Container container;
        JPanel connectionPanel, buttonPanel, messagePanel1, messagePanel2;
        JScrollPane scroll1 = new JScrollPane();
        JScrollPane scroll2 = new JScrollPane();
        messageInArea = new JTextArea(20, 40);
        messageOutArea = new JTextArea(20,40);
        scroll1.getViewport().add(messageInArea);
        scroll2.getViewport().add(messageOutArea);
        
        main = new JFrame(AppTitle);
        container = main.getContentPane();
        container.setLayout(new GridLayout(3,1));
        main.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        /* add Connection buttons */
        connectionPanel = new JPanel(new GridLayout(7, 2));
        JLabel hostLabel = new JLabel("Host: ");
        hostName = new JTextField("127.0.0.1");
        JLabel portLabel = new JLabel("Port: ");
        portName = new JTextField("830");
        JLabel loginLabel = new JLabel("Login: ");
        loginName = new JTextField("max");
        JLabel passwordLabel = new JLabel("Password: ");
        passwordName = new JTextField("monitor");
        JCheckBox ipv6Check = new JCheckBox("Use IPv6", false);
        JLabel receivedLabel = new JLabel("Received:");
        JLabel sendLabel = new JLabel("Send:");
        
        
        
        connectButton = new JButton("Connect");
        connectButton.addActionListener(this);
        sendButton = new JButton("Send");
        sendButton.addActionListener(this);
        exit = new JButton("Disconnect");
        exit.addActionListener(this);
        connectionPanel.add(hostLabel);
        connectionPanel.add(hostName);
        connectionPanel.add(portLabel);
        connectionPanel.add(portName);
        connectionPanel.add(loginLabel);
        connectionPanel.add(loginName);
        connectionPanel.add(passwordLabel);
        connectionPanel.add(passwordName);
        connectionPanel.add(ipv6Check);
        connectionPanel.add(new JLabel());
        buttonPanel = new JPanel(new GridLayout(1,2));
        buttonPanel.add(connectButton);
        buttonPanel.add(exit);
        connectionPanel.add(buttonPanel);
        connectionPanel.add(sendButton);
        
        
        
        container.add(connectionPanel);
        //messagePanel1 = new JPanel(new FlowLayout());
        //messagePanel1.add(sendLabel);
        //messagePanel1.add(messageOutArea);
        container.add(scroll2);
        //messagePanel2 = new JPanel(new FlowLayout());
        //messagePanel2.add(receivedLabel);
        container.add(scroll1);
        
        //container.add(messagePanel2);
        
        main.pack();
        main.setSize(400, 400);
        main.setVisible(true); /* Display Main Window */
    } // NetConfManager
    
    public void actionPerformed(ActionEvent e) {
        
        /* "Connect to.." menuitem has been clicked */
        if ((e.getActionCommand().equals("Connect"))){
            StringBuffer messageBuffer = new StringBuffer();
            myconnection = new NcConnector(hostName.getText(), Integer.parseInt(portName.getText()), loginName.getText(), passwordName.getText(), ipv6);
            //mylogger.info("Started Connector");
            List capabilities = new ArrayList();
            
            try{
                capabilities = myconnection.connect();
            } catch(Exception ex){
                messageBuffer.append("Exception trying to connect " +   ex.getMessage());
            }
            if(capabilities.size() != 1){
                messageBuffer.append("Capabilities: \n");
                for(int i = 0; i < capabilities.size(); i++){
                    messageBuffer.append(capabilities.get(i) + "\n");
                }
            }
            else{
                messageBuffer.append("Error: ");
                messageBuffer.append(capabilities.get(0));
            }
            messageInArea.setText(messageBuffer.toString());
        } else if((e.getActionCommand().equals("Send"))){
            
            String messageBuffer = new String();
            
            messageBuffer = myconnection.sendRPC(messageOutArea.getText());
            
            messageInArea.setText(messageBuffer);
        } else if((e.getActionCommand().equals("Disconnect"))){
            try{
                String messageBuffer = myconnection.disconnect();
                messageInArea.setText(messageBuffer);
            } catch (Exception closingex){
                System.out.println(closingex.getMessage());
                //System.exit(-1);
            }
            //System.exit(0);
        }
        /* "Exit" button has been clicked */
        
    } // actionPerformed
    
    public static void main(String[] args) throws IOException {
        try {
            NetconfConnect manager = new NetconfConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // main
    
} // class NetConfManager

