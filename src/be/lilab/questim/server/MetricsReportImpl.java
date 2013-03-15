package be.lilab.questim.server;

import java.text.DecimalFormat;
import java.util.ArrayList;



import be.lilab.questim.client.MetricsReport;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class MetricsReportImpl extends RemoteServiceServlet implements MetricsReport{
	DecimalFormat fmt = new DecimalFormat("##0.##");
	double density = 0;
	double balance = 0;
	double alignment = 0;
	double centeralignment = 0;
	double concentricity = 0;
	double simplicity = 0;

	public double getBalance(ArrayList rectXY, int frameWidth, int frameHeight){
			
			double b = 0;
		
			if(rectXY.isEmpty()){
				Window.alert("You must draw at least 1 element in order to compute metrics.");
			}
			
			else{
		
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
				
			
			b= balance;
			}
			return b;
		
	}

	public double getDensity(ArrayList rectXY, int frameWidth, int frameHeight){
		
		double d = 0;
	
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
		
		d= density;
		}
		return d;
	
}
	
public double getCAlignment(ArrayList rectXY, int frameWidth, int frameHeight){
		
		double c = 0;
	
		if(rectXY.isEmpty()){
			Window.alert("You must draw at least 1 element in order to compute metrics.");
		}
		
		else{						
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
		
		c= centeralignment;
		}
		return c;
	
}

public double getEAlignment(ArrayList rectXY, int frameWidth, int frameHeight){
	
	double e = 0;

	if(rectXY.isEmpty()){
		Window.alert("You must draw at least 1 element in order to compute metrics.");
	}
	
	else{
		
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
	
	e= alignment;
	}
	return e;

}

public double getConcentricity(ArrayList rectXY, int frameWidth, int frameHeight){
	
	double con = 0;

	if(rectXY.isEmpty()){
		Window.alert("You must draw at least 1 element in order to compute metrics.");
	}
	
	else{
		
		concentricity = Concentricity.calculate(rectXY,frameWidth,frameHeight);
		String msgConcentricity;
		if(concentricity>0.8){
			msgConcentricity = "Bad !";
		}
		else if(concentricity>0.5){
			msgConcentricity = "Ok...";
		}
		else{
			msgConcentricity = "Good !";
		}
		
	
	con= concentricity;
	}
	return con;

}

public double getSimplicity(ArrayList rectXY, int frameWidth, int frameHeight){
	
	double sim = 0;

	if(rectXY.isEmpty()){
		Window.alert("You must draw at least 1 element in order to compute metrics.");
	}
	
	else{
		
		simplicity = Simplicity.calculate(rectXY,frameWidth,frameHeight);
	sim= simplicity;
	}
	return sim;

}
}
