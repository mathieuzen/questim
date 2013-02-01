package aesthetixy.appspot.com.server;

import aesthetixy.appspot.com.client.MyMetricsReport;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class MyMetricsReportImpl extends RemoteServiceServlet implements MyMetricsReport{

	public String myMethod() {
		String s = "test";
		return s;
	}

}
