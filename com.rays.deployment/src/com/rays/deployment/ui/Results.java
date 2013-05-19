package com.rays.deployment.ui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.rays.deployment.coreds.IFileInfo;

public abstract class Results implements PropertyChangeListener
{
	private Composite resultComp = null;
	
	private Composite destComp = null;
	
	private Composite srcComp = null;
	
	
	protected abstract void readEvent(PropertyChangeEvent changeEvent);
	
	protected abstract void deployEvent(PropertyChangeEvent changeEvent);

	public Results(Composite parent) 
	{
		resultComp = new Composite(parent, SWT.BORDER);
		resultComp.setLayout(new GridLayout(2, true));
		resultComp.setVisible(false);
		createUI();
	}
	
	public Composite getResultComp() 
	{
		return resultComp;
	}

	public void createUI()
	{
		Label srcLbl = new Label(resultComp, SWT.None);
		srcLbl.setLayoutData(new GridData());
		srcLbl.setText("Source");
		
		Label destLbl = new Label(resultComp, SWT.None);
		destLbl.setLayoutData(new GridData());
		destLbl.setText("Destinaton");
		
		srcComp = new Composite(resultComp, SWT.BORDER);
		srcComp.setLayoutData(new GridData());
		
		destComp = new Composite(resultComp, SWT.BORDER);
		destComp.setLayoutData(new GridData());		
	}

	@Override
	public void propertyChange(PropertyChangeEvent changeEvent)
	{
		if (changeEvent.getPropertyName().equals("read"))
		{
			readEvent(changeEvent);
		}
		else if (changeEvent.getPropertyName().equals("deploy"))
		{
			deployEvent(changeEvent);
		}
	}
	
	protected void fillInformation(IFileInfo[] srcStat, IFileInfo[] destStat)
	{
		fillCompositeInfo(srcStat, destStat);
		resultComp.setVisible(true);
	}
	
	private void fillCompositeInfo(IFileInfo[] srcStat, IFileInfo[] destStat)
	{
		this.srcComp.setLayout(new GridLayout(2, false));
		this.destComp.setLayout(new GridLayout(2, false));
		if(srcStat.length >= destStat.length)
		{
			for(int i = 0; i < srcStat.length; ++i)
			{
				IFileInfo srcFileInfo = srcStat[i];
				Text srcNameTxt = new Text(this.srcComp, SWT.None);
				srcNameTxt.setLayoutData(new GridData(400, 24));
				srcNameTxt.setEditable(false);
				srcNameTxt.setText(srcFileInfo.getFileName());
				
				Label srcSizeLbl = new Label(this.srcComp, SWT.None);
				srcSizeLbl.setLayoutData(new GridData());
				srcSizeLbl.setText(srcFileInfo.getFileSize());
				
				Text destNameTxt = new Text(this.destComp, SWT.None);
				destNameTxt.setLayoutData(new GridData(400, 24));
				destNameTxt.setEditable(false);

				Label destSizeLbl = new Label(this.destComp, SWT.None);
				destSizeLbl.setLayoutData(new GridData());
				
				IFileInfo destFileInfo = null;
				if (i < destStat.length) 
				{
					destFileInfo = destStat[i];
					destNameTxt.setText(destFileInfo.getFileName());
					destSizeLbl.setText(destFileInfo.getFileSize());
				}
				else
				{
					destNameTxt.setText("Not Available");
					destSizeLbl.setText("");
				}
				
				if( null == destFileInfo ||
						!srcFileInfo.getFileName().equals(destFileInfo.getFileName()) ||
						!srcFileInfo.getFileSize().equals(destFileInfo.getFileSize()))
				{
					srcNameTxt.setBackground(new Color(Display.getCurrent(), 255, 0, 0));
					destNameTxt.setBackground(new Color(Display.getCurrent(), 255, 0, 0));
				}
				else
				{
					srcNameTxt.setBackground(new Color(Display.getCurrent(), 0, 255, 0));
					destNameTxt.setBackground(new Color(Display.getCurrent(), 0, 255, 0));
				}
			}
		}
		
		this.srcComp.getParent().getParent().getShell().pack();
		this.destComp.getParent().getParent().getShell().pack();
	}

}
