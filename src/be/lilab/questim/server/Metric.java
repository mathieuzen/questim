package be.lilab.questim.server;

public abstract class Metric {
	
	double goodValue;
	static double value;
	
	public double calculate(){
		return value;
	}

}
