package com.datayes.webspider.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.datayes.webspider.util.json.Connection;
import com.datayes.webspider.util.json.Pool;

public class HttpUtil {
	
	private static String port = null;
	
	private static void loadPort(){
		if(StringUtils.isEmpty(port)){
			port = ApplicationUtil.get("url.port");
		}
	}
	
	@SuppressWarnings("finally")
	public static String getPoolString(String host){
		loadPort();
		String result = null;
		String targetURL = "http://" + host + ":"+port+"/getCrawlConnectionPoolList.do";
		URL url;
		HttpURLConnection connection = null;
		try {
			// Create connection
			url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			connection.setRequestProperty("Content-Length",
					"" + Integer.toString("".getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setConnectTimeout(3000);
			connection.setReadTimeout(3000);

			// Send request
			DataOutputStream wr = new DataOutputStream(
					connection.getOutputStream());
			wr.writeBytes("");
			wr.flush();
			wr.close();

			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			result = response.toString();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			return result;
		}
	}
	
	@SuppressWarnings("finally")
	public static Pool getPool(String host){
		loadPort();
		Pool pool = new Pool();
		String targetURL = "http://" + host + ":"+port+"/getCrawlConnectionPoolList.do";
		URL url;
		HttpURLConnection connection = null;
		try {
			// Create connection
			url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			connection.setRequestProperty("Content-Length",
					"" + Integer.toString("".getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setConnectTimeout(3000);
			connection.setReadTimeout(3000);
			
			// Send request
			DataOutputStream wr = new DataOutputStream(
					connection.getOutputStream());
			wr.writeBytes("");
			wr.flush();
			wr.close();

			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			pool = (Pool)JSONObject.parseObject(response.toString(), Pool.class);

		} catch (Exception e) {
			pool = null;
			e.printStackTrace();
		} finally {

			if (connection != null) {
				connection.disconnect();
			}
			return pool;
		}
	}
	
	public static String getConnectionString(String host, int id){
		loadPort();
		String result = null;
		String targetURL = "http://" + host + ":"+port+"/getCrawlConnectionPoolById.do?id=" + id;
		URL url;
		HttpURLConnection connection = null;
		try {
			// Create connection
			url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

//			connection.setRequestProperty("Content-Length",
//					"" + Integer.toString("".getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setConnectTimeout(3000);
			connection.setReadTimeout(3000);

			// Send request
			DataOutputStream wr = new DataOutputStream(
					connection.getOutputStream());
			wr.writeBytes("");
			wr.flush();
			wr.close();

			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			result = response.toString();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			return result;
		}
	}
	
	public static Connection getConnection(String host, int id){
		loadPort();
		Connection conn = new Connection();
		String targetURL = "http://" + host + ":"+port+"/getCrawlConnectionPoolById.do?id=" + id;
		URL url;
		HttpURLConnection connection = null;
		try {
			// Create connection
			url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

//			connection.setRequestProperty("Content-Length",
//					"" + Integer.toString("".getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setConnectTimeout(3000);
			connection.setReadTimeout(3000);

			// Send request
			DataOutputStream wr = new DataOutputStream(
					connection.getOutputStream());
			wr.writeBytes("");
			wr.flush();
			wr.close();

			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			conn = (Connection)JSONObject.parseObject(response.toString(), Connection.class);

		} catch (Exception e) {
			conn = null;
			e.printStackTrace();

		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			return conn;
		}
	}
	
	public static String excutePost(String targetURL, String urlParameters) {
		URL url;
		HttpURLConnection connection = null;
		try {
			// Create connection
			url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			connection.setRequestProperty("Content-Length",
					"" + Integer.toString(urlParameters.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			// Send request
			DataOutputStream wr = new DataOutputStream(
					connection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return response.toString();

		} catch (Exception e) {

			e.printStackTrace();
			return null;

		} finally {

			if (connection != null) {
				connection.disconnect();
			}
		}
	}
	
	public static void main(String[] args) {
		
//		Connection connection = getConnection("10.21.233.64", 43);
		System.out.println(getPool("10.20.66.38"));//("10.20.66.38", 43));
//		System.out.println(connection);
	}
}
