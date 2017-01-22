package com.mattihew.cswk.programming2.controller.filters;

import java.util.Collection;
import java.util.List;

public interface Filter<E>
{
	List<E> filter(final Collection<E> elements);
}
