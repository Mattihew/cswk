package com.mattihew.cswk.programming2.controller;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.mattihew.cswk.programming2.controller.undo.CreateRecordAction;
import com.mattihew.cswk.programming2.controller.undo.EditRecordAction;
import com.mattihew.cswk.programming2.controller.undo.RemoveRecordAction;
import com.mattihew.cswk.programming2.controller.undo.UndoController;
import com.mattihew.cswk.programming2.model.RecordCache;
import com.mattihew.cswk.programming2.model.trips.Trip;

public class TripController extends TablePanelUIController<Trip>
{
	private final UndoController undoController;
	
	private final RecordCache<Trip> tripCache = new RecordCache<>();
	
	public TripController(final UndoController undoController)
	{
		this.undoController = undoController;
	}
	
	@Override
	public void createRecord(final Object[] elementValues, final UUID id)
	{
		
		
	}

	@Override
	public void createRecord(final Trip element, final UUID id)
	{
		this.undoController.doCommand(new CreateRecordAction<>(this.tripCache, this.getRecordName(), element, id));
	}

	@Override
	public void editRecord(final UUID id, final Trip element)
	{
		this.undoController.doCommand(new EditRecordAction<>(this.tripCache, this.getRecordName(), id, element));
	}

	@Override
	public void editRecord(final UUID id, final Object[] elementValues)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeRecord(final UUID id)
	{
		this.undoController.doCommand(new RemoveRecordAction<>(this.tripCache, this.getRecordName(), id));
	}

	@Override
	public String getRecordName()
	{
		return "Trip";
	}

	@Override
	public String getRecordNamePlural()
	{
		return "Trips";
	}

	@Override
	public RecordCache<Trip> getRecordCache()
	{
		return this.tripCache;
	}

	@Override
	public List<String> getTableHeadings()
	{
		return Arrays.asList("Destination","Trip Provider");
	}

	@Override
	public String[] comboOptions(final int attributeIndex)
	{
		if (attributeIndex == 1)
		{
			return new String[] {};
		}
		return null;
	}

}
