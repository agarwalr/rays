package com.rays.deployment.utils;

import java.util.Stack;

class Message
{
	private int code;
	
	private String message;

	private String detailedDesc;
	
	public Message(int code, String message, String detailedDesc)
	{
		this.code = code;
		this.message = message;
		this.detailedDesc = detailedDesc;
	}
	
	
	public String getMessage()
	{
		return this.message;
	}
	
	@Override
	public String toString()
	{
		return this.code + "-" + this.message + "-" + this.detailedDesc;
	}
}
public class MessageController 
{
	private static Stack<Message> infoStack = new Stack<Message>();
	
	private static Stack<Message> errorStack = new Stack<Message>();
	
	public static void pushInfoMessage(int code, String message, String detailedDesc)
	{
		infoStack.add(new Message(code, message, detailedDesc));
	}
	
	public static String peekInfoMessage()
	{
		int size = infoStack.size();
		if(size > 0)
			return infoStack.peek().getMessage();
		return null;
	}
	
	public static String popInfoMessage()
	{
		int size = infoStack.size();
		if(size > 0)
			return infoStack.pop().getMessage();
		return null;
	}
	
	public static String peepDetailedMessage()
	{
		int size = infoStack.size();
		if(size > 0)
			return infoStack.pop().toString();
		return null;
	}

}
