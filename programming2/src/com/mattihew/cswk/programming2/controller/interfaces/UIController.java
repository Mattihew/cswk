package com.mattihew.cswk.programming2.controller.interfaces;

import java.util.UUID;

import com.mattihew.cswk.programming2.controller.undo.UndoController;

public interface UIController<E>
{
	void createRecord(final E element);
	
	void createRecord(final E element, final UUID id);
	
	void removeRecord(final UUID id);
	
	UndoController getUndoController();
}
