package aesthetixy.appspot.com.server;

import java.util.ArrayList;



import aesthetixy.appspot.com.client.Region;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Window;

public class Balance extends Metric {

	
	public static double calculate(ArrayList rectXY, int frameWidth, int frameHeight) {
		
		double [][] belonging = new double [rectXY.size()][4];
		double [] a = new double [rectXY.size()];
		value = 0.0;
		
		double BMvert;
		double BMhori;
		
		double amax = 0;
		
		double wl = 0;
		double wr = 0;
		double wt = 0;
		double wb = 0;
		
		for(int i=0;i<rectXY.size();i++){
			
			Region r = (Region) rectXY.get(i);
			
			
			a[i] = (double)r.getWidth()* (double)r.getHeight();
			if(a[i]>amax){
				amax = a[i];
			}
			
			
			//Belonging tests
			//UL
			
			if((r.getX() < frameWidth/2) && (r.getX()+r.getWidth() > frameWidth/2) && (r.getY() < frameHeight/2) && (r.getY()+r.getHeight() > frameHeight/2))
			{
				belonging[i][0] = ((double)(frameWidth/2 - r.getX())  * (frameHeight/2 - r.getY()))/(r.getWidth()*r.getHeight());
			}
			
			else if((frameWidth/2 - r.getX() < r.getWidth()) && (frameHeight/2 - r.getY() > r.getHeight()))
			{
				belonging[i][0] = ((double)(frameWidth/2 - r.getX())/(r.getWidth()));
			}
			
			else if((frameWidth/2 - r.getX() > r.getWidth()) && (frameHeight/2 - r.getY() < r.getHeight()))
			{
				belonging[i][0] = ((double)(frameHeight/2 - r.getY())/(r.getHeight()));
			}
			
			else if((frameWidth/2 - r.getX() > r.getWidth()) && (frameHeight/2 - r.getY() > r.getHeight()))
			{
				belonging[i][0] = 1;
			}
			
			//UR
						
			if((r.getX() < frameWidth/2) && (r.getX()+r.getWidth() > frameWidth/2) && (r.getY()+r.getHeight() > frameHeight/2))
			{
				belonging[i][1] = ((double)(r.getX()+r.getWidth()  - frameWidth/2)  * (frameHeight/2 - r.getY()))/(r.getWidth()*r.getHeight());
			}
			
			else if((r.getX()+r.getWidth() > frameWidth/2) && (r.getY()+r.getHeight() < frameHeight/2))
			{
				belonging[i][1] = ((double)(r.getX()+r.getWidth()  - frameWidth/2)/(r.getWidth()));
			}
			
			else if((r.getX() > frameWidth/2) && (r.getY()+r.getHeight() > frameHeight/2))
			{
				belonging[i][1] = ((double)(frameHeight/2 - r.getY())/(r.getHeight()));
			}
			
			if((r.getX() > frameWidth/2) && (r.getY()+r.getHeight() < frameHeight/2))
			{
				belonging[i][1] = 1;
			}
			
			//LL
			
			if((r.getX() < frameWidth/2) && (r.getX()+r.getWidth() > frameWidth/2) && (r.getY() < frameHeight/2) && (r.getY()+r.getHeight() > frameHeight/2))
			{
				belonging[i][2] = ((double)(frameWidth/2 - r.getX())  * (r.getY()+r.getHeight() - frameHeight/2))/(r.getWidth()*r.getHeight());
			}
			
			else if((r.getX() < frameWidth/2) && (r.getX()+r.getWidth() < frameWidth/2) && (r.getY() < frameHeight/2) && (r.getY()+r.getHeight() > frameHeight/2))
			{
				belonging[i][2] = ((double)(r.getY()+r.getHeight()-frameHeight/2)/(r.getHeight()));
			}
			
			else if((r.getX() < frameWidth/2) && (r.getX()+r.getWidth() > frameWidth/2) && (r.getY() > frameHeight/2) && (r.getY()+r.getHeight() > frameHeight/2))
			{
				belonging[i][2] = ((double)(frameWidth/2 - r.getX())/(r.getWidth()));
			}
			
			else if((r.getX() < frameWidth/2) && (r.getX()+r.getWidth() < frameWidth/2) && (r.getY() > frameHeight/2) && (r.getY()+r.getHeight() > frameHeight/2))
			{
				belonging[i][2] = 1;
			}
			
			//LR
			if((r.getX() < frameWidth/2) && (r.getX()+r.getWidth() > frameWidth/2) && (r.getY() < frameHeight/2) && (r.getY()+r.getHeight() > frameHeight/2))
			{
				belonging[i][3] = ((double)(r.getX()+r.getWidth()  - frameWidth/2)  * (r.getY()+r.getHeight() - frameHeight/2))/(r.getWidth()*r.getHeight());
			}
			
			else if((r.getX() > frameWidth/2) && (r.getX()+r.getWidth() > frameWidth/2) && (r.getY() < frameHeight/2) && (r.getY()+r.getHeight() > frameHeight/2))
			{
				belonging[i][3] = ((double)(r.getY()+r.getHeight()-frameHeight/2)/(r.getHeight()));
			}
			
			else if((r.getX() < frameWidth/2) && (r.getX()+r.getWidth() > frameWidth/2) && (r.getY() > frameHeight/2) && (r.getY()+r.getHeight() > frameHeight/2))
			{
				belonging[i][3] = ((double)(r.getX()+r.getWidth()-frameWidth/2)/(r.getWidth()));
			}
			
			else if((r.getX() > frameWidth/2) && (r.getX()+r.getWidth() > frameWidth/2) && (r.getY() > frameHeight/2) && (r.getY()+r.getHeight() > frameHeight/2))
			{
				belonging[i][3] = 1;
			}
			
			for(int j=0; j<4; j++){
				if(belonging[i][j]<0){
					belonging[i][j]=0;
				}
			}
			
			
			if(belonging[i][0] == 1 || belonging[i][1] == 1 || belonging[i][2] == 1 || belonging [i][3] == 1)
			{
			wl += belonging[i][0]*(1+(frameWidth/2 - (r.getX()+r.getWidth()/2))/frameWidth/2)*(a[i]/amax)+belonging[i][2]*(1+(frameWidth/2 - (r.getX()+r.getWidth()/2))/frameWidth/2)*(a[i]/amax);
			wr += belonging[i][1]*(1+((r.getX()+r.getWidth()/2) - frameWidth/2)/frameWidth/2)*(a[i]/amax)+belonging[i][3]*(1+((r.getX()+r.getWidth()/2) - frameWidth/2)/frameWidth/2)*(a[i]/amax);
			wt += belonging[i][0]*(1+(frameHeight/2 - (r.getY()+r.getHeight()/2))/frameHeight/2)*(a[i]/amax)+belonging[i][1]*(1+(frameHeight/2 - (r.getY()+r.getHeight()/2))/frameHeight/2)*(a[i]/amax);
			wb += belonging[i][2]*(1+((r.getY()+r.getHeight()/2) - frameHeight/2)/frameHeight/2)*(a[i]/amax)+belonging[i][3]*(1+((r.getY()+r.getHeight()/2) - frameHeight/2)/frameHeight/2)*(a[i]/amax);
			}
			else
			{
			wl += belonging[i][0]*(a[i]/amax)+belonging[i][2]*(a[i]/amax);
			wr += belonging[i][1]*(a[i]/amax)+belonging[i][3]*(a[i]/amax);
			wt += belonging[i][0]*(a[i]/amax)+belonging[i][1]*(a[i]/amax);
			wb += belonging[i][2]*(a[i]/amax)+belonging[i][3]*(a[i]/amax);
			}
			
		}
			
			
			BMvert = (wl-wr)/Math.max(wl,wr);
			BMhori = (wt-wb)/Math.max(wt,wb);
			
			value = 1-(Math.abs(BMvert)+Math.abs(BMhori))/2;
			
			//Window.alert("LEFT: "+wl+"\n RIGHT "+wr+"\n TOP: "+wt+"\n BOTTOM: "+wb);
			
		
		return value;
	}

	
	}

