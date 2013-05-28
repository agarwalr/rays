package com.rays.deployment.utils;

import java.io.IOException;

public class ServiceController
{
	public static void startService(String serviceName)
	{
		MessageController.pushInfoMessage(0, "Starting : " + serviceName, "");
		startStopService("start " + serviceName);
	}
	
	public static void stopService(String serviceName)
	{
		MessageController.pushInfoMessage(0, "Stopping : " + serviceName, "");
		startStopService("stop " + serviceName);
	}
	
	private static void  startStopService(String scCmd)
	{
		try 
		{
			Process process = Runtime.getRuntime().exec("sc " + scCmd);
			int stat = process.waitFor();
			if(stat == 0)
			{
				MessageController.pushInfoMessage(0, "Done", "");
			}
			else
			{
				MessageController.pushInfoMessage(1, "Failed", "");
			}
		} 
		catch (IOException e)
		{
			MessageController.pushInfoMessage(1, "Failed", "");
			e.printStackTrace();
		}
		catch (InterruptedException e)
		{
			MessageController.pushInfoMessage(1, "Failed", "");
			e.printStackTrace();
		}
	}

}
