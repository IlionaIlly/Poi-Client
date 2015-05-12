package client;

public class ResultFiltering {
	private static boolean[] resflt;
	
	public ResultFiltering(boolean[] resflt) {
		super();
		ResultFiltering.resflt = resflt;
	}
	
	public boolean[] getResflt() {
		return resflt;
	}

	public void setResflt(boolean[] resflt) {
		ResultFiltering.resflt = resflt;
	}
	 
	/**
	 * "Entertainment","Food","Education","drink","fastfood restaurant",
	 *		"library","cinema","takeaway restaurant","university",
	 *		"sightseeing","typical restaurant"
	 * @param subtype
	 * @return
	 */
	
	
	
	public static boolean checksubtype(String subtype) {
		
		if(resflt[0] == false && resflt[1] == false && resflt[2] == false && resflt[3] == false && resflt[4] == false && resflt[5] == false && resflt[6] == false && resflt[7] == false && resflt[8] == false && resflt[9] == false && resflt[10] == false) 
			return true;
		
		if(resflt[0] == true) {
			if(subtype.equals("drink"))
				return true;
			if(subtype.equals("cinema"))
				return true;
			if(subtype.equals("sightseeing"))
				return true;
		}
		if(resflt[1] == true) {
			if(subtype.equals("fastfood restaurant"))
				return true;
			if(subtype.equals("takeaway restaurant"))
				return true;
			if(subtype.equals("typical restaurant"))
				return true;
		}
		if(resflt[2] == true) {
			if(subtype.equals("library"))
				return true;
			if(subtype.equals("university"))
				return true;
		}
		if(resflt[3] == true)
			if(subtype.equals("drink"))
				return true;
		if(resflt[4] == true)
			if(subtype.equals("fastfood restaurant"))
				return true;
		if(resflt[5] == true)
			if(subtype.equals("library"))
				return true;
		if(resflt[6] == true)
			if(subtype.equals("cinema"))
				return true;
		if(resflt[7] == true)
			if(subtype.equals("takeaway restaurant"))
				return true;
		if(resflt[8] == true)
			if(subtype.equals("university"))
					return true;
		if(resflt[9] == true)
			if(subtype.equals("sightseeing"))
				return true;
		if(resflt[10] == true)
			if(subtype.equals("typical restaurant"))
				return true;
		return false;
	}
	
}
