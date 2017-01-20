package com.mattihew.cswk.programming2.controller.interfaces;

import java.util.UUID;

public interface UIController<E>
{
	void createRecord(final E element);
	
	void createRecord(final E element, final UUID id);
}
