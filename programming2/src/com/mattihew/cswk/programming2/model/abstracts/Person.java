package com.mattihew.cswk.programming2.model.abstracts;

import java.util.Objects;

/**
 * @author Matt Rayner
 */
public abstract class Person
{
	private final String firstName;
	
	private final String lastName;
	
	private int lazyHash;
	
	public Person(final String firstName, final String lastName)
	{
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getFirstName()
	{
		return this.firstName;
	}
	
	public String getLastName()
	{
		return this.lastName;
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return String.join("/n", this.firstName, this.lastName);
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		if (this.lazyHash == 0)
		{
			this.lazyHash = Objects.hash(this.firstName, this.lastName);
		}
		return this.lazyHash;
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other)
	{
		boolean result;
		if(other == this)
		{
			result = true;
		}
		else if (!(other instanceof Person))
		{
			result = false;
		}
		else
		{
			final Person otherStudent = (Person) other;
			result = this.firstName.equals(otherStudent.firstName);
			result &= this.lastName.equals(otherStudent.lastName);
		}
		return result;
	}
	
	public int compareTo(final Person anotherPerson)
	{
		int result = this.firstName.compareTo(anotherPerson.firstName);
		if (result != 0)
		{
			return result;
		}
		result = this.lastName.compareTo(anotherPerson.lastName);
		
		return result;
	}
}
