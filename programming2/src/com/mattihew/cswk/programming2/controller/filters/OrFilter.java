package com.mattihew.cswk.programming2.controller.filters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class OrFilter<E> implements Filter<E>
{
	private final Collection<Filter<E>> filters;
	@SafeVarargs
	public OrFilter(final Filter<E>... filters)
	{
		this.filters = Arrays.asList(filters);
	}
	
	public OrFilter(final Collection<Filter<E>> filters)
	{
		this.filters = filters;
	}
	
	@Override
	public List<E> filter(final Collection<E> elements)
	{
		List<E> results = new ArrayList<>();
		for (final Filter<E> filter : this.filters)
		{
			results.addAll(filter.filter(elements));
		}
		return results;
	}

}
