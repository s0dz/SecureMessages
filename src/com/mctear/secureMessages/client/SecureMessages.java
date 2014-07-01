package com.mctear.secureMessages.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.http.client.URL;
import com.google.gwt.jsonp.client.JsonpRequestBuilder;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SecureMessages implements EntryPoint {
	
	private static final int REFRESH_INTERVAL = 5000; // milliseconds

	private static final String JSON_URL = "https://localhost/prm/rest/subscribers/2/secureMsgs/messagethreads/";
	
	// Widgets!
	private VerticalPanel mainPanel = new VerticalPanel();
	private FlexTable secureMessagesFlexTable = new FlexTable();
	private Label lastUpdatedLabel = new Label();
	private Label errorMsgLabel = new Label();
	
	// Data structure for holding stocks
	private ArrayList<Integer> msgIdxs = new ArrayList<Integer>();
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		// Create table for secure messages
		secureMessagesFlexTable.setText( 0, 0, "From" );
		secureMessagesFlexTable.setText( 0, 1, "Message" );
		
		// Assemble Main panel.
		mainPanel.add( errorMsgLabel );
		mainPanel.add( secureMessagesFlexTable );
		mainPanel.add( lastUpdatedLabel );
		
		// Associate the Main panel with the HTML host page.
		RootPanel.get( "secureMessageList" ).add( mainPanel );
		
		// Setup timer to refresh list automatically.
		Timer refreshTimer = new Timer() {
			@Override
			public void run() {
				refreshSecureMessageList();
			}
		};
		refreshTimer.scheduleRepeating( REFRESH_INTERVAL );
	}
	
	/**
	 * Get secure messages.
	 */
	private void refreshSecureMessageList() {
		
		int apiUserIdx = 1;
		
		String url = JSON_URL + apiUserIdx;
		
		url = URL.encode( url );
		
		JsonpRequestBuilder builder = new JsonpRequestBuilder();
		builder.requestString( url, new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {
		        displayError("Couldn't retrieve JSON", caught);
		      }
	
			@Override
			public void onSuccess(String result) {
				if( result == null ) {
					displayError( "Couldn't retrieve JSON: result was null!" );
				}
				
				updateTable( result );
			}
	    });
		
	}	
	
	private void updateTable( String message ) {
		
		secureMessagesFlexTable.setText( 0, 0, message );
	}
	
	private void displayError( String error ) {
		errorMsgLabel.setText( "Error: " + error );
		errorMsgLabel.setVisible( true );
	}
	
	private void displayError( String error, Exception e ) {
		errorMsgLabel.setText( "Error: " + error + e.toString() );
		errorMsgLabel.setVisible( true );
	}
	
	private void displayError( String error, Throwable e ) {
		errorMsgLabel.setText( "Error: " + error + e.toString() );
		errorMsgLabel.setVisible( true );
	}
}
