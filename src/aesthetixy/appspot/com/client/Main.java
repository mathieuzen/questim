package aesthetixy.appspot.com.client;

import java.util.ArrayList;
import java.util.Scanner;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

//change
public class Main implements EntryPoint {
	
	double balance = 0;
	double calignment = 0;
	double ealignment = 0;
	double density = 0;
	double concentricity = 0;
	
	Label balanceVlue = new Label(String.valueOf(balance));
	Label calignmentVlue = new Label(String.valueOf(calignment));
	Label ealignmentVlue = new Label(String.valueOf(ealignment));
	Label densityVlue = new Label(String.valueOf(density));
	Label concentricityVlue = new Label(String.valueOf(concentricity));
	
	Boolean browse = false;
	Boolean objects = false;
	//Boolean to know if mouse left click is down
	Boolean mouseDown = false;
	//URL of the void picture to draw when no screenshot loaded
	static String url = Window.Location.getParameter("url");
	Frame frame = new Frame(url);
	//Labels that displays mouse position on the canvas
	Label lblx = new Label("000");
	Label lbly = new Label("000");
	//frame size
	static int frameWidth = (int) (Window.getClientWidth()/1.5);
	static int frameHeight = (int) (Window.getClientHeight()/1.5);
	int x = 0;
	int y = 0;
	int width;
	int height;
	final Canvas myCanvas = Canvas.createIfSupported();
	static double zoom = 1.5;
	Region newone;
	Boolean clear = false;
	//array saving drawn rectangles position coordinates
	ArrayList<Region> rectXY = new ArrayList<Region>();
	String color = "#848484";
	String styleName ="";
	
	private MetricsReportAsync service;
	
	private MetricsReportAsync getService(){
		if(service == null){
			service = (MetricsReportAsync) GWT.create(MetricsReport.class);
			((ServiceDefTarget)service).setServiceEntryPoint(GWT.getModuleBaseURL()+"MetricsService");
		}
		return service;
	}
	
	public void onModuleLoad() {

		/*if(url == null || url.equals("undefined")){
			Window.alert("This is no valid URL!");
			Window.Location.replace("/questim.html");
		}*/
			
		//Clear the panel on module load (for refresh function)
		RootPanel.get().clear();
		Window.setMargin("0px");
		
		//RootPanel AttachHandler (unknown purpse for now)
    	final RootPanel rootPanel = RootPanel.get();
    	rootPanel.setStyleName("rootPanel");
    	//rootPanel.setSize("900px", "650px");
    	rootPanel.addAttachHandler(new Handler() {
    		public void onAttachOrDetach(AttachEvent event) {
    		}
    	});
    	
    	
    	
    	//Canvas and Context creation
    	myCanvas.setStyleName("canvas");
    	myCanvas.setCoordinateSpaceWidth(frameWidth);
    	myCanvas.setCoordinateSpaceHeight(frameHeight);
    	final Context2d context = myCanvas.getContext2d();	     
    	
    	//Write coordinates of mouse position in labels and draw a rectangle
    	myCanvas.addMouseMoveHandler(new MouseMoveHandler(){
    		public void onMouseMove(MouseMoveEvent event){
    				
    				context.clearRect(0, 0, frameWidth, frameHeight);

        			for(int j=0;j<rectXY.size();j++)
    				{
    					Region r;
    					r = rectXY.get(j);
    					context.strokeRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
    					context.setFillStyle(r.color);
    					context.fillRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
    					context.setGlobalAlpha(0.7);
    				}
    				
    				
        			if(mouseDown == false)
    				{
        				x = event.getRelativeX(myCanvas.getCanvasElement());    	
            			y = event.getRelativeY(myCanvas.getCanvasElement());
            			writeCoordinates(x,y);
    				}
        			
        			
        			if(mouseDown == true )
        			{	
        				context.strokeRect(x, y, event.getRelativeX(myCanvas.getCanvasElement())-x, event.getRelativeY(myCanvas.getCanvasElement())-y);      			      				
        	        	context.setFillStyle(color);
    					context.fillRect(x, y, event.getRelativeX(myCanvas.getCanvasElement())-x, event.getRelativeY(myCanvas.getCanvasElement())-y);
        			}   			
        	
    		}
    	});
    	   	
    	
    	//Change Boolean mouseDown to true if mouse left click is down
     	myCanvas.addMouseDownHandler(new MouseDownHandler(){
    		public void onMouseDown(MouseDownEvent e){
    			mouseDown = true;
    				}
    	});
    	
     	if(Window.Location.getParameter("imageLoaded").equals("true"))
		{
			Image img = new Image(Window.Location.getParameter("fileUrl"));
			//rootPanel.add(img, 50,135);
			img.setWidth(frameWidth+"px");
			img.setHeight(frameHeight+"px");
			rootPanel.add(img, Window.getClientWidth()/4-img.getOffsetWidth()/2, 50);

		}
	
		else
		{
			drawFrame(zoom);
	
		}
    	
     	drawCanvas();
    	
    	//add label x,y to the panel for mouse position
    	//rootPanel.add(lblx, 355, 632); 
    	//rootPanel.add(lbly, 395, 632);
    	rootPanel.add(lblx, Window.getClientWidth()/2-20, frameHeight+70); 
    	rootPanel.add(lbly, Window.getClientWidth()/2+20, frameHeight+70);
    	
    	//add a panel above canvas (a sort of "toolpanel")
    	AbsolutePanel absolutePanel = new AbsolutePanel();
    	absolutePanel.setStyleName("gwt-horizontalPanel");
    	rootPanel.add(absolutePanel, 50, 50);
    	absolutePanel.setSize("130px", frameHeight+"px");
    	Image metricReport = new Image("file2.png");
    	metricReport.setSize("38px","43px");
    	//Label firststeptext = new Label("Pick up a color.");
    	//firststeptext.setStyleName("steptext");
    	
    	Button zoomout = new Button("Zoom out");
    	zoomout.addClickHandler(new ClickHandler() {
    		public void onClick(ClickEvent event) {
    			if(zoom<2)
    			{
    			zoom+=0.1;
    			rootPanel.remove(frame);
    			rootPanel.remove(myCanvas);
    			drawFrame(zoom);
    			drawCanvas();
    			
    			for(int j=0;j<rectXY.size();j++)
				{
					Region r = rectXY.get(j);
					//r.setX((int)(r.getX()+14));
					//r.setY((int)(r.getY()-14));
					//r.setWidth((int)(r.getWidth()/1.1));
					//r.setHeight((int)(r.getHeight()/1.1));
					rectXY.set(j, r);
				}
    			context.clearRect(0, 0, frameWidth, frameHeight);

    			for(int j=0;j<rectXY.size();j++)
				{
					Region r;
					r = rectXY.get(j);
					context.strokeRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
					context.setFillStyle(r.color);
					context.fillRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
					context.setGlobalAlpha(0.7);
				}
    			}
    			
    			
    		}
    	});
    	
    	Button zoomin = new Button("Zoom in");
    	zoomin.addClickHandler(new ClickHandler() {
    		public void onClick(ClickEvent event) {
    			if(zoom>0.5)
    			{
    			zoom-=0.1;
    			rootPanel.remove(frame);
    			drawFrame(zoom);
    			drawCanvas();
    			for(int j=0;j<rectXY.size();j++)
				{
					Region r = rectXY.get(j);
					//r.setX((int)(r.getX()-14));
					//r.setY((int)(r.getY()+14));
					//r.setWidth((int)(r.getWidth()*1.1));
					//r.setHeight((int)(r.getHeight()*1.1));
					rectXY.set(j, r);
				}
    			
    			context.clearRect(0, 0, frameWidth, frameHeight);

    			for(int j=0;j<rectXY.size();j++)
				{
					Region r;
					r = rectXY.get(j);
					context.strokeRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
					context.setFillStyle(r.color);
					context.fillRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
					context.setGlobalAlpha(0.7);
				}
    			
    			}
    		}
    	});
    	
    	zoomin.setSize("60px", "50px");
    	zoomout.setSize("60px", "50px");
    	
    	absolutePanel.add(zoomin,absolutePanel.getOffsetWidth()/2-60/2,90);
    	absolutePanel.add(zoomout,absolutePanel.getOffsetWidth()/2-60/2,160);
    	
    	//add a button for metric report
    	PushButton btnMetricReport = new PushButton(metricReport);
    	btnMetricReport.addClickHandler(new ClickHandler() {
    		public void onClick(ClickEvent event) {
    			//new MetricsReport(rectXY, frameWidth, frameHeight);
    			getService().getBalance(rectXY,frameWidth, frameHeight, new AsyncCallback<Double>() {
					
					@Override
					public void onSuccess(Double result) {
						balance = result;
						balanceVlue.setText(String.valueOf(balance));
					}
					
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Not working... \n"+caught.toString());
						caught.printStackTrace();
					}
				});
    			
    			getService().getDensity(rectXY,frameWidth, frameHeight, new AsyncCallback<Double>() {
					
					@Override
					public void onSuccess(Double result) {
						density = result;
						densityVlue.setText(String.valueOf(density));
					}
					
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Not working... \n"+caught.toString());
						caught.printStackTrace();
					}
				});
    			
    			getService().getCAlignment(rectXY,frameWidth, frameHeight, new AsyncCallback<Double>() {
					
					@Override
					public void onSuccess(Double result) {
						calignment = result;
						calignmentVlue.setText(String.valueOf(calignment));
					}
					
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Not working... \n"+caught.toString());
						caught.printStackTrace();
					}
				});
    		
    			getService().getEAlignment(rectXY,frameWidth, frameHeight, new AsyncCallback<Double>() {
					
					@Override
					public void onSuccess(Double result) {
						ealignment = result;
						ealignmentVlue.setText(String.valueOf(ealignment));
					}
					
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Not working... \n"+caught.toString());
						caught.printStackTrace();
					}
				});
    		
    			getService().getConcentricity(rectXY,frameWidth, frameHeight, new AsyncCallback<Double>() {
					
					@Override
					public void onSuccess(Double result) {
						concentricity = result;
						concentricityVlue.setText(String.valueOf(concentricity));
					}
					
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Not working... \n"+caught.toString());
						caught.printStackTrace();
					}
				});
    		}
    	});
    	btnMetricReport.setSize("40px", "45px");
    	absolutePanel.add(btnMetricReport, 40, 230);
    	
 
    	//absolutePanel.add(firststeptext, 20, 15);
    	    	
    	//add a grid with 6 color buttons from white to black (4 shades of grey)
    	Grid grid = new Grid(2, 3);
    	grid.setSize("43px", "63px");
    	absolutePanel.add(grid, 40, 15);
    	
    	//white button
    	Button btnColor = new Button("");
    	btnColor.addClickHandler(new ClickHandler() {
    		public void onClick(ClickEvent event) {
    			
    			//context.setFillStyle("#FEFDFD");
    			color ="#FEFDFD";
    			styleName = "White3";
  
    		}
    	});
    	btnColor.setStyleName("gwt-ColorWhite");
    	grid.setWidget(0, 0, btnColor);
    	btnColor.setSize("14px", "16px");
    	
    	//grey1 button
    	Button button = new Button("");
    	button.addClickHandler(new ClickHandler() {
    		public void onClick(ClickEvent event) {
    			//rootPanel.getBodyElement().getStyle().setProperty("cursor","url(cursorgrey.ico),auto");
    			color ="#EAEAEA";
    			styleName = "White2";
    		}
    	});
    	button.setStyleName("gwt-ColorWhite2");
    	grid.setWidget(0, 1, button);
    	button.setSize("14px", "16px");
    	
    	//grey2 button
    	Button button_3 = new Button("");
    	button_3.addClickHandler(new ClickHandler() {
    		public void onClick(ClickEvent event) {
    			color ="#CBCACA";
    			styleName = "White1";
    		}
    	});
    	button_3.setStyleName("gwt-ColorWhite3");
    	grid.setWidget(0, 2, button_3);
    	button_3.setSize("14px", "16px");
    	
    	//grey3 button
    	Button button_1 = new Button("");
    	button_1.addClickHandler(new ClickHandler() {
    		public void onClick(ClickEvent event) {
    			color ="#848484";
    			styleName = "Black1";
    		}
    	});
    	button_1.setStyleName("gwt-ColorBlack1");
    	grid.setWidget(1, 0, button_1);
    	button_1.setSize("14px", "16px");
    	
    	//grey4 button
    	Button button_2 = new Button("");
    	button_2.addClickHandler(new ClickHandler() {
    		public void onClick(ClickEvent event) {
    			color ="#494949";
    			styleName = "Black2";
    		}
    	});
    	button_2.setStyleName("gwt-ColorBlack2");
    	grid.setWidget(1, 1, button_2);
    	button_2.setSize("14px", "16px");
    	
    	//black button
    	Button button_4 = new Button("");
    	button_4.addClickHandler(new ClickHandler() {
    		public void onClick(ClickEvent event) {
    			color ="#000000";
    			styleName = "Black3";
    		}
    	});
    	button_4.setStyleName("gwt-ColorBlack3");
    	grid.setWidget(1, 2, button_4);
    	button_4.setSize("14px", "16px");
    	
    	//panel for "onglets"
    	HorizontalPanel onglets = new HorizontalPanel();
    	rootPanel.add(onglets, Window.getClientWidth()-50-absolutePanel.getOffsetWidth(), 50);
    	onglets.setStyleName("panellat");
    	onglets.setSize("130px", "50px");
    	
    	//add a right-side panel to write coordinates, width and height of each drawn rectangle
    	final ScrollPanel absolutePanel_1 = new ScrollPanel();
    	rootPanel.add(absolutePanel_1, Window.getClientWidth()-50-absolutePanel.getOffsetWidth(), 100);
    	absolutePanel_1.setStyleName("panellat");
    	absolutePanel_1.setSize("130px", frameHeight-50+"px");
    	
    	final ScrollPanel absolutePanel_2 = new ScrollPanel();
    	rootPanel.add(absolutePanel_2, Window.getClientWidth()-50-absolutePanel.getOffsetWidth(), 100);
    	absolutePanel_2.setStyleName("panellat");
    	absolutePanel_2.setSize("130px", frameHeight-50+"px");
    	absolutePanel_2.setVisible(false);
    	
    	//add a VerticalPanel in this right-side panel 
    	final VerticalPanel verticalPanel = new VerticalPanel();
    	absolutePanel_1.add(verticalPanel);
    	verticalPanel.setSize("130px", "20px");
    	
    	final VerticalPanel metricsPanel = new VerticalPanel();
    	absolutePanel_2.add(metricsPanel);
    	metricsPanel.setSize("130px", "20px");
    	metricsPanel.setVisible(false);
    	    	
    	HorizontalPanel Balance = new HorizontalPanel();
    	Label balanceLbl = new Label("Balance");
    	balanceLbl.setStyleName("metricsLabel");
    	balanceVlue.setStyleName("metricsValue");
    	Balance.add(balanceLbl);
    	Balance.add(balanceVlue);
    	metricsPanel.add(Balance);
    	
    	HorizontalPanel CAlignment = new HorizontalPanel();
    	Label calignmentLbl = new Label("CAlignment");
    	calignmentLbl.setStyleName("metricsLabel");
    	calignmentVlue.setStyleName("metricsValue");
    	CAlignment.add(calignmentLbl);
    	CAlignment.add(calignmentVlue);
    	metricsPanel.add(CAlignment);
    	
    	HorizontalPanel EAlignment = new HorizontalPanel();
    	Label ealignmentLbl = new Label("EAlignment");
    	ealignmentLbl.setStyleName("metricsLabel");
    	ealignmentVlue.setStyleName("metricsValue");
    	EAlignment.add(ealignmentLbl);
    	EAlignment.add(ealignmentVlue);
    	metricsPanel.add(EAlignment);
    	
    	HorizontalPanel Density = new HorizontalPanel();
    	Label densityLbl = new Label("Density");
    	densityLbl.setStyleName("metricsLabel");
    	densityVlue.setStyleName("metricsValue");
    	Density.add(densityLbl);
    	Density.add(densityVlue);
    	metricsPanel.add(Density);
    	
    	HorizontalPanel Concentricity = new HorizontalPanel();
    	Label concentricityLbl = new Label("Concentricity");
    	concentricityLbl.setStyleName("metricsLabel");
    	concentricityVlue.setStyleName("metricsValue");
    	Concentricity.add(concentricityLbl);
    	Concentricity.add(concentricityVlue);
    	metricsPanel.add(Concentricity);
    	
    	//add onglet "Objects" to the "onglets" panel
    	Button objects = new Button("Objects");
    	objects.setSize("65px", "50px");
    	objects.addClickHandler(new ClickHandler(){
    		public void onClick(ClickEvent event){
    				verticalPanel.setVisible(true);
    				absolutePanel_1.setVisible(true);
    				metricsPanel.setVisible(false);
    				absolutePanel_2.setVisible(false);
    		}
    	});
    	Button metrics = new Button("Metrics");
    	metrics.setSize("65px", "50px");
    	metrics.addClickHandler(new ClickHandler(){
    		public void onClick(ClickEvent event){
    				verticalPanel.setVisible(false);
    				absolutePanel_1.setVisible(false);
    				metricsPanel.setVisible(true);
    				absolutePanel_2.setVisible(true);
    		}
    	});
    	onglets.add(objects);
    	onglets.add(metrics);
    	
    	//add a button to clear panel in "toolpanel"
    	Button btnNewButton = new Button("Clear Panel");
    	btnNewButton.addClickHandler(new ClickHandler() {
    		public void onClick(ClickEvent event) {
    			context.clearRect(0,0,800,740);
				for(int i=0;i<rectXY.size();i++)
				{
					rectXY.clear();
					verticalPanel.clear();

					clear = true;
					
				}
    		}
    	});
    	btnNewButton.setSize("60px", "70px");
    	absolutePanel.add(btnNewButton, absolutePanel.getOffsetWidth()/2-60/2, 305);
    	
    	final Button btnBrowse = new Button ("Browse");
    	btnBrowse.addClickHandler(new ClickHandler(){
    		public void onClick(ClickEvent event){
    			browse = !browse;
    			if(browse == true){
    				myCanvas.setVisible(false);
    				btnBrowse.setText("Draw");
    			}
    			if(browse == false){
    				myCanvas.setVisible(true);
    				btnBrowse.setText("Browse");
    			}
    		}
    	});
    	btnBrowse.setSize("60px", "35px");
    	absolutePanel.add(btnBrowse, absolutePanel.getOffsetWidth()/2-60/2, 385);
    	
    	//Change Boolean mouseDown to false if mouse left click is up
    	myCanvas.addMouseUpHandler(new MouseUpHandler(){
    		int i=0;
    		public void onMouseUp(MouseUpEvent e){  			
    			mouseDown = false;
    			
    			if(clear == true)
    			{
    				clear = false;
    				i=0;
    			}
    			
    			newone = new Region(x,y,e.getRelativeX(myCanvas.getCanvasElement())-x,e.getRelativeY(myCanvas.getCanvasElement())-y,color);
    			rectXY.add(newone);
    			
    			/*rectXY[i][0] = rectXY[0][0];
    			rectXY[i][1] = rectXY[0][1];
    			rectXY[i][2] = rectXY[0][2];
    			rectXY[i][3] = rectXY[0][3];*/
    			Region r;
    			r = rectXY.get(i);
    			//rectCOL[i] = rectCOL[0];
    			
    			i++;
     			
    			final VerticalPanel verticalPanel2 = new VerticalPanel();
    			verticalPanel2.setStyleName("object"+styleName);
    	    	verticalPanel.add(verticalPanel2);
    	    	
    	    	final HorizontalPanel horizontalPanel = new HorizontalPanel();
    	    	verticalPanel2.add(horizontalPanel);
    	    	
    	    	final VerticalPanel verticalPanel3 = new VerticalPanel();
    	    	horizontalPanel.add(verticalPanel3);
    	    	verticalPanel3.setStyleName("LabelObject");
    	    			
    			Label lblRectangle = new Label("Rectangle"+i);
    			lblRectangle.setStyleName("Label");
    	    	verticalPanel3.add(lblRectangle);
    	    	
    	    	
    	    	Label lblxy = new Label("("+r.getX()+":"+r.getY()+")");
    	    	lblxy.setStyleName("Label");
    	    	verticalPanel3.add(lblxy);
    	    	
    	    	
    	    	Label lblwh = new Label("w: "+r.getWidth()+" h: "+r.getHeight());
    	    	lblwh.setStyleName("Label");
    	    	verticalPanel3.add(lblwh);
    	    	
    	    
    	    	final Button delete = new Button("X");
    	    	delete.setStyleName("delete");
    	    	delete.setTabIndex(i);
    	    	horizontalPanel.add(delete);
    	    	delete.addClickHandler(new ClickHandler() {
    	    		public void onClick(ClickEvent event) {
    	    			Widget t = (delete.getParent()).getParent();
    	    			t.removeFromParent();
    	    			Region rdel = rectXY.get(delete.getTabIndex()-1);
    	    			context.clearRect(rdel.getX()-1, rdel.getY()-1, rdel.getWidth()+2, rdel.getHeight()+2);
    	    			i=delete.getTabIndex();
    	    			rectXY.remove(delete.getTabIndex()-1);
    	    			i = rectXY.size();
    	    			//Problem here: try to delete a rectangle in app and add other rectangles after this one; it erases other coordinates
    	    		}
    	    	});
    				}
    	});
    	   	    	
    	
    	
	}
	
	public void writeCoordinates(int x, int y){
		//convert x and y in string
		lblx.setText(String.valueOf(x));
		lbly.setText(String.valueOf(y));
	}
	
	public static void setUrl(String str){
		url = str;
	}
	
	public void drawFrame(double zoom){
		//add a frame to panel with specified url
		frame.setUrl(url);
		//rootPanel.add(frame, 50, 135);
		frame.setSize(frameWidth*zoom+"px", frameHeight*zoom+"px");
		//RootPanel.get().remove(0);
		//RootPanel.get().insert(frame, 0, Window.getClientWidth()/2-frameWidth/2, 50);
		RootPanel.get().add(frame, Window.getClientWidth()/2-frameWidth/2, 50);
		frame.getElement().getStyle().setProperty("transform", "scale("+1/zoom+")");
		frame.getElement().getStyle().setProperty("-moz-transform", "scale("+1/zoom+")");
		frame.getElement().getStyle().setProperty("-ms-transform", "scale("+1/zoom+")");
		frame.getElement().getStyle().setProperty("-webkit-transform", "scale("+1/zoom+")");
		frame.getElement().getStyle().setProperty("-o-transform", "scale("+1/zoom+")");
	}
 	public void drawCanvas(){
	//add a canvas to panel on the preceding frame
	//RootPanel.get().add(myCanvas, 50, 135);
 	myCanvas.setSize(frameWidth+"px", frameHeight+"px");
 	RootPanel.get().add(myCanvas, Window.getClientWidth()/2-frameWidth/2, 50);
 	}
}
