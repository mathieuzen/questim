package aesthetixy.appspot.com.client;

import java.util.ArrayList;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Window;

public class MetricsReport {
	NumberFormat fmt = NumberFormat.getFormat("##0.##");
	double density = 0;
	double balance = 0;

	public MetricsReport(ArrayList rectXY, int frameWidth, int frameHeight){
			if(rectXY.isEmpty()){
				Window.alert("You must draw at least 1 element in order to compute metrics.");
			}
			
			else{
				density = Density.calculate(rectXY, frameWidth, frameHeight);
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
				
				balance = Balance.calculate(rectXY, frameWidth, frameHeight);
				String msgBalance;
				if(balance<0.5){
					msgBalance = "Bad !";
				}
				else if(balance<0.8){
					msgBalance = "Ok...";
				}
				else{
					msgBalance = "Good !";
				}
				
			
			Window.alert("METRIC REPORT\n Density = "+fmt.format(density)+"  "+msgDensity
						+"\n Balance = "+fmt.format(balance)+"  "+msgBalance
					);
			}
		
	}
}
