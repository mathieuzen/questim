package aesthetixy.appspot.com.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;

public interface MetricsReport extends RemoteService {
	public double getBalance(ArrayList <Region> rectXY, int frameWidth, int frameHeight);
	public double getDensity(ArrayList <Region> rectXY, int frameWidth, int frameHeight);
	public double getCAlignment(ArrayList <Region> rectXY, int frameWidth, int frameHeight);
	public double getEAlignment(ArrayList <Region> rectXY, int frameWidth, int frameHeight);
	public double getConcentricity(ArrayList <Region> rectXY, int frameWidth, int frameHeight);
}
