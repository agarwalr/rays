package com.rays.deployment.ui;

import java.beans.PropertyChangeEvent;

import org.eclipse.swt.widgets.Composite;

import com.rays.deployment.coreds.DeploymentInfoHelper;
import com.rays.deployment.coreds.SrcDestInfo;

public class BinaryResult extends Results 
{
	private DeploymentInfoHelper deploymentHlpr = null;
	
	public BinaryResult(Composite parent) 
	{
		super(parent);
	}

	@Override
	public void readEvent(PropertyChangeEvent changeEvent) 
	{
		SrcDestInfo info = (SrcDestInfo) changeEvent.getNewValue();
		String src = info.getSource() + "\\bin";
		String dest = info.getDest() + "\\bin";
		
		deploymentHlpr = new DeploymentInfoHelper(src, dest);
		if(deploymentHlpr.getSourceFileInfo() != null && deploymentHlpr.getDestFileInfo() != null)
		{
			fillInformation(deploymentHlpr.getSourceFileInfo(), deploymentHlpr.getDestFileInfo());
		}

	}

	@Override
	protected void deployEvent(PropertyChangeEvent changeEvent)
	{
		this.deploymentHlpr.deploy();
	}

}
