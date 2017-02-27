package com.mattihew.cswk.programming2.model.abstracts;

import java.util.Objects;

import com.mattihew.cswk.programming2.model.interfaces.TableRecord;

/**
 * This class represents a person within the system.
 * 
 * @author Matt Rayner
 */
public abstract class Person implements TableRecord
{
	/** this persons first name.*/
	private final String firstName;
	/** this persons last name.*/
	private final String lastName;
	/** the cached hash of this person.*/
	private int lazyHash;
	
	/**
	 * Class Constructor.
	 *
	 * @param firstName the first name to use for this person.
	 * @param lastName the last name to use for this person.
	 */
	public Person(final String firstName, final String lastName)
	{
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	/**
	 * gets the first name of this person.
	 * 
	 * @return this person's first name.
	 */
	public String getFirstName()
	{
		return this.firstName;
	}
	
	/**
	 * gets the last name of this person.
	 * 
	 * @return this person's last name.
	 */
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
		return String.join(" ", this.firstName, this.lastName);
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
	
	/**
	 * method that sub-classes can use when implementing {@link Comparable}.
	 * 
	 * Sub classes should make a call to this method before adding their own comparison logic.
	 * For example: <br>
	 * <code>
	 * public int compareTo(&lt? extends Person&gt o) {<br>
	 * .   int result = super.compareTo(anotherStudent);<br>
	 * .   if (result != 0) {<br>
	 * .   .  return result;<br>
	 * .   }<br>
	 * }<br>
	 * </code>
	 * 
	 * @param anotherPerson the other person to compare against
	 * @return result as specified by {@link Comparable#compareTo}
	 */
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

	@Override
	public Object getValueAt(final int columnIndex)
	{
		switch (columnIndex)
		{
			case 0:
				return this.firstName;
			case 1:
				return this.lastName;
			default:
				return null;
		}
	}

	@Override
	public int getColumnCount()
	{
		return 2;
	}
	
}
