package com.mattihew.cswk.programming2.controller.undo;

/**
 * Interface representing an undoable action
 * 
 * @author Matt Rayner
 */
public interface UndoableAction
{
	/**
	 * Method run when Action is first added to UndoController.
	 */
	void doAction();
	
	/**
	 * Method run when UndoController undo action is performed
	 */
	void undoAction();
	
	/**
	 * Method run when UndoController redo action is performed.
	 * by default this calls the {@link #doAction()} method.
	 */
	default void redoAction()
	{
		this.doAction();
	}
	
	/**
	 * get the title for this Action. used to see what action will next be undone/redone.
	 * 
	 * @return title of the action.
	 */
	String getTitle();
}
