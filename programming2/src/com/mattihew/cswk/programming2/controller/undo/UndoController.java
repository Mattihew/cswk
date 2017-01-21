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
		this.history = new LinkedList<>();
		this.iterator = this.history.listIterator();
	}
	
	public synchronized void doCommand(final Command command)
	{
		this.iterator.add(command);
		while (this.iterator.hasNext())
		{
			this.iterator.next();
			this.iterator.remove();
		}
		command.doCommand();
	}
	
	public synchronized void undoCommand()
	{
		if (this.iterator.hasPrevious())
		{
			this.iterator.previous().undoCommand();
		}
	}
	
	public synchronized void redoCommand()
	{
		if (this.iterator.hasNext())
		{
			this.iterator.next().doCommand();
		}
	}
	
	public synchronized boolean canUndo()
	{
		return this.iterator.hasPrevious();
	}
	
	public synchronized boolean canRedo()
	{
		return this.iterator.hasNext();
	}
	
	public synchronized String nextUndoTitle()
	{
		String result = null;
		if (this.iterator.hasPrevious())
		{
			result = this.iterator.previous().getTitle();
			this.iterator.next();
		}
		return result;
	}
	
	public synchronized String nextRedoTitle()
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