package client_gui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import wsconnect.WSInfo;
import client.Configuration;
import client.Poi;
import client.ResultFiltering;
import property.Memory;

public class GetTab extends JPanel implements ActionListener,ItemListener {
	private int T;
	private Timer timer = new Timer();
	private UpdateGUITask task;
    JButton button = new JButton("Click me!");
    private JPanel selectType = new JPanel();
    private JPanel selectPosition = new JPanel();
    private JPanel poisArea = new JPanel();
    private JPanel refreshpanel = new JPanel();
	private final WSInfo wsinfo = new WSInfo();
	private Memory m = Memory.getInstance();

	private JButton refresh = new JButton ("Refresh");
	private JLabel selectCoordinatesLabel = new JLabel("Select Position :");
	private JTextField X = new JTextField("Type Coordinate X", 10);
	private JTextField Y = new JTextField("Type Coordinate Y", 10);
	private JButton okay = new JButton("OK!");   
	String[] names = {"Entertainment","Food","Education","drink","fastfood restaurant",
			"library","cinema","takeaway restaurant","university",
			"sightseeing","typical restaurant"};

	JCheckBox[] tp = new JCheckBox[11];
	boolean[] resflt = new boolean[11];
	private JTextArea poitextArea = new JTextArea(20,35){
		ImageIcon icon = new ImageIcon("background.jpg");
		Image image = icon.getImage();
		
		Image grayImage = GrayFilter.createDisabledImage(image);
	    {
	      setOpaque(false);
	    }
	    public void paint(Graphics g) {
	        g.drawImage(grayImage, 0, 0, this);
	        super.paint(g);
	      }
	};
	
	private JScrollPane scroll= new JScrollPane(poitextArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
	
	private final Configuration config;
    
    public GetTab(Configuration c , int t ) {
    	config = c;
    	
    	for(int i = 0; i < tp.length; i++){
    		tp[i] = new JCheckBox(names[i]);
    		selectType.add(tp[i]);
    		tp[i].addActionListener(this);
    	}
    	
    	this.add(selectType);
       	selectType.setLayout(new GridLayout(4,3));
       	
       	this.add(selectPosition,BorderLayout.CENTER);
    	selectPosition.add(selectCoordinatesLabel);
    	selectPosition.add(X);
    	selectPosition.add(Y);
    	selectPosition.add(okay);
    	System.out.println(X.getText());
    	
    	this.add(poisArea,BorderLayout.SOUTH);
    	poitextArea.setLineWrap(true);
  		poitextArea.setWrapStyleWord(true);
		poitextArea.setEditable(false);
		scroll.setAutoscrolls(true);
		poisArea.add(scroll);
		
		this.add(refreshpanel);
		refreshpanel.setLayout(new GridLayout(1,1));
		add(refresh);
    
		T = t;
		task = new UpdateGUITask(T);
		timer.schedule(task, T*1000);
		
    	okay.addActionListener(this);
    	refresh.addActionListener(this);
        setVisible(true);
    	
       // System.out.println(c.toString());
    	
    }
    public void Refresh() {
    	getmapdata();
		task = new UpdateGUITask(T);
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		for(int i = 0; i < tp.length; i++){
			if (tp[i] == e.getSource()){
				//System.out.print(" " + i + ":" +  selected);
				resflt[i] = tp[i].isSelected();
			}
		}

		
	   	if (e.getSource() == okay) {
			double x =ParseDouble(X.getText());   
			double y = ParseDouble(Y.getText());
			
			boolean x_okay;
			boolean y_okay;
			String errors = "Result: ";
			
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
			
			if (x_okay && y_okay) {
			   getmapdata();
			} else {
				JOptionPane.showMessageDialog(this, errors);
			}
		}
		
		if (e.getSource() == refresh) {			
			try {
				getmapdata();
		    } catch (Exception ex) {
				JOptionPane.showMessageDialog(this, ex.toString());
		    }
		}
	}
	
	private double ParseDouble(String strNumber) {
		 if (strNumber != null && strNumber.length() > 0  && !strNumber.contains("-") && !strNumber.contains("Type")) {
		       try {
		          return Double.parseDouble(strNumber);
		       } catch(Exception e) {
		          return -1;  
		       }
		   }
		   else return -1;
	}

	// -------------------  TASK   --------------------
	class UpdateGUITask extends TimerTask {
		int T;
        
		public UpdateGUITask(int T) {
			this.T = T;
		}
		
		@Override
		public void run() {
			System.out.println("Tick!!!");
			Refresh();	
		}		
	}
	
	public void getmapdata () {
		String username = config.getUsername();
		String password = config.getPassword();
		double x =ParseDouble(X.getText());   
		double y = ParseDouble(Y.getText());
		for (int i = 0; i < resflt.length; i++) {
			System.out.print(" " + i + ":" +  resflt[i] + "\n");
		}
		
		new ResultFiltering(resflt);
			
		ArrayList<Poi> poi_list = wsinfo.getMapData(username, password, x, y);
		
		poitextArea.setText("");
		outerloop:
		for (Poi p : poi_list) {
			if (p.getName().equals("No POIs round this range in database!")) {
				poitextArea.append("No POIs round this range in database!");
				break outerloop;
			}
			poitextArea.append(p.toString());
			poitextArea.append("\n");
			System.out.println(p.toString());
		}
	}
}
	