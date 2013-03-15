package be.lilab.questim.client;

import com.google.gwt.user.client.ui.Image;

public class MetricsDescription {
	
	String text = null;
	String img = null;
	
	public String getText(String metric){
		if(metric.equals("balance")){
			text = "Balance is the distribution of optical weight in a picture. " +
					"In a picture, the human eye perceives objects more important than others. " +
					"Dark colors, unusual shapes and large size add weight to objects. ";
		}
		return text;	
	}
	
	public String getImage(String metric){
		img = (metric+".png");
		return img;	
	}
}
