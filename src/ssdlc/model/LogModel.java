package ssdlc.model;

import org.owasp.encoder.Encode;

public class LogModel {
	
	public static String log_sanitized(String info) {
		return Encode.forJava(info);
	}


}
