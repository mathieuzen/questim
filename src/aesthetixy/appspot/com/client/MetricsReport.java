package aesthetixy.appspot.com.client;

import java.util.ArrayList;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Window;

public class MetricsReport {
	NumberFormat fmt = NumberFormat.getFormat("##0.##");
	float density = 0;
	float balance = 0;

	public MetricsReport(ArrayList rectXY, int frameWidth, int frameHeight){
			
			density = Density.getDensity(rectXY, frameWidth, frameHeight);
			String msgDensity;
			if(density<0.2||density>0.8){
				msgDensity = "Bad !";
			}
			else if(density<0.4||density>0.6){
				msgDensity = "Ok...";
			}
			else{
				msgDensity = "Good !";
			}
			/*	
			balance = Balance.getBalance(rectXY, frameWidth, frameHeight);
			String msgBalance;
			if(balance<0.4){
				msgBalance = "Bad !";
			}
			else if(balance<0.7){
				msgBalance = "Ok...";
			}
			else{
				msgBalance = "Good !";
			}*/
			
			Window.alert("METRIC REPORT\n Density = "+fmt.format(density)+"  "+msgDensity);
			//Window.alert("METRIC REPORT\n Density = "+fmt.format(density)+"  "+msgDensity+"\n Balance = "+fmt.format(balance)+"  "+msgBalance);
			
	}
}
