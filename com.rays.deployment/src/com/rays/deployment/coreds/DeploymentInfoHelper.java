package com.rays.deployment.coreds;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Vector;

class NameFilter implements FilenameFilter
{
	private Vector<String> srcFileNames = null;
	
	public NameFilter(Vector<String> srcFileNames) 
	{
		this.srcFileNames = srcFileNames;
	}

	@Override
	public boolean accept(File arg0, String arg1)
	{
		return this.srcFileNames.contains(arg1);
	}
	
}

public class DeploymentInfoHelper 
{
	private IFileInfo[] srcInfo = null;
	private IFileInfo[] destInfo = null;
	
	private String dest = null;
	
	public DeploymentInfoHelper(String src, String dest) 
	{
		this.dest = dest;
		setDeploymentStat(src, dest);
	}

	public IFileInfo[] getSourceFileInfo()
	{
		return srcInfo;
	}
	
	public IFileInfo[] getDestFileInfo()
	{
		return destInfo;
	}
	
	public void deploy()
	{
		for(IFileInfo fileInfo : srcInfo)
		{
			boolean transferStat = fileInfo.getFile().renameTo(new File(this.dest + "\\" + fileInfo.getFileName()));
			if(!transferStat)
			{
				System.out.println("Err");
			}
		}
	}
	
	private void setDeploymentStat(String src, String dest)
	{
		File file = new File(src);
		if(file.isDirectory())
		{
			File[] files = file.listFiles();
			this.srcInfo = new IFileInfo[files.length];
			Vector<String> fileNames = fillFileInfo(this.srcInfo, files);
			
			file = new File(dest);
			String[] filePath = file.list(new NameFilter(fileNames));
			files = new File[filePath.length];
			for(int i = 0; i < filePath.length; ++i)
			{
				files[i] = new File(dest + "\\" +filePath[i]);
			}
			
			this.destInfo = new IFileInfo[files.length];
			fillFileInfo(this.destInfo, files);
			
		}
	}
	
	private Vector<String> fillFileInfo(IFileInfo[] filesInfo, File[] files)
	{
		Vector<String> fileNames = new Vector<String>();
		
		for(int i = 0; i < files.length; ++i)
		{
			filesInfo[i] = new CFileInfo(files[i]);
			fileNames.add(filesInfo[i].getFileName());
		}
		
		return fileNames;
	}

}
