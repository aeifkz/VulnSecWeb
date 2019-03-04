package ssdlc.model;

import org.owasp.encoder.Encode;

public class LogModel {
	
	public static String log_sanitized(String info) {
		//finish TODO Day3 實作Log消毒行為
		return Encode.forJava(info);
	}


}
