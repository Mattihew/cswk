package com.mattihew.cswk.programming2.view;

import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.WindowConstants;

public class NewStudentView extends JDialog
{
	public NewStudentView(final Frame owner)
	{
		super(owner, "New Student", true);
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		this.setVisible(true);
	}
	
	
}
