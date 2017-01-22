package com.mattihew.cswk.programming2.controller.interfaces;

import java.awt.Frame;
import java.awt.Panel;
import java.util.UUID;

public interface UIController<E>
{
	String getItemName();
	
	void createRecord(final E element);
	
	void createRecord(final E element, final UUID id);
	
	void removeRecord(final UUID id);
	
	Panel getUIPanel(final Frame owner);
	
	void insertNewItem(final Frame owner);
}
