package aesthetixy.appspot.com.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MyDialogBox extends DialogBox {
    
	//textbox + okButton + url declaration
	private TextBox textBox = new TextBox();
    private Button okButton = new Button("Ok");
    public String url;

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
          }
        });
        
        //DialogBox creation
        VerticalPanel vPanel = new VerticalPanel();
        vPanel.add(textBox);
        vPanel.add(okButton);
        setWidget(vPanel);
    }
}
