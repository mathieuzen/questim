package aesthetixy.appspot.com.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class Index implements EntryPoint{

	public void onModuleLoad() {
		final MyDialogBox mDBox = new MyDialogBox("New Input:");
		RootPanel.get().add(mDBox, 270, 210);
	}
}
