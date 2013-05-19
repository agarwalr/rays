package com.rays.deployment.coreds;

public class SrcDestInfo 
{
	private String source = null;
	
	private String dest = null;
	
	public String getSource()
	{
		return source;
	}

	public String getDest()
	{
		return dest;
	}

	public SrcDestInfo(String src, String dest)
	{
		this.source = src;
		this.dest = dest;
	}

}
