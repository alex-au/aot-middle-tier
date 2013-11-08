package com.aot.aotmiddletier.gateway;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class IcomGateway {
	
	private final Logger log = Logger.getLogger(this.getClass());
	
	private String userAgent;
	
	// iCom URL
	private String url;
	
	private String user;
	
	private String password;
	
	/**
	 * Send Request to iCom
	 * @param type
	 * @param requestBody
	 * @param recordTag
	 * @return String iCom Response
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public String sendRequest(String type, String requestBody) throws ClientProtocolException, IOException {

        HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		StringBuffer reqBuffer = new StringBuffer();
		reqBuffer.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n");
        reqBuffer.append("<!DOCTYPE Request SYSTEM \"productConnect_2_77_000.dtd\">\n");
        reqBuffer.append("<Request>\n");
        reqBuffer.append("  <" + type + ">\n");
        reqBuffer.append(requestBody);
        reqBuffer.append("  </" + type + ">\n");
        reqBuffer.append("</Request>\n");
 
		// add header
		post.setHeader("User-Agent", userAgent);
		
		HttpEntity entity = new StringEntity(reqBuffer.toString());
		
		log.info("iCom " + type + " request:\n" + reqBuffer.toString());
		
		post.setEntity(entity);
		
		HttpResponse response = client.execute(post);
		
		String strRsp = EntityUtils.toString(response.getEntity());

		log.debug("iCom " + type + " response:\n" + strRsp);
		
		return strRsp;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
