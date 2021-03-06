package be.lilab.questim.client;

import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.MultiUploader;
import gwtupload.client.PreloadedImage;
import gwtupload.client.PreloadedImage.OnLoadPreloadedImageHandler;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Index implements EntryPoint {

	FlowPanel input = new FlowPanel();

	Label title = new Label("QUESTIM");
	Label title2 = new Label("Quality Estimator Tool using Metrics");
	final TextBox textBox = new TextBox();
	Button okButton = new Button("Continue");
	final FlowPanel panelImages = new FlowPanel();
	String url;
	boolean imageLoaded = false;
	String fileUrl;

	public void onModuleLoad() {

		okButton.setStyleName("okButton");
		textBox.setStyleName("textBox");
		title.setStyleName("h1");
		title2.setStyleName("h2");
		panelImages.setStyleName("image");

		textBox.addKeyDownHandler(new KeyDownHandler(){
			@Override
			public void onKeyDown(KeyDownEvent event){
				if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER){
					next();
				}
			}
		
		});

		okButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				next();
			}
		});

		VerticalPanel titlePanel = new VerticalPanel();
		titlePanel.setStyleName("titlePanel");
		titlePanel.add(title);
		titlePanel.add(title2);

		VerticalPanel vPanel = new VerticalPanel();
		Label labelurl = new Label("Enter URL:");
		labelurl.setStyleName("labelurl");
		vPanel.add(labelurl);
		textBox.setValue("http://");
		vPanel.add(textBox);
		Label labelor = new Label("or");
		labelor.setStyleName("labelor");
		vPanel.add(labelor);
		Label labelsc = new Label("Upload a screenshot:");
		labelsc.setStyleName("labelsc");
		vPanel.add(labelsc);
		MultiUploader defaultUploader = new MultiUploader();
		defaultUploader.setStyleName("defaultUploader");
		vPanel.add(defaultUploader);
		defaultUploader.addOnFinishUploadHandler(onFinishUploaderHandler);
		vPanel.add(panelImages);
		vPanel.add(okButton);
		vPanel.setCellHorizontalAlignment(okButton,
				HasHorizontalAlignment.ALIGN_CENTER);

		input.add(vPanel);
		input.setStyleName("input");
		RootPanel.get().add(titlePanel);
		RootPanel.get().add(input);

	}

	IUploader.OnFinishUploaderHandler onFinishUploaderHandler = new IUploader.OnFinishUploaderHandler() {
		public void onFinish(IUploader uploader) {
			if (uploader.getStatus() == Status.SUCCESS) {

				new PreloadedImage(uploader.fileUrl(), showImage);
				fileUrl = uploader.fileUrl();

				imageLoaded = true;

			}
		}
	};

	// Attach an image to the pictures viewer
	OnLoadPreloadedImageHandler showImage = new OnLoadPreloadedImageHandler() {
		public void onLoad(PreloadedImage image) {
			image.setWidth("250px");
			panelImages.add(image);
		}
	};

	public void next() {
		// url receive the url entered by user
		url = textBox.getText();
		// change url variable in main class
		Main.setUrl(url);
		if (url.length() != 0 && url != "http://") {
			imageLoaded = false;
			Window.Location.replace("/AesthetiXYG2.html?url=" + url);
		}

		else {
			Window.Location.replace("/AesthetiXYG2.html?fileUrl=" + fileUrl
					+ "&imageLoaded=true");
		}
	}

}
