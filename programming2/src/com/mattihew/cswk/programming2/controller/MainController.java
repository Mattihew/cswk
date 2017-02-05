package com.mattihew.cswk.programming2.controller;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Observable;

import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.mattihew.cswk.programming2.controller.interfaces.UIController;
import com.mattihew.cswk.programming2.controller.undo.UndoController;
import com.mattihew.cswk.programming2.view.MainFrame;

public class MainController extends Observable
{
	private final UndoController undoController;
	
	private final Collection<UIController<?>> uiControllers = new LinkedHashSet<>(3);
	
	private MainFrame mainFrame;
	/**
	 * Launch the application.
	 */
	public static void main(final String[] args)
	{
		new MainController();
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
				MainController.this.createUI();
			}
		});
	}
	
	private void createUI()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}
		final StudentController studentController = new StudentController(this.undoController);
		this.uiControllers.add(studentController);
		this.uiControllers.add(new TeacherController(this.undoController));
		this.uiControllers.add(new TripController(this.undoController, this, studentController.getRecordCache().getRecords().values()));
		this.mainFrame = new MainFrame(this, this.undoController, this.uiControllers);
		this.mainFrame.setVisible(true);
		this.mainFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e)
			{
				MainController.this.disposing();
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
	
	public void addUIController(final UIController<?> uiController)
	{
		if (this.uiControllers.add(uiController))
		{
			this.setChanged();
			this.notifyObservers(Collections.singleton(uiController));
		}
	}
	
	private void disposing()
	{
		for (UIController<?> controller : this.uiControllers)
		{
			controller.dispose();
		}
	}
}
