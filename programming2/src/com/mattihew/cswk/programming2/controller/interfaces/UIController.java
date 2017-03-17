package com.mattihew.cswk.programming2.controller.interfaces;

import java.awt.Frame;
import java.awt.Panel;
import java.util.UUID;

import com.mattihew.cswk.programming2.controller.MainController;
import com.mattihew.cswk.programming2.model.tableModel.RecordCache;

/**
 * A controller that adds UI elements to the {@link MainController}.
 * 
 * @author Matt Rayner
 *
 * @param <E> the Record type
 */
public interface UIController<E>
{
	/**
	 * Creates a record from its values. and generates a uuid for it.
	 * 
	 * @param elementValues the values to use for the record.
	 * @see #createRecord(Object[], UUID)
	 */
	default void createRecord (final Object[] elementValues)
	{
		this.createRecord(elementValues, null);
	}
	
	/**
	 * Creates a record from its values.
	 * 
	 * @param elementValues the values to use for the record.
	 * @param id the uuid to use for the record.
	 */
	void createRecord (final Object[] elementValues, final UUID id);
	
	/**
	 * Adds a created record. with a null uuid.
	 * 
	 * @param element the record that has been created.
	 * @see #createRecord(Object, UUID)
	 */
	default void createRecord(final E element)
	{
		this.createRecord(element, null);
	}
	
	/**
	 * Adds a created record.
	 * 
	 * @param element the element that has been created.
	 * @param id the uuid to assign to the record.
	 */
	void createRecord(final E element, final UUID id);
	
	/**
	 * replaces an existing record with a new record.
	 * 
	 * @param id the id of the existing record
	 * @param element the new element to replace with.
	 */
	void editRecord(final UUID id, final E element);
	
	/**
	 * creates an element from its values and then replaces an existing value with it.
	 * 
	 * @param id the uuid of the record to replace
	 * @param elementValues the values to create a record with.
	 */
	void editRecord(final UUID id, final Object[] elementValues);
	
	/**
	 * Removes a record.
	 * 
	 * @param id the uuid of the record to remove
	 */
	void removeRecord(final UUID id);
	
	/**
	 * Gets the Panel to insert into the UI
	 * 
	 * @param owner the Frame that the panel will be added to.
	 * @return the panel to add.
	 */
	Panel getUIPanel(final Frame owner);
	
	/**
	 * opens the insert item dialog.
	 * 
	 * @param owner the frame that called this method.
	 */
	void insertNewItem(final Frame owner);
	
	/**
	 * Opens the edit item dialog.
	 * 
	 * @param owner the frame that called this method
	 * @param id the uuid of the item to edit.
	 */
	void editExistingItem(Frame owner, UUID id);
	
	/**
	 * Opens the remove item dialog.
	 * 
	 * @param owner the frame that called this method
	 * @param id the uuid of the item to remove.
	 */
	void removeExistingItem(Frame owner, UUID id);
	
	/**
	 * Gets a user readable name for a single record.
	 * 
	 * @return the name of a single record.
	 */
	String getRecordName();
	
	/**
	 * Gets a user readable name of a collection of records.
	 * 
	 * @return the name of a group of records.
	 */
	default String getRecordNamePlural()
	{
		return this.getRecordName() + "s";
	}
	
	/**
	 * Gets the Record cache for this controller.
	 * 
	 * @return this controller's RecordCache 
	 */
	RecordCache<E> getRecordCache();
	
	/**
	 * Overridable method run when the application is shutting down.
	 */
	default void dispose() {}
}
