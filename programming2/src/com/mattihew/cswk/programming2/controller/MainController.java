package com.mattihew.cswk.programming2.controller;

import java.awt.EventQueue;
import java.util.Arrays;

import javax.swing.UIManager;

import com.mattihew.cswk.programming2.controller.undo.UndoController;
import com.mattihew.cswk.programming2.view.MainFrame;

public class MainController
{
	/**
	 * Launch the application.
	 */
	public static void main(final String[] args)
	{
		final UndoController undoController = new UndoController();
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
				final StudentController studentController = new StudentController(undoController);
				new MainFrame(undoController, Arrays.asList(studentController));
			}
		});
	}
}
