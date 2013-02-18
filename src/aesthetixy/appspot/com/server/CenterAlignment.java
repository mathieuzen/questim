package aesthetixy.appspot.com.server;

import java.util.ArrayList;



import aesthetixy.appspot.com.client.Region;

import com.google.gwt.user.client.Window;


public class CenterAlignment extends Metric {
	
	static int treshold = 5;
	static int n;
	static int verticalCentralAlignment;
	static int horizontalCentralAlignment;
	static int CAV;
	static int CAH;
	
	public static double calculate(ArrayList rectXY) {
		
		verticalCentralAlignment = 0;
		horizontalCentralAlignment = 0;
		CAV = 0;
		CAH = 0;
		n = rectXY.size();
		value = 0;
		
		for(int i=0; i<rectXY.size();i++){	
			Region r1 = (Region)rectXY.get(i);
			verticalCentralAlignment = 0;
			horizontalCentralAlignment = 0;
			for(int j=0; j<rectXY.size();j++){
				if(j!=i){				
				Region r2 = (Region)rectXY.get(j);
					if((r1.getX()+r1.getWidth()/2<=r2.getX()+r2.getWidth()/2+treshold) && (r1.getX()+r1.getWidth()/2>=r2.getX()+r2.getWidth()/2-treshold))
						verticalCentralAlignment = 1;
					if((r1.getY()+r1.getHeight()/2<=r2.getY()+r2.getHeight()/2+treshold) && (r1.getY()+r1.getHeight()/2>=r2.getY()+r2.getHeight()/2-treshold))
						horizontalCentralAlignment = 1;
				}
			}
			
			CAV += verticalCentralAlignment;
			CAH += horizontalCentralAlignment;
			
		}
		
		//Window.alert(""+CAV+" - "+CAH);
		value = (double)(CAV + CAH)/(n*2);
		return value;

	}
	
}
	
