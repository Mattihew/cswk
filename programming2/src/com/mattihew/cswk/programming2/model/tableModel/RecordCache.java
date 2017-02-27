package com.mattihew.cswk.programming2.model.tableModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Observable;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

public class RecordCache<R> extends Observable
{
	private final SortedMap<UUID, R> records;
	
	public RecordCache()
	{
		super();
		this.records = new TreeMap<>();
	}
	
	public R getRecord(final UUID id)
	{
		return this.records.get(id);
	}
	
	public UUID getID(final R record)
	{
		for(final Entry<UUID, R> entry : this.records.entrySet())
		{
			if (Objects.equals(entry.getValue(), record))
			{
				return entry.getKey();
			}
		}
		return null;
	}
	
	public Map<UUID, R> getRecords()
	{
		return Collections.unmodifiableMap(this.records);
	}
	
	public Map<UUID, R> getRecords(final Collection<UUID> ids)
	{
		if (ids.isEmpty())
		{
			return Collections.emptyMap();
		}
		else if (ids.size() == 1)
		{
			final UUID id = ids.iterator().next();
			return Collections.singletonMap(id, this.records.get(id));
		}
		final Map<UUID, R> results = new LinkedHashMap<>();
		for (final Entry<UUID, R> recordEntry : this.records.entrySet())
		{
			if (ids.contains(recordEntry.getKey()))
			{
				results.put(recordEntry.getKey(), recordEntry.getValue());
			}
		}
		return results;
	}
	
	public R removeRecord(final UUID id)
	{
		final R result = this.records.remove(id);
		if (Objects.nonNull(result))
		{
			this.setChanged();
			this.notifyObservers(Collections.singleton(id));
		}
		return result;
	}
	
	public UUID addRecord(final R record)
	{
		if (this.records.containsValue(record))
		{
			return null;
		}
		final UUID recordID = UUID.randomUUID();
		this.records.put(recordID, record);
		this.setChanged();
		this.notifyObservers(Collections.singleton(recordID));
		return recordID;
	}
	
	public List<UUID> addRecords(final Collection<R> records)
	{
		if (records.isEmpty())
		{
			return Collections.emptyList();
		}
		final List<UUID> ids = new ArrayList<>(records.size());
		for (final R record : records)
		{
			if (!this.records.containsValue(record))
			{
				final UUID recordID = UUID.randomUUID();
				this.records.put(recordID, record);
				ids.add(recordID);
			}
		}
		if (!ids.isEmpty())
		{
			this.setChanged();
			this.notifyObservers(ids);
		}
		return ids;
	}
	
	public R putRecord(final UUID id, final R record)
	{
		final R oldRecord = this.records.put(id, record);
		if (!record.equals(oldRecord))
		{
			this.setChanged();
			this.notifyObservers(Collections.singleton(id));
		}
		return oldRecord;
	}
	
	public Map<UUID, R> putRecords(final Map<UUID, R> idMap)
	{
		if (idMap.isEmpty())
		{
			return Collections.emptyMap();
		}
		final Map<UUID, R> oldRecords = new LinkedHashMap<>(idMap.size());
		boolean changed = false;
		for (final Entry<UUID, R> entry : idMap.entrySet())
		{
			final R oldRecord = this.records.put(entry.getKey(), entry.getValue());
			oldRecords.put(entry.getKey(), oldRecord);
			changed |= !Objects.equals(oldRecords, entry.getValue());
		}
		if (changed)
		{
			this.setChanged();
			this.notifyObservers(oldRecords.keySet());
		}
		return oldRecords;
	}
	
	public int size()
	{
		return this.records.size();
	}
	
	Set<Entry<UUID, R>> entrySet()
	{
		return this.records.entrySet();
	}
}