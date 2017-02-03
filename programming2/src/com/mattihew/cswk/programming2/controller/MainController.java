package com.mattihew.cswk.programming2.controller;

import java.awt.EventQueue;
import java.util.Arrays;

import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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
		final MainController mainController = new MainController();
		mainController.createUI();
	}
	
	/**
	 * Class Constructor.
	 */
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
				catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	private void createUI()
	{
		final StudentController studentController = new StudentController(this.undoController);
		final TeacherController teacherController = new TeacherController(this.undoController);
		final TripController tripController = new TripController(this.undoController);
		new MainFrame(this, this.undoController, Arrays.asList(studentController, teacherController, tripController));
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
