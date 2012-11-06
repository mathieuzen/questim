package aesthetixy.appspot.com.client;

import com.google.gwt.user.client.Window;

public class Density {

	String texte ="";
	float density;
	float area = 0;
	float areaframe;
	

	public Density(int[][] rectXY, int frameWidth, int frameHeight) {
			for(int i=1; i<rectXY.length; i++){
				if(rectXY[i][0]!=0 || rectXY[i][1]!=0)
				{
					texte += ("Rectangle "+i+" "+rectXY[i][2]+"x"+rectXY[i][3]+" in "+rectXY[i][0]+" "+rectXY[i][1]+"\n");
					area += rectXY[i][2]*rectXY[i][3];
				}
				
				areaframe = frameWidth*frameHeight;
				
				density = area/areaframe;
			}
			
			Window.alert("Frame area: "+areaframe+"\n Objects area: "+area+"\n Density: "+density);
	
	
	}

}
