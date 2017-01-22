package com.mattihew.cswk.programming2.controller.filters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.mattihew.cswk.programming2.model.abstracts.Person;

public class FirstNameContainsFilter implements Filter<Person>
{
	private final String searchString;
	public FirstNameContainsFilter(final String searchString)
	{
		this.searchString = searchString;
	}
	
	@Override
	public List<Person> filter(final Collection<Person> elements)
	{
		final List<Person> results = new ArrayList<>();
		for (final Person person : elements)
		{
			if (person.getFirstName().contains(this.searchString))
			{
				results.add(person);
			}
		}
		return results;
	}

}
