package ssdlc.model;

import org.owasp.encoder.Encode;

public class LogModel {
	
	public static String log_sanitized(String info) {
		//Finish TODO Day2 實作Log消毒行為
		info = Encode.forJava(info);
		return info ;
	}
	
	public static void main(String[] args) {
		System.out.println("test\r\n");
		System.out.println(LogModel.log_sanitized("test\r\n"));
	}


}
