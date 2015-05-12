package client_gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import property.Memory;
import wsconnect.WSInfo;
import client.Configuration;



public class SetTab extends JPanel implements ActionListener{
	
	private JPanel panel = new JPanel();
	private JPanel position = new JPanel();
	private JPanel insertpoi = new JPanel();
	private JTextField name = new JTextField("Type the POI name here",37);
	private JLabel coord_x = new JLabel("Coordinate X");
	private JTextField coord_X = new JTextField(10);
	private JLabel coord_y = new JLabel("Coordinate Y");
	private JTextField coord_Y = new JTextField(10);
	private JButton insert = new JButton ("Insert your POI");
	private JLabel lbsubtype =  new JLabel ("Subtype");
	private JComboBox<String> subtypelist = new JComboBox<String>();
	private JLabel image = new JLabel();
	private final Configuration config;
	private final WSInfo wsinfo = new WSInfo();
	private Memory m = Memory.getInstance();
	
			
	public SetTab (Configuration c ) {
		 
		this.setLayout(new GridLayout(4,1));
		config = c;
		
		ImageIcon icon = new ImageIcon("images.gif");
		image.setIcon(icon);
        add(image);
		
        add(panel);
		panel.add(name);
		panel.add(lbsubtype);
		panel.add(subtypelist);
	
		add(position);
		position.add(coord_x);
		position.add(coord_X);
		position.add(coord_y);
		position.add(coord_Y);
	     
		add(insertpoi);
		insertpoi.add(insert);
       
		
		subtypelist.addItem("");
        subtypelist.addItem("drink");
        subtypelist.addItem("cinema");
        subtypelist.addItem("sightseeing");
        subtypelist.addItem("library");
        subtypelist.addItem("university");
        subtypelist.addItem("fastfood restaurant");
        subtypelist.addItem("takeaway restaurant");
        subtypelist.addItem("typical restaurant");
        
        subtypelist.addActionListener(this);
        insert.addActionListener(this);
        
    	setVisible(true);
    	//System.out.println(c.toString());
	}
    
@Override
	public void actionPerformed(ActionEvent e) {
		 
		if(e.getSource() == insert) {
			try {
				String poiname = name.getText().trim();
				String username = config.getUsername();
				String password = config.getPassword();
				String comboitem = subtypelist.getSelectedItem().toString().trim();
				double x = ParseDouble(coord_X.getText().trim());   
				double y = ParseDouble(coord_Y.getText().trim());
				
				boolean poiname_okay;
				boolean x_okay;
				boolean y_okay;
				
				String errors = "Insertion not committed. Reasons: \n";
				
				// check poi name
				if (poiname.isEmpty()) {
					errors += "Poi name should be at least 2 characters \n";
					poiname_okay = false;
				} else {
					poiname_okay = true;
				}
				
				// check x_okay:
				if (x == -1) {
					errors += "x is not a valid positive number \n";
					x_okay = false;
				} else {
					x_okay = true;
				}
				
				if (y == -1) {
					errors += "y is not a valid positive number \n";
					y_okay = false;
				} else {
					y_okay = true;
				}
				
				if (poiname_okay && x_okay && y_okay) {
					// call web service
					String resp = wsinfo.setMonitorData(username,password,x ,y,comboitem,poiname);
					errors = "Insertion result: \n\n";
					// response ...
					if(resp.equals("This POI already exists1!") || resp.equals("This POI already exists2!")) {
						errors += "Error: Sorry. This POI exists \n";
					}
					if(resp.equals("Acsess denied! Poi was not inserted and SET was not counted!")) {
						errors += "Error: Sorry. This space is already occupied \n";
					}
					if(resp.equals("Succesfull POI insertion2") || resp.equals("Succesfull POI insertion1") ) {
						errors += "Successful POI insertion! good work!";
					}
					System.out.println(resp);
					JOptionPane.showMessageDialog(this, errors);
					
				} else {
					JOptionPane.showMessageDialog(this, errors);
				}
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(this, e1.toString());
				// TODO Auto-generated catch block
				e1.printStackTrace();
		
			}
		}
        
	}

	private double ParseDouble(String strNumber) {
		 if (strNumber != null && strNumber.length() > 0  && !strNumber.contains("-")) {
		       try {
		          return Double.parseDouble(strNumber);
		       } catch(Exception e) {
		          return -1;  
		       }
		   }
		   else return -1;
	}

}


