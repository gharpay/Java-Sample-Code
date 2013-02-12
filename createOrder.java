/**
 * 
 */
package com.gharpay.tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.gharpay.exceptions.GharpayException;

/**
 * @author arpit
 *
 */
public class SampleCreateOrder {

    public static final String username = "xxxx" ; //Add your API Username or API Key here
    public static final String password = "xxxx" ; //Add your API Password or API Secret here
    
    public static void main(String[] args){
	
	String xmlMessage = "<transaction> <!--Optional:--> <additionalInformation> <!--1 or more repetitions:--> <parameters> <name>InvoiceURL</name> <value>http://testUrl</value> </parameters> <parameters> <name>TicketType</name> <value>E-Ticket</value> </parameters> </additionalInformation> <customerDetails> <address>xxxxxxx</address> <contactNo>9177781351</contactNo> <!--Optional:--> <email>xxx@gmail.com</email> <firstName>xxx</firstName> <!--Optional:--> <lastName>xxxxxxxx</lastName> <!--Optional:--> <prefix>Mr.</prefix> </customerDetails> <orderDetails> <pincode>500082</pincode> <clientOrderID>Order123</clientOrderID> <deliveryDate>09-12-2011</deliveryDate> <orderAmount>1000</orderAmount> <!--Zero or more repetitions:--> <productDetails> <productID>Prod123</productID> <productQuantity>1</productQuantity> <unitCost>1000</unitCost> </productDetails> <templateID>1</templateID> </orderDetails> </transaction>" ;
	    
	    if(username==null || password == null || username.trim().length()==0 || password.trim().length()==0){
		System.out.println("Username/Password in rest services is null or empty") ;
		}
		String result = null ;
		String function = "createOrder" ;
		String urlString = "http://services.gharpay.in/rest/GharpayService/"+function;

		String contentType = "application/xml";
		String charset = "UTF-8";

		URL url = null;
		URLConnection connection = null;
		OutputStream output = null;
		InputStream response = null;

		try{
		    url = new URL(urlString);
		    connection = url.openConnection();
		    connection.setDoOutput(true);
		    connection.setRequestProperty("Accept-Charset", charset);
		    connection.setRequestProperty("Content-Type", contentType+";charset=" + charset);
		    connection.setRequestProperty("username", username) ;
		    connection.setRequestProperty("password", password) ;
		    output = connection.getOutputStream() ;
		    output.write(xmlMessage.getBytes(charset));

		    response = connection.getInputStream();
		    BufferedReader br = new BufferedReader(new InputStreamReader(response));
		    StringBuilder sb = new StringBuilder() ;
		    String line = null ;
		    while((line = br.readLine())!=null){
			sb.append(line);
		    }
		    response.close() ;
		    result = sb.toString() ;
		    System.out.println("Result : \n"+result);
		}catch(MalformedURLException e){
		    System.out.println("Incorrect URL for Rest Services");
		}catch(UnsupportedEncodingException e){
		    System.out.println("Unable to connect to Rest Services");
		}catch(IOException e){
		    e.printStackTrace();
		    System.out.println("Unable to connect to Rest Services");
		}
    }
}
