package com.yk.pojo;

public class Message<T> {
	private int errno;
	private String msg;
	private T t;

	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	public Message() {
		super();
	}
	public int getErrno() {
		return errno;
	}
	public void setErrno(int errno) {
		this.errno = errno;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Message(int errno, String msg, T t) {
		super();
		this.errno = errno;
		this.msg = msg;
		this.t = t;
	}
	@Override
	public String toString() {
		return "Message [errno=" + errno + ", msg=" + msg + ", t=" + t + "]";
	}

	
}
