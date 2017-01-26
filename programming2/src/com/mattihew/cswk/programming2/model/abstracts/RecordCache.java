package com.mattihew.cswk.programming2.model.abstracts;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Observable;
import java.util.UUID;

public abstract class RecordCache<R> extends Observable
{
	private final Map<UUID, R> records;
	
	public RecordCache()
	{
		super();
		this.records = new LinkedHashMap<>();
	}
	
	public R getRecord(final UUID id)
	{
		return this.records.get(id);
	}
	
	public Map<UUID, R> getRecords()
	{
		return Collections.unmodifiableMap(this.records);
	}
	
	public R removeRecord(final UUID id)
	{
		final R result = this.records.remove(id);
		this.setChanged();
		this.notifyObservers(Collections.singleton(id));
		return result;
	}
	
	public UUID addRecord(final R record)
	{
		if (this.records.containsValue(record))
		{
			return null;
		}
		final UUID studentID = UUID.randomUUID();
		this.records.put(studentID, record);
		this.setChanged();
		this.notifyObservers(Collections.singleton(studentID));
		return studentID;
	}
	
	public R putRecord(final UUID id, final R record)
	{
		final R result = this.records.put(id, record);
		if (!record.equals(result))
		{
			this.setChanged();
			this.notifyObservers(Collections.singleton(id));
		}
		return result;
	}
}