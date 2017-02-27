package com.mattihew.cswk.programming2.controller.interfaces;

import java.awt.Frame;
import java.awt.Panel;
import java.util.UUID;

import com.mattihew.cswk.programming2.model.tableModel.RecordCache;

public interface UIController<E>
{
	default void createRecord (final Object[] elementValues)
	{
		this.createRecord(elementValues, null);
	}
	
	void createRecord (final Object[] elementValues, final UUID id);
	
	default void createRecord(final E element)
	{
		this.createRecord(element, null);
	}
	
	void createRecord(final E element, final UUID id);
	
	void editRecord(final UUID id, final E element);
	
	void editRecord(final UUID id, final Object[] elementValues);
	
	void removeRecord(final UUID id);
	
	Panel getUIPanel(final Frame owner);
	
	void insertNewItem(final Frame owner);
	
	void editExistingItem(Frame owner, UUID id);
	
	void removeExistingItem(Frame owner, UUID id);
	
	String getRecordName();
	
	String getRecordNamePlural();
	
	RecordCache<E> getRecordCache();
	
	default void dispose() {}
}
