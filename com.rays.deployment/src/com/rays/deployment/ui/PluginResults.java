package com.rays.deployment.ui;

import java.beans.PropertyChangeEvent;

import org.eclipse.swt.widgets.Composite;

import com.rays.deployment.coreds.DeploymentInfoHelper;
import com.rays.deployment.coreds.SrcDestInfo;

public class PluginResults extends Results
{
	private DeploymentInfoHelper deploymentHlpr = null;
	
	public PluginResults(Composite parent) 
	{
		super(parent);
	}
	
	@Override
	public void readEvent(PropertyChangeEvent changeEvent)
	{
		if (changeEvent.getPropertyName().equals("read"))
		{
			SrcDestInfo info = (SrcDestInfo) changeEvent.getNewValue();
			String src = info.getSource() + "\\portal\\plugins";
			String dest = info.getDest() + "\\portal\\plugins";
			
			deploymentHlpr = new DeploymentInfoHelper(src, dest);
			if(deploymentHlpr.getSourceFileInfo() != null && deploymentHlpr.getDestFileInfo() != null)
			{
				fillInformation(deploymentHlpr.getSourceFileInfo(), deploymentHlpr.getDestFileInfo());
			}
		}
	}

	@Override
	protected void deployEvent(PropertyChangeEvent changeEvent) 
	{
		this.deploymentHlpr.deploy();
		
	}

}
