package com.mattihew.cswk.programming2.controller;

import java.awt.EventQueue;

import javax.swing.UIManager;

public class MainController
{
	/**
	 * Launch the application.
	 */
	public static void main(final String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				}
				catch (Exception e)
				{
					
				}
				(new StudentController()).showUI();
			}
		});
	}
}
