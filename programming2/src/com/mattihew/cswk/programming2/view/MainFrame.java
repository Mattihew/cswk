package com.mattihew.cswk.programming2.view;

import java.awt.BorderLayout;
import java.util.Collection;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.mattihew.cswk.programming2.controller.MainController;
import com.mattihew.cswk.programming2.controller.interfaces.UIController;
import com.mattihew.cswk.programming2.controller.undo.UndoController;

public class MainFrame extends JFrame implements Observer
{
	/** serialVersionUID */
	private static final long serialVersionUID = 5870316368738488041L;
	
	private final JMenuBar menuBar;
	
	private final JTabbedPane tabs;
	
	private JMenuItem mntmUndo;
	
	private JMenuItem mntmRedo;
	
	private JMenu mnInsert;

	/**
	 * Class Constructor.
	 *
	 * @param mainController the main controller for the whole Application
	 * @param undoController the undo controller to control with this UI
	 * @param controllers the UIControllers to populate the UI with
	 * @wbp.parser.constructor
	 */
	public MainFrame(final MainController mainController, final UndoController undoController, final Collection<UIController<?>> controllers)
	{
		super("Trip Manager");
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		this.menuBar = new JMenuBar();
		this.setJMenuBar(this.menuBar);
		
		if (Objects.nonNull(undoController))
		{
			JMenu mnEdit = new JMenu("Edit");
			this.menuBar.add(mnEdit);
			
			this.mntmUndo = new JMenuItem("Undo");
			this.mntmUndo.setEnabled(false);
			mnEdit.add(this.mntmUndo);
			this.mntmUndo.addActionListener((e) -> undoController.undoCommand());
			
			this.mntmRedo = new JMenuItem("Redo");
			this.mntmRedo.setEnabled(false);
			mnEdit.add(this.mntmRedo);
			this.mntmRedo.addActionListener((e) -> undoController.redoCommand());
		}
		
		this.createInsertItems(controllers);
		
		this.tabs = new JTabbedPane();
		this.tabs.addChangeListener(new ChangeListener() {
			private int selectedTabIndex;
			@Override
			public void stateChanged(final ChangeEvent e)
			{
				JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
				mainController.tabChanged(tabbedPane, this.selectedTabIndex);
				this.selectedTabIndex = tabbedPane.getSelectedIndex();
			}
		});
		this.getContentPane().add(this.tabs, BorderLayout.CENTER);
		this.createTabs(controllers);
		undoController.addObserver(this);
		mainController.addObserver(this);
	}
	
	/**
	 * Adds UI Panels to tabs.
	 * 
	 * @param controllers the controllers to get Panels from.
	 */
	private void createTabs(final Collection<UIController<?>> controllers)
	{
		for (UIController<?> controller : controllers)
		{
			this.tabs.addTab(controller.getRecordNamePlural(), controller.getUIPanel(this));
		}
	}
	
	/**
	 * adds menu item to insert menu for inserting new items
	 * 
	 * @param controllers the controllers of the Records that can be inserted
	 */
	private void createInsertItems(final Collection<UIController<?>> controllers)
	{
		if (Objects.nonNull(controllers) && !controllers.isEmpty())
		{
			if (Objects.isNull(this.mnInsert))
			{
				this.mnInsert = new JMenu("Insert");
				this.menuBar.add(this.mnInsert);
			}
			for (UIController<?> controller : controllers)
			{
				JMenuItem mntmNewItem = new JMenuItem("New " + controller.getRecordName());
				this.mnInsert.add(mntmNewItem);
				mntmNewItem.addActionListener((e) -> controller.insertNewItem(MainFrame.this));
			}
		}
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(final Observable observable, final Object arg)
	{
		if (observable instanceof UndoController)
		{
			final UndoController undoContoller = (UndoController) observable;
			if (Objects.nonNull(this.mntmUndo))
			{
				this.mntmUndo.setEnabled(undoContoller.canUndo());
				this.mntmUndo.setText("Undo " + undoContoller.nextUndoTitle());
			}
			if (Objects.nonNull(this.mntmRedo))
			{
				this.mntmRedo.setEnabled(undoContoller.canRedo());
				this.mntmRedo.setText("Redo " + undoContoller.nextRedoTitle());
			}
		}
		else if (observable instanceof MainController)
		{
			if (arg instanceof Collection)
			{
				@SuppressWarnings("unchecked") // if arg is a Collection it will always be a collection of UIControllers
				final Collection<UIController<?>> uiControllers = (Collection<UIController<?>>) arg;
				this.createInsertItems(uiControllers);
				this.createTabs(uiControllers);
			}
		}
	}
	
}
