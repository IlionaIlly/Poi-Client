package client_gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

import property.Memory;
import client.Configuration;

public class GetSetFrame extends JFrame {
	final Configuration config;
	private Memory m = Memory.getInstance();
	
	private void initUI() {

        JMenuBar menubar = new JMenuBar();

        JMenu file = new JMenu("Menu");
        file.setMnemonic(KeyEvent.VK_F);

        JMenuItem eMenuItem = new JMenuItem("Logout", null);
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.setToolTipText("Exit application");
        
        eMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	dispose();
            	int t =  Integer.parseInt(m.getT());
                ClientForm logFace = new ClientForm(t);
        		logFace.setVisible(true);
            }
        });

        file.add(eMenuItem);

        menubar.add(file);

        setJMenuBar(menubar);
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
	
	public GetSetFrame(Configuration c){
		super("Welcome");
		this.config=c;
		
		initUI();
		
		setSize(500,620);
		setLocation(400,50); 
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTabbedPane tabbedPane = new JTabbedPane();
		SetTab set = new SetTab(config);
		int t =  Integer.parseInt(m.getT());
		GetTab get = new GetTab(config,t);
		add(tabbedPane);
		tabbedPane.add("SetTab", set);
		tabbedPane.add("GetTab", get);
	}
	 
	  public static void main(String[] args) {
		  GetSetFrame frameTabel = new GetSetFrame(new Configuration("Roi", "123"));
	  
	  
		  frameTabel.setVisible(true);
	 
	}
}