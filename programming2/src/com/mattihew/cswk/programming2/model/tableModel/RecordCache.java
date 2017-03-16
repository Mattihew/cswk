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

/**
 * Storage class for any type of Record.
 * 
 * @author Matt Rayner
 * @param <R> The type of the Record.
 */
public class RecordCache<R> extends Observable
{
	private final SortedMap<UUID, R> records;
	
	/**
	 * Class Constructor.
	 */
	public RecordCache()
	{
		super();
		this.records = new TreeMap<>();
	}
	
	/**
	 * Gets a Record by its ID.
	 * 
	 * @param id the id of the record to get.
	 * @return the record or {@code null} if non found
	 */
	public R getRecord(final UUID id)
	{
		return this.records.get(id);
	}
	
	/**
	 * Gets the id of an already stored record.
	 * 
	 * @param record the record to lookup
	 * @return the records id or {@code null} if record is not stored in this object.
	 */
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
	
	/**
	 * Gets a unmodifiable view of the map used to store id's and records.
	 * 
	 * @return this cache's map
	 */
	public Map<UUID, R> getRecords()
	{
		return Collections.unmodifiableMap(this.records);
	}
	
	/**
	 * Gets a new map of records that have an id specified by the parameters
	 * 
	 * @param ids a collection of ids to look for.
	 * @return a map of id's to records.
	 */
	public Map<UUID, R> getRecords(final Collection<UUID> ids)
	{
		final Map<UUID, R> results;
		if (Objects.isNull(ids) || ids.isEmpty())
		{
			//no ids listed
			results =  Collections.emptyMap();
		}
		else if (ids.size() == 1)
		{
			//1 id listed
			final UUID id = ids.iterator().next();
			results = Collections.singletonMap(id, this.records.get(id));
		}
		else
		{
			// more than 1 id listed
			results = new LinkedHashMap<>();
			for (final Entry<UUID, R> recordEntry : this.records.entrySet())
			{
				if (ids.contains(recordEntry.getKey()))
				{
					results.put(recordEntry.getKey(), recordEntry.getValue());
				}
			}
		}
		return results;
	}
	
	/**
	 * Removes a record.
	 * 
	 * @param id the id of the record to remove.
	 * @return the record that was removed, or {@code null} if no record was removed.
	 */
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
	
	/**
	 * Adds a Record
	 * 
	 * @param record the record to add.
	 * @return the id given to the record
	 */
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
	
	/**
	 * Adds a collection of Records.
	 * 
	 * @param records the Records to add.
	 * @return a list of UUID created for the Records.
	 */
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
	
	/**
	 * Put a record with an id into the cache
	 * 
	 * @param id the id to give to the Record
	 * @param record the record to store.
	 * @return the previous Record stored at the given id, or {@code null} if no mapping existed.
	 */
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
	
	/**
	 * puts a Map of uuids to Records into this cache.
	 * 
	 * @param idMap the ids and records to put.
	 * @return Map of the previous values for the provided ids.
	 */
	public Map<UUID, R> putRecords(final Map<UUID, R> idMap)
	{
		if (Objects.isNull(idMap) || idMap.isEmpty())
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
	
	/**
	 * Gets the number of Records stored.
	 * 
	 * @return the number of records
	 */
	public int size()
	{
		return this.records.size();
	}
	
	/**
	 * gets a unmodifiable view of this caches Map entry's.
	 * 
	 * @return the Map entry's
	 */
	Set<Entry<UUID, R>> entrySet()
	{
		return Collections.unmodifiableMap(this.records).entrySet();
	}
}