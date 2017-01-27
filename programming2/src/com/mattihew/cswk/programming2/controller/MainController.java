package com.mattihew.cswk.programming2.controller;

import java.awt.EventQueue;
import java.util.Arrays;

import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import com.mattihew.cswk.programming2.controller.undo.ChangeTabAction;
import com.mattihew.cswk.programming2.controller.undo.UndoController;
import com.mattihew.cswk.programming2.view.MainFrame;

public class MainController
{
	private final UndoController undoController;
	/**
	 * Launch the application.
	 */
	public static void main(final String[] args)
	{
		new MainController();
	}
	
	private MainController()
	{
		this.undoController = new UndoController();
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
				final StudentController studentController = new StudentController(MainController.this.undoController);
				final TeacherController teacherController = new TeacherController(MainController.this.undoController);
				new MainFrame(MainController.this, MainController.this.undoController, Arrays.asList(studentController, teacherController));
			}
		});
	}
	
	public void tabChanged(final JTabbedPane tabbedPane, final int oldTabIndex)
	{
		if (this.undoController.canDo() && tabbedPane.getSelectedIndex() != oldTabIndex)
		{
			//this got annoying really fast.
			//this.undoController.doCommand(new ChangeTabAction(tabbedPane, oldTabIndex));
		}
	}
}
