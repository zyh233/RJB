package cn.cumt.exception;

@SuppressWarnings("serial")
public class UpdateFailException extends Exception{
	public UpdateFailException(String msg){
		super(msg);
	}
}
