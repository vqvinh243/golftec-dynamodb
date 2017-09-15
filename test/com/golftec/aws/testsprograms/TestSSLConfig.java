package com.golftec.aws.testsprograms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * To test whether your development or production environment is compatible with SHA256-signed certificates,
 *  write code that performs an HTTPS GET to https://www.amazonsha256.com/. If the TLS handshake succeeds, 
 *  then your Java environment is fine, and there's no need to update it.

 * Amazon has provided a set of scripts for Java, JavaScript, PHP, Python and Ruby 
 * that you can use to test your installation of these languages. 
 * For other programming languages, consult your language's documentation for information about how to access websites using HTTPS.
 * 
 * @author Al Wells
 *
 */

public class TestSSLConfig {
	
	public void performHttpsGet() {
		 URL url;
	     HttpsURLConnection conn;
	     BufferedReader rd;
	     String line;
	     StringBuilder result = new StringBuilder();
	      try {
	         url = new URL("https://www.amazonsha256.com/");
	         conn = (HttpsURLConnection) url.openConnection();
	         conn.setRequestMethod("GET");
	         rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	         while ((line = rd.readLine()) != null) {
	            result.append(line + "\n");
	         }
	         rd.close();
	      } 
	      catch (Exception e) {
	         e.printStackTrace();
	      }
	      System.out.println(result.toString());
	}
	
	public static void main(String[] args) {
		TestSSLConfig tsc = new TestSSLConfig();
		tsc.performHttpsGet();
	}
	
	

}
