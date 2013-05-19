package com.rays.deployment.ui;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.rays.deployment.coreds.SrcDestInfo;

public class UserInput
{
	private Composite inputComp = null;
	private PropertyChangeSupport pcs = null;
	
	public Composite getInputComp() 
	{
		return inputComp;
	}

	public UserInput(Composite parent)
	{
		inputComp = new Composite(parent, SWT.BORDER);
		GridLayout grdLayout = new GridLayout(2, false);
		inputComp.setLayout(grdLayout);
		createUI();
	}
	
	public void createUI()
	{
		GridData gridData = new GridData();
		Label srcLbl = new Label(inputComp, SWT.None);
		srcLbl.setText("Source : ");
		srcLbl.setLayoutData(gridData);
		
		final Text srcTxt = new Text(inputComp, SWT.None);
		gridData = new GridData(500, 24);
		srcTxt.setLayoutData(gridData);		
		
		gridData = new GridData();
		Label targetLbl = new Label(inputComp, SWT.None);
		targetLbl.setText("Target : ");
		targetLbl.setLayoutData(gridData);
		
		final Text targetTxt = new Text(inputComp, SWT.None);
		gridData = new GridData(500,24);
		targetTxt.setLayoutData(gridData);
		
		final Button readBtn = new Button(inputComp, SWT.PUSH);
		readBtn.setText("Read");
		readBtn.setLayoutData(new GridData());

		pcs = new PropertyChangeSupport(this);

		final Button deployBtn = new Button(inputComp, SWT.PUSH);
		deployBtn.setText("Deploy");
		deployBtn.setLayoutData(new GridData());
		deployBtn.setEnabled(false);
		
		readBtn.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) 
			{
				if (srcTxt.getText() != "" && targetTxt.getText() != "")
				{
					readBtn.setEnabled(false);
					deployBtn.setEnabled(true);
				}
				
				SrcDestInfo info = new SrcDestInfo(srcTxt.getText(), targetTxt.getText());
				pcs.firePropertyChange("read", "", info);

			}

			@Override
			public void mouseDown(MouseEvent e) {
			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
			}
		});
		
		deployBtn.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e)
			{
				pcs.firePropertyChange("deploy", "", "jj");
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener)
	{
		pcs.addPropertyChangeListener(propertyName, listener);
	}

}
