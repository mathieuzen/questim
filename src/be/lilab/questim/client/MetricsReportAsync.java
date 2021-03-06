package be.lilab.questim.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

interface MetricsReportAsync {

public void getBalance(ArrayList<Region> rectXY, int frameWidth,int frameHeight, AsyncCallback<Double> asyncCallback);
public void getDensity(ArrayList<Region> rectXY, int frameWidth,int frameHeight, AsyncCallback<Double> asyncCallback);
public void getCAlignment(ArrayList<Region> rectXY, int frameWidth,int frameHeight, AsyncCallback<Double> asyncCallback);
public void getEAlignment(ArrayList<Region> rectXY, int frameWidth,int frameHeight, AsyncCallback<Double> asyncCallback);
public void getConcentricity(ArrayList<Region> rectXY, int frameWidth,int frameHeight, AsyncCallback<Double> asyncCallback);
public void getSimplicity(ArrayList<Region> rectXY, int frameWidth,int frameHeight, AsyncCallback<Double> asyncCallback);

}
