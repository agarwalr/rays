package com.rays.deployment.ui;

import org.eclipse.swt.widgets.Composite;

enum ResultType
{
	Plugin,
	Binary
}

public class ResultsFactory 
{
	public static Results getResult(ResultType type, Composite parent)
	{
		Results res = null;
		if(type == ResultType.Plugin)
		{
			res = new PluginResults(parent);
		}
		else
		{
			res = new BinaryResult(parent);
		}
		
		return res;
	}

}
