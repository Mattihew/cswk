package com.mattihew.cswk.programming2.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	
	private JMenuItem mntmUndo;
	
	private JMenuItem mntmRedo;
	
	private JMenu mnInsert;
	
	private final JTabbedPane tabs;
	
	private int selectedTabIndex;
	
	/**
	 * Create the frame.
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
			this.mntmUndo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent e)
				{
					undoController.undoCommand();
				}
			});
			
			this.mntmRedo = new JMenuItem("Redo");
			this.mntmRedo.setEnabled(false);
			mnEdit.add(this.mntmRedo);
			this.mntmRedo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent e)
				{
					undoController.redoCommand();
				}
			});
		}
		
		
		this.createInsertItems(controllers);
		
		
		this.tabs = new JTabbedPane();
		this.tabs.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(final ChangeEvent e)
			{
				mainController.tabChanged(MainFrame.this.tabs, MainFrame.this.selectedTabIndex);
				MainFrame.this.selectedTabIndex = MainFrame.this.tabs.getSelectedIndex();
			}
		});
		this.getContentPane().add(this.tabs, BorderLayout.CENTER);
		this.createTabs(controllers);
		undoController.addObserver(this);
		mainController.addObserver(this);
	}
	
	private void createTabs(final Collection<UIController<?>> controllers)
	{
		for (UIController<?> controller : controllers)
		{
			this.tabs.addTab(controller.getRecordNamePlural(), controller.getUIPanel(this));
		}
	}
	
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
				mntmNewItem.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(final ActionEvent e)
					{
						controller.insertNewItem(MainFrame.this);
					}
				});
			}
		}
	}

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
				final Collection<UIController<?>> uiControllers = (Collection<UIController<?>>) arg;
				this.createInsertItems(uiControllers);
				this.createTabs(uiControllers);
			}
		}
	}
	
}
