package client_gui;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import wsconnect.WSInfo;
import client.Configuration;

public class ClientForm extends JFrame implements ActionListener, WindowListener  {
	private int T;
	private Timer timer = new Timer();
	private UpdateGUITask task;
	private final WSInfo wsinfo = new WSInfo();
	
	private JPanel panel = new JPanel();
	
	private JLabel background = new JLabel(new ImageIcon("background.jpg"));
	
	private JButton blogin = new JButton("Login");
	private JPanel bloginpanel = new JPanel();
	private JLabel bloglabel =  new JLabel ("Login to POI service");
	 
	private JLabel usrloglabel =  new JLabel ("Username");
	private JTextField txuser = new JTextField(20);
	private JLabel pswloglabel =  new JLabel ("Password");
	private JPasswordField pass = new JPasswordField(20);
	
	private JButton signup = new JButton("Sign up");
	private JPanel signuppanel = new JPanel();
	private JLabel usrsignlabel =  new JLabel ("Username");
	private JLabel signlabel =  new JLabel ("Still not a POIer. Sign up!");
	private JTextField txsuser = new JTextField(20);
	private JLabel pswsignlabel =  new JLabel ("Password");
	private JPasswordField psw1 = new JPasswordField(20);
	private JLabel psw1signlabel =  new JLabel ("Re-enter password");
	private JPasswordField psw2 = new JPasswordField(20);
	
	
	public ClientForm(int t) {
		super("Signup/Login Autentification");
		
		setSize(720,499);
		setLocation(350,100);
		
		setResizable(false);
        
		panel.setLayout(new GridLayout(2,1));
		background.setLayout(new FlowLayout());
		bloginpanel.setLayout(new GridLayout(6,1));
		signuppanel.setLayout(new GridLayout(8,1));
		bloglabel.setFont(new Font("serif", Font.BOLD, 16));
		signlabel.setFont(new Font("serif", Font.BOLD, 16));
		usrloglabel.setFont(new Font("serif", Font.PLAIN, 13));
		pswloglabel.setFont(new Font("serif", Font.PLAIN, 13));
		usrsignlabel.setFont(new Font("serif", Font.PLAIN, 13));
		pswsignlabel.setFont(new Font("serif", Font.PLAIN, 13));
		psw1signlabel.setFont(new Font("serif", Font.PLAIN, 13));
	
		add(background);
		background.add(bloginpanel);
		background.add(signuppanel);
		
		bloginpanel.add(bloglabel);
		bloginpanel.add(usrloglabel);
		bloginpanel.add(txuser);
		bloginpanel.add(pswloglabel);
		bloginpanel.add(pass);
		bloginpanel.add(blogin);
		
		signuppanel.add(signlabel);
		signuppanel.add(usrsignlabel);
		signuppanel.add(txsuser);
		signuppanel.add(pswsignlabel);
		signuppanel.add(psw1);
		signuppanel.add(psw1signlabel);
		signuppanel.add(psw2);
		signuppanel.add(signup);
		
		T = t;
		task = new UpdateGUITask(T);
		timer.schedule(task, T*1000, T*1000);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		blogin.addActionListener(this);
		signup.addActionListener(this);
		
	}
    
	public void Show() {
		this.setVisible(true);       // make the frame visible
	}
	
	public void Refresh() {
		task = new UpdateGUITask(T);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Login
		if (e.getSource() == blogin) {
			
			String puname = txuser.getText(); 	
			String ppaswd = pass.getText();
			

			if (puname.isEmpty() || ppaswd.isEmpty()){
				JOptionPane.showMessageDialog(this,"Login failed! Missing username/password.");
				txuser.setText("");
				pass.setText("");
				txuser.requestFocus();
			}
			else if(wsinfo.sucsessfulLogIn(puname, ppaswd) == true) {
				Configuration c = new Configuration(puname, ppaswd);
				GetSetFrame regFace = new GetSetFrame(c);
				regFace.setVisible(true);
				setVisible(false);
				dispose();
			}
			else {
			    JOptionPane.showMessageDialog(null,"Login failed! Wrong Password/Username!");
				txuser.setText("");
				pass.setText("");
				txuser.requestFocus();
			}
		}
		
		//Registration
		try {
			if (e.getSource() == signup) {
				
				String puname = txsuser.getText();
				String ppaswd = psw1.getText();
				String ppaswdrep = psw2.getText();
				
				boolean username_okay;
				boolean psw1_okay;
				boolean psw2_okay;
				
				String errors = "Insertion not committed. Reasons: \n";		
				
				// check username
				if (puname.isEmpty()) {
					errors += "Usernaname should be at least 2 characters \n";
					username_okay = false;
				} else {
					username_okay = true;
				}
				
				if (ppaswd.isEmpty() || ppaswdrep.isEmpty()) {
					errors += "You should fill the password \n";
					username_okay = false;
				} else {
					username_okay = true;
				}
				// check psw1_okay:
				if (!ppaswd.equals(ppaswdrep)) {
					errors += "Password doesnt match \n";
					psw1_okay = false;
					psw2_okay = false;
				} else {
					psw1_okay = true;
					psw2_okay = true;
				}
				
				if (username_okay && psw1_okay && psw2_okay) {
					// call web service
					String resp = wsinfo.registerUser(puname, ppaswd, ppaswdrep);
					errors = "Signup result: \n\n";
					// response ...
					if(resp.equals("ERROR: Already registered")) {
						errors += "ERROR: Already registered \n";
					}
					if(resp.equals("SUCCESS: Successful registration")) {
						errors += "Successful registration! Now you can login with your details \n";
						signupTextClear();
						txuser.setText(puname);
						pass.setText(ppaswd);
					}
					if(resp.equals("ERROR: Invalid username")) {
						errors += "ERROR: Invalid username";
					}
					if(resp.equals("ERROR: You have to fill the fields to proceed.")) {
						errors += "ERROR: You have to fill the fields to proceed.";
					}
					if(resp.equals("ERROR: Database insert failed")) {
						errors += "ERROR: Database insert failed";
					}
					System.out.println(resp);
					JOptionPane.showMessageDialog(this, errors);
					
				} else {
					JOptionPane.showMessageDialog(this, errors);
				}
			}
		} catch (HeadlessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	}
	
	/**
	 * Clears the text left by sign up action.
	 */
	public void signupTextClear() {
		txsuser.setText("");
		psw1.setText("");
		psw2.setText("");
		txsuser.requestFocus();
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
	}
    
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		timer.cancel();
	}
    
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
    
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
	}
    
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
	}
    
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
    
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
	}
	
	
	
	
	// -------------------  TASK   --------------------
	class UpdateGUITask extends TimerTask {
		int T;
        
		public UpdateGUITask(int T) {
			this.T = T;
		}
		
		@Override
		public void run() {
			System.out.println("TICK!!!");
			Refresh();	
		}		
	}
}
