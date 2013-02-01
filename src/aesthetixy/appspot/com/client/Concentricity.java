package aesthetixy.appspot.com.client;

import java.util.ArrayList;

import com.google.gwt.user.client.Window;

import aesthetixy.appspot.com.client.Region;

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
			
			dbar += Math.hypot((double)r.getX()-xc, (double)r.getY()-yc);
		}
		
		dbar/=rectXY.size();
		
		value = dbar/ddiag;
		
		return value;
		
	}

}
