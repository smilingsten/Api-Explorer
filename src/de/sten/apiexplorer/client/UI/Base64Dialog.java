package de.sten.apiexplorer.client.UI;


import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.sten.apiexplorer.client.Logic.Base64Tool;

public class Base64Dialog extends DialogBox{

	private FlowPanel dialogrootpnl;
	private TextArea encode;
	private TextArea decode;
	private Button close;
	VerticalPanel vpnl;
	private Base64Dialog me;

	public Base64Dialog() {
		me = this;
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
		vpnl.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		vpnl.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		vpnl.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		vpnl.addStyleName("b64dialogVpnl");
		encode.addStyleName("b64box");
		encode.setVisibleLines(6);
		decode.setVisibleLines(6);
		decode.addStyleName("b64box");
		close.addStyleName("dialogclosebtn");
	}

	private void createElements() {
		dialogrootpnl = new FlowPanel();
		encode = new TextArea();
		decode = new TextArea();
		close = new Button("Close");
		vpnl = new VerticalPanel();
		
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
		close.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				me.hide();
				
			}
		});
		
	}
	

	private void assembleDialog() {

		vpnl.add(new HTML("Enter plain text here to encode to base64:"));
		vpnl.add(encode);
		vpnl.add(new HTML("Your base64 encoded text:"));
		vpnl.add(decode);
		dialogrootpnl.add(vpnl);
		dialogrootpnl.add(close);
		this.add(dialogrootpnl);

	}

	private void encode() {
		decode.setText(Base64Tool.encodeString(encode.getText()));

	}

}
