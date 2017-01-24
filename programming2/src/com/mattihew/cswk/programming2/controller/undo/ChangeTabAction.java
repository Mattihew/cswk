package com.mattihew.cswk.programming2.controller.undo;

import javax.swing.JTabbedPane;

public class ChangeTabAction implements UndoableAction
{
	private final JTabbedPane tabbedPane;
	private final int newTab;
	private final int oldTab;
	
	public ChangeTabAction(final JTabbedPane tabbedPane, final int oldTabIndex)
	{
		this.tabbedPane = tabbedPane;
		this.newTab = tabbedPane.getSelectedIndex();
		this.oldTab = oldTabIndex;
	}
	
	@Override
	public void doAction()
	{
		// do nothing
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
