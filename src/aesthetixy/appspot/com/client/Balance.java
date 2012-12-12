package aesthetixy.appspot.com.client;

import com.google.gwt.user.client.Window;

public class Balance {
	
	String texte ="";

	public Balance(int[][] rectXY, int frameWidth, int frameHeight) {
		// TODO Auto-generated constructor stub
	}

	public static float getBalance(int[][] rectXY, int frameWidth, int frameHeight) {
		
		float BM,BMvert,BMhori;	
		float wl,wr,wt,wb;
		float [][] d = null;
		float [][] a;
		float amax;
		float [][] c;
		float cframe;
		String [] belonging = null;
		int l = 0,r = 0,t =0,b = 0;
		
		for(int i=0;i<rectXY.length;i++){
		
		//Belonging tests
		if(rectXY[i][0]+rectXY[i][2]/2<=frameWidth/2){
			belonging[i] = "LEFT";
			l++;
		}
		if(rectXY[i][0]+rectXY[i][2]/2>=frameWidth/2){
			belonging[i] = "RIGHT";
			r++;
		}
		if(rectXY[i][1]+rectXY[i][3]/2<=frameHeight/2){
			belonging[i] = "TOP";
			t++;
		}
		if(rectXY[i][1]+rectXY[i][3]/2>=frameHeight/2){
			belonging[i] = "BOTTOM";
			b++;
		}
		
		}
		
		
		Window.alert("LEFT: "+l+"\n RIGHT "+r+"\n TOP: "+t+"\n BOTTOM: "+b);
		
		return 0;
	}

}
