package com.tutorial.aaronpractice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class GetHttpEx {
	
	public String getInternetData() throws Exception{
		BufferedReader in = null;
		String data = "error getting data from the internet";
		URI website;
		try{
			HttpClient client = new DefaultHttpClient();
			website = new URI("http://www.aaronklapheck.com");
			HttpGet request = new HttpGet();
			request.setURI(website);
			HttpResponse response = client.execute(request);
			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer sb = new StringBuffer("");
			String l = "";
			String nL = System.getProperty("line.separator");
			while((l = in.readLine()) != null){
				sb.append(l + nL);
			}
			in.close();
			data = sb.toString();
			return data;
		} finally{
			if(in != null){
				try{
					in.close();
					return data;
				} catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
	}
}
