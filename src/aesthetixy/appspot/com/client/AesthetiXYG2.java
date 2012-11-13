package aesthetixy.appspot.com.client;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;


public class AesthetiXYG2 implements EntryPoint {
	
	/*
	 *Some variables declarations 
	 */
	
	//Boolean to know if mouse left click is down
	Boolean mouseDown = false;
	
	//URL of the void picture to draw when no screenshot loaded
	public static String url ="http://googcloudlabs.appspot.com/";
	
	//Labels that displays mouse position on the canvas
	Label lblx = new Label("000");
	Label lbly = new Label("000");
	
	//frame size
	int frameWidth = 640;
	int frameHeight = 480;
	
	int x = 0;
	int y = 0;
	
	Boolean clear = false;
	
	//2D array saving drawn rectangles position coordinates
	int rectXY[][] = new int[20][5];
	String rectCOL[] = new String[20];
	
	public void onModuleLoad() {
		
		for(int i=0;i<rectCOL.length;i++)
			rectCOL[i]=null;
		
		
		//Clear the panel on module load (for refresh function)
		RootPanel.get().clear();
		Window.setMargin("0px");
		
		//Command relative to File/New item
		Command new1 = new Command(){
			public void execute(){
				//call a DialogBox demanding to the user a URL
				    final MyDialogBox mDBox = new MyDialogBox("Enter URL for screenshot:");
				    mDBox.center();
				    mDBox.show();			    				 			  	  				      
			}
		};
		
		//Command relative to File/Refresh item
		Command refresh = new Command(){
			public void execute(){
				onModuleLoad();
			}
		};
		
		//Command relative to Metric/Balance item
		Command balance = new Command(){
			public void execute(){
				Balance balance = new Balance(rectXY, frameWidth, frameHeight);
			}
		};
		
		//Command relative to Metric/Alignment item
		Command alignment = new Command(){
			public void execute(){
				Window.alert("Alignment");
			}
		};

		//Command relative to Metric/Density item
		Command density = new Command(){
			public void execute(){
				Density density = new Density(rectXY, frameWidth, frameHeight);
			}
		};
		
		//RootPanel AttachHandler (unknown purpose for now)
    	RootPanel rootPanel = RootPanel.get();
    	rootPanel.setStyleName("rootPanel");
    	rootPanel.setSize("800px", "740px");
    	rootPanel.addAttachHandler(new Handler() {
    		public void onAttachOrDetach(AttachEvent event) {
    		}
    	});
    	
    	//MenuBar created and added to RootPanel
    	MenuBar menuBar = new MenuBar(false);
    	rootPanel.add(menuBar, 0, 0);
    	menuBar.setSize("968px", "32px");
    	MenuBar menuBar_1 = new MenuBar(true);
    	
    	//Item "File" in MenuBar
    	MenuItem mntmFile = new MenuItem("File", false, menuBar_1);
    	//Item "File/New" in MenuBar
    	MenuItem mntmNew = new MenuItem("New", false, (Command) new1);
    	menuBar_1.addItem(mntmNew);
    	//Item "File/Detection" in MenuBar
    	MenuItem mntmDetection = new MenuItem("Refresh", false, (Command) refresh);
    	menuBar_1.addItem(mntmDetection);
    	menuBar.addItem(mntmFile);
    	MenuBar menuBar_2 = new MenuBar(true);
    	
    	//Item "Metrics" in MenuBar
    	MenuItem mntmMetrics = new MenuItem("Metrics", false, menuBar_2);
    	//Item "Metrics/Balance" in MenuBar
    	MenuItem mntmBalance = new MenuItem("Balance", false, (Command) balance);
    	menuBar_2.addItem(mntmBalance);
    	//Item "Metrics/Density" in MenuBar
    	MenuItem mntmDensity = new MenuItem("Density", false, (Command) density);
    	menuBar_2.addItem(mntmDensity);
    	//Item "Metrics/Alignment" in MenuBar
    	MenuItem mntmAlignment = new MenuItem("Alignment", false, (Command) alignment);
    	menuBar_2.addItem(mntmAlignment);
    	menuBar.addItem(mntmMetrics);
    	 
    	//Canvas and Context creation
    	final Canvas myCanvas = Canvas.createIfSupported();
    	myCanvas.setStyleName("canvas");
    	myCanvas.setCoordinateSpaceWidth(640);
    	myCanvas.setCoordinateSpaceHeight(480);
    	final Context2d context = myCanvas.getContext2d();	     
    	
    	//Write coordinates of mouse position in labels and draw a rectangle
    	myCanvas.addMouseMoveHandler(new MouseMoveHandler(){
    		public void onMouseMove(MouseMoveEvent event){
    			
    				context.clearRect(0, 0, 640, 480);
    				
    				for(int i=1;i<rectXY.length;i++)
    				{
    					context.strokeRect(rectXY[i-1][0], rectXY[i-1][1], rectXY[i-1][2], rectXY[i-1][3]);
    					context.setFillStyle(rectCOL[i]);
    					context.fillRect(rectXY[i][0], rectXY[i][1], rectXY[i][2], rectXY[i][3]);
    					context.setGlobalAlpha(0.5);
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
        				rectXY[0][0] = x;
          	        	rectXY[0][1] = y;
        	        	rectXY[0][2] = event.getRelativeX(myCanvas.getCanvasElement())-x;				
        	        	rectXY[0][3] = event.getRelativeY(myCanvas.getCanvasElement())-y;
        	        	context.setFillStyle(rectCOL[0]);
    					context.fillRect(rectXY[0][0], rectXY[0][1], rectXY[0][2], rectXY[0][3]);
        			}   			
        	
    		}
    	});
    	   	
    	
    	//Change Boolean mouseDown to true if mouse left click is down
     	myCanvas.addMouseDownHandler(new MouseDownHandler(){
    		public void onMouseDown(MouseDownEvent e){
    			mouseDown = true;
    				}
    	});
    	
     	
    	
    	//add a frame to panel with specified url
    	Frame frame = new Frame(url);
    	rootPanel.add(frame, 50, 153);
    	frame.setSize(frameWidth*2+"px", frameHeight*2+"px");
    	
    	//add a canvas to panel on the preceding frame
    	RootPanel.get().add(myCanvas, 50, 153);
    	myCanvas.setSize(frameWidth+"px", frameHeight+"px");
    	
    	//add label x,y to the panel for mouse position
    	rootPanel.add(lblx, 355, 650); 
    	rootPanel.add(lbly, 395, 650);
    	
    	//add a panel above canvas (a sort of "toolpanel")
    	AbsolutePanel absolutePanel = new AbsolutePanel();
    	absolutePanel.setStyleName("gwt-horizontalPanel");
    	rootPanel.add(absolutePanel, 50, 68);
    	absolutePanel.setSize("640px", "75px");
    	    	
    	//add a grid with 6 color buttons from white to black (4 shades of grey)
    	Grid grid = new Grid(2, 3);
    	absolutePanel.add(grid, 20, 15);
    	grid.setSize("62px", "43px");
    	
    	//white button
    	Button btnColor = new Button("");
    	btnColor.addClickHandler(new ClickHandler() {
    		public void onClick(ClickEvent event) {
				context.clearRect(rectXY[0][0], rectXY[0][1], rectXY[0][2], rectXY[0][3]);
    			context.setFillStyle("#FEFDFD");
    			rectCOL[0] ="#FEFDFD";
    			context.fillRect(rectXY[0][0], rectXY[0][1], rectXY[0][2], rectXY[0][3]);
  
    		}
    	});
    	btnColor.setStyleName("gwt-ColorWhite");
    	grid.setWidget(0, 0, btnColor);
    	btnColor.setSize("14px", "16px");
    	
    	//grey1 button
    	Button button = new Button("");
    	button.addClickHandler(new ClickHandler() {
    		public void onClick(ClickEvent event) {
				context.clearRect(rectXY[0][0], rectXY[0][1], rectXY[0][2], rectXY[0][3]);
    			context.setFillStyle("#EAEAEA");
    			rectCOL[0] ="#EAEAEA";
    			context.fillRect(rectXY[0][0], rectXY[0][1], rectXY[0][2], rectXY[0][3]);
    		}
    	});
    	button.setStyleName("gwt-ColorWhite2");
    	grid.setWidget(0, 1, button);
    	button.setSize("14px", "16px");
    	
    	//grey2 button
    	Button button_3 = new Button("");
    	button_3.addClickHandler(new ClickHandler() {
    		public void onClick(ClickEvent event) {
				context.clearRect(rectXY[0][0], rectXY[0][1], rectXY[0][2], rectXY[0][3]);
    			context.setFillStyle("#CBCACA");
    			rectCOL[0] ="#CBCACA";
    			context.fillRect(rectXY[0][0], rectXY[0][1], rectXY[0][2], rectXY[0][3]);
    		}
    	});
    	button_3.setStyleName("gwt-ColorWhite3");
    	grid.setWidget(0, 2, button_3);
    	button_3.setSize("14px", "16px");
    	
    	//grey3 button
    	Button button_1 = new Button("");
    	button_1.addClickHandler(new ClickHandler() {
    		public void onClick(ClickEvent event) {
				context.clearRect(rectXY[0][0], rectXY[0][1], rectXY[0][2], rectXY[0][3]);
    			context.setFillStyle("#848484");
    			rectCOL[0] ="#848484";
    			context.fillRect(rectXY[0][0], rectXY[0][1], rectXY[0][2], rectXY[0][3]);
    		}
    	});
    	button_1.setStyleName("gwt-ColorBlack1");
    	grid.setWidget(1, 0, button_1);
    	button_1.setSize("14px", "16px");
    	
    	//grey4 button
    	Button button_2 = new Button("");
    	button_2.addClickHandler(new ClickHandler() {
    		public void onClick(ClickEvent event) {
				context.clearRect(rectXY[0][0], rectXY[0][1], rectXY[0][2], rectXY[0][3]);
    			context.setFillStyle("#494949");
    			rectCOL[0] ="#494949";
    			context.fillRect(rectXY[0][0], rectXY[0][1], rectXY[0][2], rectXY[0][3]);
    		}
    	});
    	button_2.setStyleName("gwt-ColorBlack2");
    	grid.setWidget(1, 1, button_2);
    	button_2.setSize("14px", "16px");
    	
    	//black button
    	Button button_4 = new Button("");
    	button_4.addClickHandler(new ClickHandler() {
    		public void onClick(ClickEvent event) {
				context.clearRect(rectXY[0][0], rectXY[0][1], rectXY[0][2], rectXY[0][3]);
    			context.setFillStyle("#000000");
    			rectCOL[0] ="#000000";
    			context.fillRect(rectXY[0][0], rectXY[0][1], rectXY[0][2], rectXY[0][3]);

    		}
    	});
    	button_4.setStyleName("gwt-ColorBlack3");
    	grid.setWidget(1, 2, button_4);
    	button_4.setSize("14px", "16px");
    	    	
    	//add a right-side panel to write coordinates, width and height of each drawn rectangle
    	AbsolutePanel absolutePanel_1 = new AbsolutePanel();
    	rootPanel.add(absolutePanel_1, 700, 68);
    	absolutePanel_1.setStyleName("panellat");
    	absolutePanel_1.setSize("164px", "565px");
    	
    	//add a VerticalPanel in this right-side panel 
    	final VerticalPanel verticalPanel = new VerticalPanel();
    	absolutePanel_1.add(verticalPanel, 10, 10);
    	verticalPanel.setSize("144px", "20px");
    	
    	//add title "Saved datas" to the right-side panel
    	final Label lblNewLabel = new Label("Objects");
    	lblNewLabel.setDirectionEstimator(false);
    	verticalPanel.add(lblNewLabel);
    	verticalPanel.setCellHorizontalAlignment(lblNewLabel, HasHorizontalAlignment.ALIGN_CENTER);
    	
    	//add a button to clear panel in "toolpanel"
    	Button btnNewButton = new Button("Clear Panel");
    	btnNewButton.addClickHandler(new ClickHandler() {
    		public void onClick(ClickEvent event) {
    			context.clearRect(0,0,800,740);
				for(int i=0;i<rectXY.length;i++)
				{
					rectXY[i][0] = 0;
					rectXY[i][1] = 0;
					rectXY[i][2] = 0;
					rectXY[i][3] = 0;
					verticalPanel.clear();
					verticalPanel.add(lblNewLabel);
					verticalPanel.setCellHorizontalAlignment(lblNewLabel, HasHorizontalAlignment.ALIGN_CENTER);
					clear = true;
					
				}
    		}
    	});
    	absolutePanel.add(btnNewButton, 90, 15);
    	btnNewButton.setSize("89px", "50px");
    	
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
    			
    			i++;
    			
    			rectXY[i][0] = rectXY[0][0];
    			rectXY[i][1] = rectXY[0][1];
    			rectXY[i][2] = rectXY[0][2];
    			rectXY[i][3] = rectXY[0][3];
    			rectCOL[i] = rectCOL[0];
     			
    			final VerticalPanel verticalPanel2 = new VerticalPanel();
    			verticalPanel2.setStyleName("object");
    	    	verticalPanel.add(verticalPanel2);
    	    	
    	    	final HorizontalPanel horizontalPanel = new HorizontalPanel();
    	    	verticalPanel2.add(horizontalPanel);
    	    	
    	    	final VerticalPanel verticalPanel3 = new VerticalPanel();
    	    	horizontalPanel.add(verticalPanel3);
    	    	verticalPanel3.setStyleName("LabelObject");
    	    			
    			Label lblRectangle = new Label("Rectangle"+i);
    	    	verticalPanel3.add(lblRectangle);
    	    	
    	    	
    	    	Label lblxy = new Label("("+rectXY[i][0]+":"+rectXY[i][1]+")");
    	    	verticalPanel3.add(lblxy);
    	    	
    	    	
    	    	Label lblwh = new Label("w: "+rectXY[i][2]+" h: "+rectXY[i][3]);
    	    	verticalPanel3.add(lblwh);
    	    	
    	    
    	    	final Button delete = new Button("X");
    	    	delete.setStyleName("delete");
    	    	delete.setTabIndex(i);
    	    	horizontalPanel.add(delete);
    	    	delete.addClickHandler(new ClickHandler() {
    	    		public void onClick(ClickEvent event) {
    	    			Widget t = (delete.getParent()).getParent();
    	    			t.removeFromParent();
    	    			context.clearRect(rectXY[delete.getTabIndex()][0]-1, rectXY[delete.getTabIndex()][1]-1, rectXY[delete.getTabIndex()][2]+2, rectXY[delete.getTabIndex()][3]+2);
    	    			i=delete.getTabIndex();
    					rectXY[0][0] = 0;
    					rectXY[0][1] = 0;
    					rectXY[0][2] = 0;
    					rectXY[0][3] = 0;
    	    			rectXY[i][0] = 0;
    	    			rectXY[i][1] = 0;
    	    			rectXY[i][2] = 0;
    	    			rectXY[i][3] = 0;
    	    			rectCOL[i] = rectCOL[0];
    	    			i=delete.getTabIndex()-1;
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
}
