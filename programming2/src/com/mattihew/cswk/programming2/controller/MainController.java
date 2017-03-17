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
import com.mattihew.cswk.programming2.model.interfaces.TripProvider;
import com.mattihew.cswk.programming2.view.MainFrame;

/**
 * The main controller for this application.
 * 
 * @author Matt Rayner
 */
public class MainController extends Observable
{
	private final UndoController undoController;
	
	private final Collection<UIController<?>> uiControllers = new LinkedHashSet<>(3);
	
	private MainFrame mainFrame;
	/**
	 * Launch the application.
	 * @param args unused launch arguments
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
		EventQueue.invokeLater(() -> MainController.this.createUI());
	}
	
	/**
	 * Creates and populates the UI with any initial data.
	 */
	private final void createUI()
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
		final TeacherController teacherController = new TeacherController(this.undoController);
		final ExternalProviderController extProvController = new ExternalProviderController(undoController);
		this.uiControllers.add(studentController);
		this.uiControllers.add(teacherController);
		this.uiControllers.add(extProvController);
		
		this.uiControllers.add(new TripController(this.undoController, this, 
				studentController.getRecordCache().getRecords().values(),
				extProvController.getRecordCache().getRecords().values(),
				teacherController.getRecordCache().getRecords().values()));
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
	
	/**
	 * Triggered when the tabs have been changed on the ui.
	 * 
	 * @param tabbedPane the tabbed pane that has changed.
	 * @param oldTabIndex the previous tab index.
	 */
	public void tabChanged(final JTabbedPane tabbedPane, final int oldTabIndex)
	{
		if (this.undoController.canDo() && tabbedPane.getSelectedIndex() != oldTabIndex)
		{
			//this got annoying really fast.
			//this.undoController.doCommand(new ChangeTabAction(tabbedPane, oldTabIndex));
		}
	}
	
	/**
	 * Adds a new {@link UIController} to this controller.
	 * 
	 * @param uiController the UIController to add.
	 */
	public void addUIController(final UIController<?> uiController)
	{
		if (this.uiControllers.add(uiController))
		{
			this.setChanged();
			this.notifyObservers(Collections.singleton(uiController));
		}
	}
	
	/**
	 * disposes each controller.
	 */
	private void disposing()
	{
		for (UIController<?> controller : this.uiControllers)
		{
			controller.dispose();
		}
	}
}
