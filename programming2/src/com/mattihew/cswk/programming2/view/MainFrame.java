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

import com.mattihew.cswk.programming2.controller.interfaces.UIController;
import com.mattihew.cswk.programming2.controller.undo.UndoController;

public class MainFrame extends JFrame implements Observer
{
	/** serialVersionUID */
	private static final long serialVersionUID = 5870316368738488041L;
	
	private JMenuItem mntmUndo;
	
	private JMenuItem mntmRedo;
	
	/**
	 * Create the frame.
	 * @wbp.parser.constructor
	 */
	public MainFrame(final UndoController undoController, final Collection<UIController<?>> controllers)
	{
		super("Trip Manager");
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		if (Objects.nonNull(undoController))
		{
			JMenu mnEdit = new JMenu("Edit");
			menuBar.add(mnEdit);
			
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
		
		if (Objects.nonNull(controllers) && !controllers.isEmpty())
		{
			JMenu mnInsert = new JMenu("Insert");
			menuBar.add(mnInsert);
			
			for (UIController<?> controller : controllers)
			{
				JMenuItem mntmNewItem = new JMenuItem("New " + controller.getItemName());
				mnInsert.add(mntmNewItem);
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
		
		final JTabbedPane tabs = new JTabbedPane();
		this.getContentPane().add(tabs, BorderLayout.CENTER);
		for (UIController<?> controller : controllers)
		{
			tabs.addTab(controller.getItemName() + "s", controller.getUIPanel(this));
		}
		undoController.addObserver(this);
		this.setVisible(true);
	}

	@Override
	public void update(final Observable o, final Object arg)
	{
		if (o instanceof UndoController)
		{
			final UndoController undoContoller = (UndoController) o;
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
	}
}
