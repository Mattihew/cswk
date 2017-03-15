package com.mattihew.cswk.programming2.controller;

import java.util.UUID;

import com.mattihew.cswk.programming2.controller.interfaces.UIController;
import com.mattihew.cswk.programming2.controller.undo.CreateRecordAction;
import com.mattihew.cswk.programming2.controller.undo.EditRecordAction;
import com.mattihew.cswk.programming2.controller.undo.RemoveRecordAction;
import com.mattihew.cswk.programming2.controller.undo.UndoController;

/**
 * Controller for Records. Defines default behaviour for creating,
 *  editing and removing records in a undoable way.
 * 
 * @author Matt Rayner
 * 
 * @param <R> the Record's class
 */
public abstract class RecordController<R> implements UIController<R>
{
	private final UndoController undoController;
	/**
	 * Class Constructor
	 *
	 * @param undoController the undo controller to store the action history.
	 */
	public RecordController(final UndoController undoController)
	{
		super();
		this.undoController = undoController;
	}
	
	@Override
	public void createRecord(final R element, final UUID id)
	{
		this.undoController.doCommand(new CreateRecordAction<>(this.getRecordCache(), this.getRecordName(), element, id));
	}

	@Override
	public void editRecord(final UUID id, final R element)
	{
		this.undoController.doCommand(new EditRecordAction<>(this.getRecordCache(), this.getRecordName(), id, element));
	}
	
	@Override
	public void removeRecord(final UUID id)
	{
		this.undoController.doCommand(new RemoveRecordAction<>(this.getRecordCache(), this.getRecordName(), id));
	}
}
