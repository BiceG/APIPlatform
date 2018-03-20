package com.yisquare.tools;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;

import org.json.*;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class Util {
	public static String pathString = "";

	/**
	 * ����sql�������ȡ��ѯ�ļ�¼��
	 * 
	 * @param sql
	 * @return
	 */
	public static int getIdNum(String idJson) {
		String dbCount = Util.Regex("ID\":(.*)}]", idJson, 1);
		return Integer.parseInt(dbCount);
	}

	/**
	 * ������ʽ ����pat���� ����ȡstr��������Ҫ�����ݻ�ȡ����group��i��
	 * 
	 * @param pat
	 * @param str
	 * @param i
	 * @return
	 */
	public static String Regex(String pat, String str, int i) {
		Pattern pattern = Pattern.compile(pat);// �����ҵĹ���
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			return matcher.group(i);
		}
		return null;
	}

	public static String getNowFormat() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// �������ڸ�ʽ
		return df.format(new Date());
	}

	// public static void main(String as[]){
	// // System.out.println(getIdNum("[{\"ID\":41}]"));
	// // System.out.println(getNowFormat());
	// System.out.println(isOSLinux());
	//
	// }

	public static boolean isOSLinux() {
		Properties prop = System.getProperties();
		String os = prop.getProperty("os.name");
		if (os != null && os.toLowerCase().indexOf("linux") > -1) {
			return true;
		} else {
			return false;
		}
	}

	public static String isOSLinux(ServletContext servletContext) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void writeDBInfo(String str, String path) {
		// TODO Auto-generated constructor stub
		System.out.println("��Ҫ��ӡ����" + str);
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");// �������ڸ�ʽ
			Writer writer = new FileWriter(path + "/log/log.html", true);// true��ʾ
																			// ������
			BufferedWriter br = new BufferedWriter(writer);

			br.write("<font color='red'>" + df.format(new Date())
					+ "</font></br>");
			br.write(str);
			br.write("</br>");
			br.write("</br>");
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getInfo(String key) {
		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream("ipConfig.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return p.getProperty("ip");
	}

	public void setInfo(String key, String value) {
		try {
			FileWriter writer = new FileWriter("ipConfig.properties");
			Properties p = new Properties();
			p.setProperty(key, value);
			p.store(writer, "������Ϣ");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void main(String as[]) {
		// Util util = new Util();
		// util.setInfo("ip", "123");

		String json = "[{\"ServiceID\":\"1\"}],[{\"ServiceID\":\"2\"}],[{\"ServiceID\":\"3\"}],[{\"ServiceID\":\"4\"}]";
		JSONArray jsonArray = new JSONArray(json);
		for (int i = 0; i < jsonArray.length(); i++) {
			System.out.println(JSONObject.getNames(jsonArray.get(i)));
		}

	}

	public static String[] jsonToStringList(String json) {

		JsonElement je = new Gson().toJsonTree(json);
		int size = je.getAsJsonArray().size();
		String[] list = new String[size];
		for (int i = 0; i < size; i++) {
			list[i] = je.getAsJsonArray().get(i).getAsString();
		}
		return list;
	}
}
