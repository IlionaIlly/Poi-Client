package client;

public class Poi {
	double x;
	double y;
	String subtype;
	String name;
	//String type;
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Poi : [name=" + name + ", x=" + x + ", y=" + y + ", subtype="
				+ subtype + "]";
	}

	public String getSubtype() {
		return subtype;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}


	public Poi(double x, double y,  String subtype, String name) {
		super();
		this.x = x;
		this.y = y;
		this.name = name;
		this.subtype = subtype;
	}
	
	
}

