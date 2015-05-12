package main;

import property.Memory;
import client_gui.ClientForm;

public class ClientMain {
	
    public static void main(String[] args) {     
        //http://Alinas-MacBook-Pro.local:9999/PoiService/PoiWebServiceImpl?WSDL  
    	
    	
    	//System.out.println("test");
    	//test();
    	
    	
    	Memory m = Memory.getInstance();
    	int t = Integer.parseInt(m.getT());
    	System.out.println(m);
    	
    	ClientForm f = new ClientForm(t);
        f.Show();    	
    }
}