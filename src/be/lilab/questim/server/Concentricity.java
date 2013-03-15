package be.lilab.questim.server;

import java.util.ArrayList;


import be.lilab.questim.client.Region;

import com.google.gwt.user.client.Window;


public class Concentricity extends Metric {
	
	public static double calculate(ArrayList rectXY, int frameWidth, int frameHeight){
		
		value = 0.0;
		double ddiag = Math.hypot((double)frameWidth/2,(double)frameHeight/2);
		double dbar = 0.0;
		double dic = 0.0;
		int xc = frameWidth/2;
		int yc = frameHeight/2;
		
		for(int i=0;i<rectXY.size();i++){
			Region r = (Region)rectXY.get(i);
			
			dbar += Math.hypot((double)(r.getX()+r.getWidth()/2)-xc, (double)(r.getY()+r.getHeight()/2)-yc);
		}
		
		dbar/=rectXY.size();
		
		value = dbar/ddiag;
		
		return value;
		
	}

}
