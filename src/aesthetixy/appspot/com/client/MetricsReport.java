package aesthetixy.appspot.com.client;

import java.util.ArrayList;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Window;

public class MetricsReport {
	NumberFormat fmt = NumberFormat.getFormat("##0.##");
	double density = 0;
	double balance = 0;
	double alignment = 0;
	double centeralignment = 0;
	double concentricity = 0;

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
				
				alignment = Alignment.calculate(rectXY);
				String msgAlignment;
				if(alignment<0.2){
					msgAlignment = "Bad !";
				}
				else if(alignment<0.5){
					msgAlignment = "Ok...";
				}
				else{
					msgAlignment= "Good !";
				}
				
				centeralignment = CenterAlignment.calculate(rectXY);
				String msgCenterAlignment;
				if(centeralignment<0.2){
					msgCenterAlignment = "Bad !";
				}
				else if(centeralignment<0.5){
					msgCenterAlignment = "Ok...";
				}
				else{
					msgCenterAlignment= "Good !";
				}
				
				concentricity = Concentricity.calculate(rectXY,frameWidth,frameHeight);
				String msgConcentricity;
				if(concentricity>0.7){
					msgConcentricity = "Bad !";
				}
				else if(concentricity>0.4){
					msgConcentricity = "Ok...";
				}
				else{
					msgConcentricity = "Good !";
				}
				
			
			Window.alert("METRIC REPORT\n Density = "+fmt.format(density)+"  "+msgDensity
						+"\n Balance = "+fmt.format(balance)+"  "+msgBalance
						+"\n EAlignment = "+fmt.format(alignment)+"  "+msgAlignment
						+"\n CAlignment = "+fmt.format(centeralignment)+"  "+msgCenterAlignment
						+"\n Concentricity = "+fmt.format(concentricity)+"  "+msgConcentricity
					);
			}
		
	}
}
