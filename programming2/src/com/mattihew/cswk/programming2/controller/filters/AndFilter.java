package com.mattihew.cswk.programming2.controller.filters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class AndFilter<E> implements Filter<E>
{
	private final Collection<Filter<E>> filters;
	@SafeVarargs
	public AndFilter(final Filter<E>... filters)
	{
		this.filters = Arrays.asList(filters);
	}
	
	public AndFilter(final Collection<Filter<E>> filters)
	{
		this.filters = filters;
	}
	
	@Override
	public List<E> filter(final Collection<E> elements)
	{
		List<E> results = new ArrayList<>(elements);
		for (final Filter<E> filter : this.filters)
		{
			results = filter.filter(results);
		}
		return results;
	}

}
