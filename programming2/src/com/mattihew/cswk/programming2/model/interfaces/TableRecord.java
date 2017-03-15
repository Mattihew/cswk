package com.mattihew.cswk.programming2.model.interfaces;

public interface TableRecord
{
	/**
	 * Gets a value from this object based on the table column index
	 * 
	 * @param columnIndex the index of the table column this object is displayed in.
	 * @return the value at that the specified index.
	 */
	Object getValueAt(final int columnIndex);
	
	/**
	 * Gets the number of columns that should be displayed for this item.
	 * 
	 * @return the number of columns.
	 */
	int getColumnCount();
}
