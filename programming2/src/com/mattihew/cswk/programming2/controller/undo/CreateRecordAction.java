package com.mattihew.cswk.programming2.controller.undo;

import java.util.Objects;
import java.util.UUID;

import com.mattihew.cswk.programming2.model.RecordCache;

/**
 * Adds a student to the student storage.
 * 
 * @author Matt Rayner
 */
public class CreateRecordAction<R> implements UndoableAction
{
	private final RecordCache<R> recordCache;
	private final String recordName;
	private final R newRecord;
	private R oldRecord;
	private UUID id;
	
	public CreateRecordAction(final RecordCache<R> recordCache, final String recordName, final R newRecord)
	{
		this(recordCache, recordName, newRecord, null);
	}
	
	public CreateRecordAction(final RecordCache<R> recordCache, final String recordName, final R student, final UUID id)
	{
		this.recordCache = Objects.requireNonNull(recordCache, "Record cache required to do creation into");
		this.recordName = recordName;
		this.newRecord = Objects.requireNonNull(student, "New Record to be created is required");
		this.id = id;
	}
	
	@Override
	public void doAction()
	{
		if (Objects.isNull(this.id))
		{
			this.id = this.recordCache.addRecord(this.newRecord);
		}
		else
		{
			this.oldRecord = this.recordCache.putRecord(this.id, this.newRecord);
		}
		
	}

	@Override
	public void undoAction()
	{
		if (Objects.isNull(this.oldRecord))
		{
			this.recordCache.removeRecord(this.id);
		}
		else
		{
			this.recordCache.putRecord(this.id, this.oldRecord);
		}
	}

	@Override
	public String getTitle()
	{
		return "Create " + this.recordName;
	}

}
