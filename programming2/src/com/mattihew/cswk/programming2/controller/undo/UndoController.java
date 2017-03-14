package com.mattihew.cswk.programming2.controller.undo;

import java.util.ConcurrentModificationException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Observable;

public class UndoController extends Observable
{
	private final ListIterator<UndoableAction> iterator;
	
	private boolean isWorking;
	
	/**
	 * Class Constructor.
	 */
	public UndoController()
	{
		super();
		this.iterator = new LinkedList<UndoableAction>().listIterator();
	}
	
	/**
	 * Executes a command and adds it to the history list.
	 * 
	 * @param command the command to do.
	 * @throws ConcurrentModificationException if a command is currently being done
	 */
	public synchronized void doCommand(final UndoableAction command) throws ConcurrentModificationException
	{
		Objects.requireNonNull(command, "command cannot be null");
		if (this.isWorking)
		{
			throw new ConcurrentModificationException("Cannot add new actions while undoing or redoing another action");
		}
		this.iterator.add(command);
		while (this.iterator.hasNext())
		{
			this.iterator.next();
			this.iterator.remove();
		}
		this.isWorking = true;
		command.doAction();
		this.setChanged();
		this.notifyObservers();
		this.isWorking = false;
	}
	
	/**
	 * Undoes the previous command in the undo history.
	 * 
	 * @throws NoSuchElementException if no command exists before the current position of the pointer.
	 */
	public synchronized void undoCommand() throws NoSuchElementException
	{
		this.isWorking = true;
		this.iterator.previous().undoAction();
		this.setChanged();
		this.notifyObservers();
		this.isWorking = false;
	}
	
	/**
	 * Redoes the next command in the undo history.
	 * 
	 * @throws NoSuchElementException if no command exists after the current position of the pointer.
	 */
	public synchronized void redoCommand() throws NoSuchElementException
	{
		this.isWorking = true;
		this.iterator.next().redoAction();
		this.setChanged();
		this.notifyObservers();
		this.isWorking = false;
	}
	
	/**
	 * Checks if commands are allowed to be done.
	 * 
	 * @return <code>true</code> if commands can be added.
	 */
	public boolean canDo()
	{
		return !this.isWorking;
	}
	
	/**
	 * Checks if there are commands to undo.
	 * 
	 * @return <code>true</code> if there are commands to undo.
	 */
	public boolean canUndo()
	{
		return this.iterator.hasPrevious();
	}
	
	/**
	 * Checks if there are commands to redo.
	 * 
	 * @return <code>true</code> if there are commands to redo.
	 */
	public boolean canRedo()
	{
		return this.iterator.hasNext();
	}
	
	/**
	 * Gets the title of the action that will be performed by calling {@link #undoCommand()}
	 * 
	 * @return the previous command's title, or an empty String if non exists.
	 */
	public synchronized String nextUndoTitle()
	{
		String result = "";
		if (this.iterator.hasPrevious())
		{
			result = this.iterator.previous().getTitle();
			this.iterator.next();
		}
		return result;
	}
	
	/**
	 * Gets the title of the action that will be performed by calling {@link #redoCommand()}
	 * 
	 * @return the next command's title, or an empty String if non exists.
	 */
	public synchronized String nextRedoTitle()
	{
		String result = "";
		if (this.iterator.hasNext())
		{
			result = this.iterator.next().getTitle();
			this.iterator.previous();
		}
		return result;
	}
}
