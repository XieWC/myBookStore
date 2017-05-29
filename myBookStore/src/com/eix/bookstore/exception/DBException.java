package com.eix.bookstore.exception;

public class DBException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
    public DBException(){
    	
    }
    public DBException(String str){
    	super(str);
    }
    
}
