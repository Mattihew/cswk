package com.mattihew.cswk.programming2.model;

import com.mattihew.cswk.programming2.model.abstracts.Person;
import com.mattihew.cswk.programming2.model.interfaces.TripProvider;

/**
 * @author Matt Rayner
 */
public class Teacher extends Person
{

	public Teacher(final String firstName, final String lastName)
	{
		super(firstName, lastName);
	}
	
	public static Teacher FromTableColumnValues(final Object[] values)
	{
		return new Teacher((String) values[0], (String) values[1]);
	}
}