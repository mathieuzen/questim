package aesthetixy.appspot.com.client;

import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.MultiUploader;
import gwtupload.client.PreloadedImage;
import gwtupload.client.PreloadedImage.OnLoadPreloadedImageHandler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MyDialogBox extends DialogBox {
    
	//textbox + okButton + url declaration
	private TextBox textBox = new TextBox();
    private Button okButton = new Button("Ok");
    private FlowPanel panelImages = new FlowPanel();
    public String url;
    static boolean imageLoaded = false;
    static String fileUrl;

    public MyDialogBox(String text) {
        super();
        
        //setText "Enter URL for screenshot:"
        setText(text);
        
        okButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                hide();
                //url receive the url entered by user
                url = textBox.getText();
                //change url variable in main class (API url -> size max= 200 :( )
                AesthetiXYG2.url=url;
                if(url.length()!=0){
                	imageLoaded = false;
                }  
          }
        });
       

        
        //DialogBox creation
        VerticalPanel vPanel = new VerticalPanel();
        Label labelurl = new Label("Enter URL:");
        vPanel.add(labelurl);
        vPanel.add(textBox);
        Label labelor = new Label("or");
        vPanel.add(labelor);
        Label labelsc = new Label("Upload a screenshot:");
        vPanel.add(labelsc);
        MultiUploader defaultUploader = new MultiUploader();
        vPanel.add(defaultUploader);
        defaultUploader.addOnFinishUploadHandler(onFinishUploaderHandler);
        vPanel.add(panelImages);
        vPanel.add(okButton);
        setWidget(vPanel);             
    }
    
    private IUploader.OnFinishUploaderHandler onFinishUploaderHandler = new IUploader.OnFinishUploaderHandler() {
        public void onFinish(IUploader uploader) {
          if (uploader.getStatus() == Status.SUCCESS) {

            new PreloadedImage(uploader.fileUrl(), showImage);
            fileUrl = uploader.fileUrl();
            
            imageLoaded = true;
            
           /*//The server sends useful information to the client by default
            UploadedInfo info = uploader.getServerInfo();
            Window.alert("File name " + info.name);
            Window.alert("File content-type " + info.ctype);
            Window.alert("File size " + info.size);*/
            

          }
        }
      };

      
      // Attach an image to the pictures viewer
      private OnLoadPreloadedImageHandler showImage = new OnLoadPreloadedImageHandler() {
        public void onLoad(PreloadedImage image) {
          image.setWidth("300px");
          image.setHeight("200px");
          panelImages.add(image);
        }
      };

}
