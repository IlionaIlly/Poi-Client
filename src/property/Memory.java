package property;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class Memory {
	private static Memory memory;		// (1)
	private final String wsURL;
	private final String T;
	
	
	private Properties prop = new Properties();

	// (2)
	private Memory(){
		try{
			//Load property file from project folder
			prop.load(new FileInputStream("settings.properties"));
		} catch (IOException ex) {
			System.err.println("IO Exception occured while loading property file");
		}
		wsURL = prop.getProperty("WEB_SERVICE_URL");
		T = prop.getProperty("T");
	}
	

	@Override
	public String toString() {
		String s = 	"wsURL: " + wsURL + "\n" + 
					"types: " +"\n" + 
					"T: " + T + "\n";
		return s;
	}

	// (3)
	public static Memory getInstance() {
		if (memory == null) {
			synchronized (Memory.class) { // (4)
				 if (memory == null) {
					 memory = new Memory();
				 }
			}
		}
		return memory;
	}
	
	public String getWsURL() {
		return wsURL;
	}

	public String getT() {
		return T;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		System.out.println("Clone not supported for singleton object");
		return null;
	}
	
}
