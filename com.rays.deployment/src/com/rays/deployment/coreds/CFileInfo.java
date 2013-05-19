package com.rays.deployment.coreds;

import java.io.File;

public class CFileInfo implements IFileInfo 
{
	private File file = null;
	
	public CFileInfo(File file)
	{
		this.file = file;
	}

	@Override
	public String getFileName() 
	{
		return this.file.getName();
	}

	@Override
	public String getFileSize() 
	{
		return new Long(this.file.length()).toString();
	}

	@Override
	public File getFile()
	{
		return this.file;
	}

}
