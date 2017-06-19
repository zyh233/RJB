package cn.cumt.exception;

@SuppressWarnings("serial")
public class UserRepeatException extends Exception{
	
	public UserRepeatException(String msg){
		super(msg);
	}
}
