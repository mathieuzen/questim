package aesthetixy.appspot.com.server;



import java.util.ArrayList;



import aesthetixy.appspot.com.client.Region;

import com.google.gwt.i18n.client.NumberFormat;


public class Density extends Metric {

	static float density;
	static float area = 0;
	static float areaframe;
	NumberFormat fmt = NumberFormat.getFormat("##0.##");

	public static double calculate(ArrayList rectXY, int frameWidth, int frameHeight) {
		value = 0;
		areaframe=0;
		area=0;
		
		for(int i=0; i<rectXY.size(); i++){
			Region r = (Region) rectXY.get(i);
			if(r.getX()!=0 || r.getY()!=0)
			{
				area += r.getWidth()*r.getHeight();
			}
			
			areaframe = frameWidth*frameHeight;
		
			value = area/areaframe;
		
		}
		
		return value;

	}
	
}
	
