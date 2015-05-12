package wsconnect;

import java.rmi.RemoteException;
import java.util.ArrayList;

import property.Memory;

import client.Poi;
import client.ResultFiltering;

import server.PoiWebServiceProxy;

public class WSInfo {
	private Memory m = Memory.getInstance();
	PoiWebServiceProxy proxy = new PoiWebServiceProxy();
	
	public WSInfo() {
		//defined in wsdl
		proxy.setEndpoint(m.getWsURL());
	}
	
	public String registerUser(String username, String password1, String password2) {
		String s = username + "#" + password1 + "#" + password2;
		try {
			return proxy.registerUser(s);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean sucsessfulLogIn(String username, String password) {
		String s = username + "#" + password;
		try {
			return proxy.sucsessfulLogIn(s);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public String setMonitorData(String username, String password, Double geolocationX, Double geolocationY, String PoiType, String PoiName) {
		String usernamePsw = username + "#" + password;
		String newEntry = geolocationX + "#" + geolocationY + "#" + PoiType + "#" + PoiName;
		try {
			return proxy.setMonitorData(usernamePsw, newEntry);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Poi> getMapData(String username, String password, Double geolocationX, Double geolocationY) {
		String usernamePsw = username + "#" + password;
		String position = geolocationX + "#" + geolocationY;
		try {
			String response = proxy.getMapData(usernamePsw, position);
			ArrayList<Poi> list = new ArrayList<Poi>();
			
			// x#y#subtype#name$
			System.out.println("Response: " + response);

			if (response.toString().equals("No POIs round this range in database!")) {
				double x = 0;
				double y = 0;
				String subtype = "null";
				String name = response;
				Poi p = new Poi(x, y, subtype, name);
				list.add(p); 
				return list;
			}
			
			String[] pois = response.split("\\$");
			for (String s : pois) {
				String[] fields = s.split("#");
				double x = Double.parseDouble(fields[0]);
				double y = Double.parseDouble(fields[1]);
				String subtype = fields[2];
				String name = fields[3];
				Poi p = new Poi(x, y, subtype, name);
				if(ResultFiltering.checksubtype(subtype) == true)
					list.add(p);
			}
			return list;						
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}  catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

    
}
