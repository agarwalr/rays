package com.rays.deployment.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout());
		
		Composite mainComp = new Composite(shell, SWT.None);
		mainComp.setLayoutData(new GridData());
		mainComp.setLayout(new GridLayout());
		
		UserInput input = new UserInput(mainComp);
		input.getInputComp().setLayoutData(new GridData());
		
		
		Results pluginInfo = ResultsFactory.getResult(ResultType.Plugin , mainComp);
		pluginInfo.getResultComp().setLayoutData(new GridData());
		
		Results binInfo = ResultsFactory.getResult(ResultType.Binary, mainComp);
		binInfo.getResultComp().setLayoutData(new GridData());
		
		input.addPropertyChangeListener ("deploy", pluginInfo);
		input.addPropertyChangeListener("deploy", binInfo);
		
		input.addPropertyChangeListener ("read", pluginInfo);
		input.addPropertyChangeListener("read", binInfo);
		
		shell.pack();
		shell.open();
		while(!shell.isDisposed())
		{
			if(!display.readAndDispatch())
			{
				display.sleep();
			}
		}
		
		display.dispose();
		

	}

}
