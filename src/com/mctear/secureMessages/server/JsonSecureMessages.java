package com.mctear.secureMessages.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

/**
 * This does the same pseudo calculation of stock prices as the client side Javascript
 * but instead on the server side using this servlet.
 * 
 * Returns a JSON payload.
 * 
 * Example usage: http://localhost:8888/stockwatcher/stockPrices?q=ABC+DEF
 * 
 * @author gmctear
 *
 */
public class JsonSecureMessages extends HttpServlet {

	private static final long serialVersionUID = 8491800536020050397L;

	private static final String JSON_URL = "https://localhost/prm/rest/subscribers/2/secureMsgs/messagethreads/";
	
	@Override
	protected void doGet( HttpServletRequest request, HttpServletResponse response )
			throws ServletException, IOException {
		
		
		PrintWriter out = response.getWriter();
		
		int apiUserIdx = 1;
		
		String url = JSON_URL + apiUserIdx;
		
		URL obj = new URL( url );
		
		HttpClient client = new HttpClient();
		client.
		
	    HttpGet httpGet = new HttpGet(url);
	    
	    httpGet.setHeader( "Username")
		
		
		
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod( "GET" );
		
		BufferedReader in = new BufferedReader( new InputStreamReader( con.getInputStream() ) );
		
		String inputLine;
		StringBuffer responseString = new StringBuffer();
		
		while ((inputLine = in.readLine()) != null) {
			responseString.append(inputLine);
		}
		in.close();
		
		out.print( response.toString() );
		out.flush();
	}
}
