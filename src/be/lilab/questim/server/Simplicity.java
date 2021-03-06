package be.lilab.questim.server;

import java.util.ArrayList;


import be.lilab.questim.client.Region;

import com.google.gwt.user.client.Window;


public class Simplicity extends Metric {
	
	static int treshold = 1;
	static int n;
	static int verticalAlignment1;
	static int horizontalAlignment1;
	static int verticalAlignment2;
	static int horizontalAlignment2;
	static int DAV;
	static int DAH;
	
	public static double calculate(ArrayList rectXY, int frameWidth, int frameHeight){
		
		value = 0.0;
		
		verticalAlignment1 = 0;
		horizontalAlignment1 = 0;
		verticalAlignment2 = 0;
		horizontalAlignment2 = 0;
		DAV = 0;
		DAH = 0;
		n = rectXY.size();
		
		for(int i=0; i<rectXY.size();i++){	
			Region r1 = (Region)rectXY.get(i);
			verticalAlignment1 = 0;
			horizontalAlignment1 = 0;
			verticalAlignment2 = 0;
			horizontalAlignment2 = 0;
			for(int j=0; j<rectXY.size();j++){
				if(j!=i){				
				Region r2 = (Region)rectXY.get(j);
					if(!(r1.getX()<=r2.getX()+treshold) && !(r1.getX()>=r2.getX()-treshold))
						verticalAlignment1=1;
					if(!(r1.getX()+r1.getWidth()<=r2.getX()+r2.getWidth()+treshold) && !(r1.getX()+r1.getWidth()>=r2.getX()+r2.getWidth()-treshold))
						verticalAlignment2=1;
					if(!(r1.getY()<=r2.getY()+treshold) && !(r1.getY()>=r2.getY()-treshold))
						horizontalAlignment1=1;
					if(!(r1.getY()+r1.getHeight()<=r2.getY()+r2.getHeight()+treshold) && !(r1.getY()+r1.getHeight()>=r2.getY()+r2.getHeight()-treshold))
						horizontalAlignment2=1;
				}
			}
			
			DAV += verticalAlignment1 + verticalAlignment2;
			DAH += horizontalAlignment1 + horizontalAlignment2;					
		}
		value = (double) 1/(DAV+DAH+n);
		//Formule différente
		
		
		return value;
	}
}
