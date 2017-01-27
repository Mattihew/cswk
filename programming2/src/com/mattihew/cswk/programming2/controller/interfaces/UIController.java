package com.mattihew.cswk.programming2.controller.interfaces;

import java.awt.Frame;
import java.awt.Panel;
import java.util.List;
import java.util.UUID;

import com.mattihew.cswk.programming2.model.RecordCache;
import com.mattihew.cswk.programming2.model.students.Student;

public interface UIController<E>
{
	default void createRecord (final String[] elementValues)
	{
		this.createRecord(elementValues, null);
	}
	
	void createRecord (final String[] elementValues, final UUID id);
	
	default void createRecord(final E element)
	{
		this.createRecord(element, null);
	}
	
	void createRecord(final E element, final UUID id);
	
	void editRecord(final UUID id, final E element);
	
	void editRecord(final UUID id, final String[] elementValues);
	
	void removeRecord(final UUID id);
	
	Panel getUIPanel(final Frame owner);
	
	void insertNewItem(final Frame owner);
	
	void editExistingItem(Frame owner, UUID id);
	
	void removeExistingItem(Frame owner, UUID id);
	
	String getRecordName();
	
	String getRecordNamePlural();
	
	RecordCache<E> getRecordCache();
	
	List<String> getTableHeadings();

	
}
