package com.mattihew.cswk.programming2.controller.undo;

import java.util.Objects;
import java.util.UUID;

import com.mattihew.cswk.programming2.model.abstracts.RecordCache;

public class RemoveRecordAction<R> implements UndoableAction
{
	private final RecordCache<R> recordCache;
	private final UUID id;
	private R oldRecord;
	public RemoveRecordAction(final RecordCache<R> recordCache, final UUID id)
	{
		this.recordCache = Objects.requireNonNull(recordCache, "Record Cache required for removal");
		this.id = Objects.requireNonNull(id, "Id of record required");
	}
	
	@Override
	public void doAction()
	{
		this.oldRecord = this.recordCache.removeRecord(this.id);
	}

	@Override
	public void undoAction()
	{
		this.recordCache.putRecord(this.id, this.oldRecord);
	}

	@Override
	public String getTitle()
	{
		return "Remove " + this.recordCache.getRecordName();
	}

}