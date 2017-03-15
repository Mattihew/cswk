package com.mattihew.cswk.programming2.controller.undo;

import javax.swing.JTabbedPane;

/**
 * Action for changing Tabs
 * 
 * @author Matt Rayner
 */
public class ChangeTabAction implements UndoableAction
{
	private final JTabbedPane tabbedPane;
	private final int newTab;
	private final int oldTab;
	private final boolean changeTabOnDoAction;
	
	/**
	 * Class Constructor
	 *
	 * @param tabbedPane the {@link JTabbedPane} this action applies to.
	 * @param oldTabIndex the previous index of the index.
	 */
	public ChangeTabAction(final JTabbedPane tabbedPane, final int oldTabIndex)
	{
		this(tabbedPane, oldTabIndex, false);
	}
	
	/**
	 * Class Constructor
	 *
	 * @param tabbedPane the {@link JTabbedPane} this action applies to.
	 * @param oldTabIndex the previous index of the index.
	 * @param changeTabNow if <code>true</code> then the action will be triggered on the do action
	 */
	public ChangeTabAction(final JTabbedPane tabbedPane, final int oldTabIndex, final boolean changeTabNow)
	{
		this.tabbedPane = tabbedPane;
		this.newTab = tabbedPane.getSelectedIndex();
		this.oldTab = oldTabIndex;
		this.changeTabOnDoAction = changeTabNow;
	}
	
	@Override
	public void doAction()
	{
		if(this.changeTabOnDoAction)
		{
			this.redoAction();
		}
	}

	@Override
	public void undoAction()
	{
		this.tabbedPane.setSelectedIndex(this.oldTab);
	}
	
	@Override
	public void redoAction()
	{
		this.tabbedPane.setSelectedIndex(this.newTab);
	}

	@Override
	public String getTitle()
	{
		return "Change Tab";
	}

}
