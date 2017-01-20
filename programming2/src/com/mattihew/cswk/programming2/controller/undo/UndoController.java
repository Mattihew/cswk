package com.mattihew.cswk.programming2.controller.undo;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class UndoController
{
	private final List<Command> history;
	private final ListIterator<Command> iterator;
	
	public UndoController()
	{
		this.history =  new LinkedList<>();
		this.iterator = this.history.listIterator();
	}
	
	public void doCommand(final Command command)
	{
		this.iterator.add(command);
		while (this.iterator.hasNext())
		{
			this.iterator.remove();
		}
		command.doCommand();
	}
	
	public void undoCommand()
	{
		if (this.iterator.hasPrevious())
		{
			this.iterator.previous().undoCommand();
		}
	}
	
	public void redoCommand()
	{
		if (this.iterator.hasNext())
		{
			this.iterator.next().doCommand();
		}
	}
	
	public boolean canUndo()
	{
		return this.iterator.hasPrevious();
	}
	
	public boolean canRedo()
	{
		return this.iterator.hasNext();
	}
	
	public String nextUndoTitle()
	{
		String result = null;
		if (this.iterator.hasPrevious())
		{
			result = this.iterator.previous().getTitle();
			this.iterator.next();
		}
		return result;
	}
	
	public String nextRedoTitle()
	{
		String result = null;
		if (this.iterator.hasNext())
		{
			result = this.iterator.next().getTitle();
			this.iterator.previous();
		}
		return result;
	}
}
