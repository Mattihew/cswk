package com.mattihew.cswk.programming2.model.interfaces;

public interface TableRecord
{
	Object getValueAt(final int columnIndex);
	
	int getColumnCount();
}
