import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author arpit
 *
 */
public class SampleViewOrderStatus {

	public static void main (String[] args) {

		String function = "viewOrderStatus?orderID=GharpayOrderID" ;
		String username = "Your_API_Key";
		String password = "Your_API_Secret";
		String xmlMessage = null ;

		try{
			String response = restCall(function, username, password, xmlMessage) ;
			System.out.println("Response:" +response);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	public static String restCall(String function, String username, String password, String xmlMessage) throws Exception{

		if(username==null || password == null || username.trim().length()==0 || password.trim().length()==0){
			throw new Exception("Username/Password in rest services is null or empty") ;
		}
		String result = null ;

		/*
		 * Configure this to point to the appropriate URL. By default, has been assigned to the test portal
		 */
		String urlString = "http://services.gharpay.in/rest/GharpayService/"+function;

		String contentType = "application/xml";
		String charset = "UTF-8";

		URL url = null;
		URLConnection connection = null;
		OutputStream output = null;
		InputStream response = null;

		try{
			/*
			 * Configure the Connection object to sent the appropriate headers in the request
			 */
			url = new URL(urlString);
			connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Accept-Charset", charset);
			connection.setRequestProperty("Content-Type", contentType+";charset=" + charset);
			connection.setRequestProperty("username", username) ;
			connection.setRequestProperty("password", password) ;

			/*
			 * Create the output stream only when message needs to be sent in the request body
			 */
			if(xmlMessage !=null && xmlMessage.length()>0){
				output = connection.getOutputStream() ;
				output.write(xmlMessage.getBytes(charset));
			}

			/*
			 * Invoke the API call and read the response stream.
			 */
			response = connection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(response));
			StringBuilder sb = new StringBuilder() ;
			String line = null ;
			while((line = br.readLine())!=null){
				sb.append(line);
			}
			response.close() ;
			result = sb.toString() ;
		}catch(MalformedURLException e){
			e.printStackTrace();
			throw new Exception("Incorrect URL for Rest Services");
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
			throw new Exception("Unable to connect to Rest Services");
		}catch(IOException e){
			e.printStackTrace();
			throw new Exception("Unable to connect to Rest Services");
		}

		return result; 
	}
}
