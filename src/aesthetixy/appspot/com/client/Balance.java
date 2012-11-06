package aesthetixy.appspot.com.client;

import com.google.gwt.user.client.Window;

public class Balance {
	
	String texte ="";
	

	public Balance(int[][] rectXY, int frameWidth, int frameHeight) {
			for(int i=1; i<rectXY.length; i++){
				if(rectXY[i][0]!=0 || rectXY[i][1]!=0)
				{
					texte += ("Rectangle "+i+" "+rectXY[i][2]+"x"+rectXY[i][3]+" in "+rectXY[i][0]+" "+rectXY[i][1]+"\n");
				}
				
			}
			
			Window.alert(texte);
			
	}

}
