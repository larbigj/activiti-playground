package net.larbig.vaadin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.vaadin.Application;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Select;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ActivitiCamelVaadinApplication extends Application {
	@Override
	public void init() {
		Window mainWindow = new Window("ActivitiCamelVaadinApplication");
		
		final String[] procIds = new String[]{};
		
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		
		final Button btnStart = new Button("Start Process");
		final Select cbProcs = new Select("ProcessID");
		final Button btnReceive = new Button("Wake up Process");
		
		
		btnStart.addListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					URL url = new URL("http://localhost:8080/camel/start");
		            URLConnection conn = url.openConnection();
		            conn.setDoOutput(true);
		            BufferedReader in = new BufferedReader( new InputStreamReader( conn.getInputStream() ) );	            
		            String response;
		            while ( (response = in.readLine()) != null ) {
		            	cbProcs.addItem(response);
//		                System.out.println( response );
		            }
		            in.close();
					
					
					
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		
		
		
		HorizontalLayout vl = new HorizontalLayout();
		
		btnReceive.addListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				String pid = (String)cbProcs.getValue();
				try {
					URL url = new URL("http://localhost:8080/camel/receive?laboprocid="+pid);
					URLConnection conn = url.openConnection();
					conn.setDoOutput(true);
					conn.getInputStream();
					cbProcs.removeItem(pid);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		vl.addComponent(cbProcs);
		vl.addComponent(btnReceive);
		vl.setComponentAlignment(cbProcs, Alignment.BOTTOM_CENTER);
		vl.setComponentAlignment(btnReceive, Alignment.BOTTOM_CENTER);
		
		
		
		layout.addComponent(btnStart);
		layout.addComponent(vl);

		
		
		
		
		
		
		
		mainWindow.addComponent(layout);
		setMainWindow(mainWindow);
	}

}
