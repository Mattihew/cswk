package com.mattihew.cswk.programming2.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

import com.mattihew.cswk.programming2.controller.interfaces.UIController;
import com.mattihew.cswk.programming2.controller.undo.UndoController;

public class MainFrame extends JFrame
{
	/** serialVersionUID */
	private static final long serialVersionUID = 5870316368738488041L;
	
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
		
		if (!Objects.isNull(undoController))
		{
			JMenu mnEdit = new JMenu("Edit");
			menuBar.add(mnEdit);
			
			JMenuItem mntmUndo = new JMenuItem("Undo");
			mnEdit.add(mntmUndo);
			mntmUndo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent e)
				{
					undoController.undoCommand();
				}
			});
			
			JMenuItem mntmRedo = new JMenuItem("Redo");
			mnEdit.add(mntmRedo);
			mntmRedo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent e)
				{
					undoController.redoCommand();
				}
			});
		}
		
		if (!controllers.isEmpty())
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
		
		this.setVisible(true);
	}
}
