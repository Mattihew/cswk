package com.mattihew.cswk.programming2.controller.undo;

import java.util.Objects;
import java.util.UUID;

import com.mattihew.cswk.programming2.model.tableModel.RecordCache;

/**
 * Adds a student to the student storage.
 * 
 * @author Matt Rayner
 * 
 * @param <R> the Record cache
 */
public class CreateRecordAction<R> implements UndoableAction
{
	private final RecordCache<R> recordCache;
	private final String recordName;
	private final R newRecord;
	private R oldRecord;
	private UUID id;
	
	/**
	 * Class Constructor
	 *
	 * @param recordCache the record cache to add the record to.
	 * @param recordName the record name. used for this action's title.
	 * @param newRecord the new value for the Record
	 */
	public CreateRecordAction(final RecordCache<R> recordCache, final String recordName, final R newRecord)
	{
		this(recordCache, recordName, newRecord, null);
	}
	
	/**
	 * Class Constructor
	 *
	 * @param recordCache the record cache to add the record to.
	 * @param recordName the record name. used for this action's title.
	 * @param newRecord the new value for the Record
	 * @param id the id to give to the record.
	 */
	public CreateRecordAction(final RecordCache<R> recordCache, final String recordName, final R record, final UUID id)
	{
		this.recordCache = Objects.requireNonNull(recordCache, "Record cache required to do creation into");
		this.recordName = recordName;
		this.newRecord = Objects.requireNonNull(record, "New Record to be created is required");
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
