package com.mattihew.cswk.programming2.controller.undo;

import java.util.Objects;
import java.util.UUID;

import com.mattihew.cswk.programming2.model.RecordCache;

public class EditRecordAction<R> implements UndoableAction
{
	private final RecordCache<R> recordCache;
	private final String recordName;
	private final UUID id;
	private final R newRecord;
	private R oldRecord;
	
	
	public EditRecordAction(final RecordCache<R> recordCache, final String recordName, final UUID id, final R record)
	{
		this.recordCache = Objects.requireNonNull(recordCache, "Record cache required for editing");
		this.recordName = recordName;
		this.newRecord = Objects.requireNonNull(record, "Student required for editing");
		this.id = Objects.requireNonNull(id, "Id of student Required");
	}
	
	@Override
	public void doAction()
	{
		this.oldRecord = this.recordCache.putRecord(this.id, this.newRecord);
	}

	@Override
	public void undoAction()
	{
		if (Objects.nonNull(this.oldRecord))
		{
			this.recordCache.putRecord(this.id, this.oldRecord);
		}
		else
		{
			this.recordCache.removeRecord(this.id);
		}
	}

	@Override
	public String getTitle()
	{
		return "Edit " + this.recordName;
	}

}
