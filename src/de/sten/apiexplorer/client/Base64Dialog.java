package de.sten.apiexplorer.client;


import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Base64Dialog extends DialogBox{

	private TextArea encode;
	private TextArea decode;
	VerticalPanel vpnl;

	public Base64Dialog() {
		createElements();
		applyStyles();
		addHandlers();
		assembleDialog();
		

	}

	private void applyStyles() {
		this.setText("Base64 En/Decoder");
		this.addStyleName("b64dialog");
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		encode.addStyleName("b64box");
		encode.setVisibleLines(6);
		decode.setVisibleLines(6);
		decode.addStyleName("b64box");
	}

	private void createElements() {
		encode = new TextArea();
		decode = new TextArea();
		vpnl = new VerticalPanel();
		vpnl.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		vpnl.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		vpnl.addStyleName("b64dialogVpnl");
	}

	private void addHandlers() {
		encode.addChangeHandler(new ChangeHandler() {
			
			public void onChange(ChangeEvent event) {
				encode();				
			}
		});
		encode.addKeyUpHandler(new KeyUpHandler() {
			
			public void onKeyUp(KeyUpEvent event) {
				encode();				
			}
		});
		encode.addMouseDownHandler(new MouseDownHandler() {
			
			public void onMouseDown(MouseDownEvent event) {
				encode();				
			}
		});
		
	}
	

	private void assembleDialog() {

		vpnl.add(new HTML("Enter plain text here to encode to base64:"));
		vpnl.add(encode);
		vpnl.add(new HTML("Your base64 encoded text:"));
		vpnl.add(decode);
		this.add(vpnl);

	}

	private void encode() {
		decode.setText(Base64Tool.encodeString(encode.getText()));

	}

}
