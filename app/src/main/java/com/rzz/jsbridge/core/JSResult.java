package com.rzz.jsbridge.core;

public class JSResult {
	public final static int SUCCESS = 200;
	public final static int ERROR = 500;
	
    private int code;
    private Object data;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
    
    
}
