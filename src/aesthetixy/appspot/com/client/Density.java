package aesthetixy.appspot.com.client;



import java.util.ArrayList;

import com.google.gwt.i18n.client.NumberFormat;


public class Density {

	static String texte ="";
	static float density;
	static float area = 0;
	static float areaframe;
	NumberFormat fmt = NumberFormat.getFormat("##0.##");

	public static float getDensity(ArrayList rectXY, int frameWidth, int frameHeight) {
		density=0;
		areaframe=0;
		area=0;
			for(int i=1; i<rectXY.size(); i++){
				Region r = (Region) rectXY.get(i);
				if(r.getX()!=0 || r.getY()!=0)
				{
					area += r.getWidth()*r.getHeight();
				}
				
				areaframe = frameWidth*frameHeight;
			
				density = area/areaframe;
			
			}
			
			//Window.alert("Frame area: "+areaframe+"\nObjects area: "+area+"\nDensity: "+fmt.format(density));
			return density;
	
	}



}
