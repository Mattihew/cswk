package com.mattihew.cswk.programming2.model;

import java.util.Objects;

public class Student implements Comparable<Student>
{
	private final String firstName;
	
	private final String lastName;
	
	private final String phone;
	
	/**
	 * Class Constructor.
	 *
	 * @param name the name of the student.
	 * @param phone the phone number of the student.
	 */
	public Student(final String firstName, final String lastName, final String phone)
	{
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return String.join("/n", this.firstName, this.lastName, this.phone);
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return Objects.hash(this.firstName, this.lastName, this.phone);
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
		else if (!(other instanceof Student))
		{
			result = false;
		}
		else
		{
			final Student otherStudent = (Student) other;
			result = this.firstName.equals(otherStudent.firstName);
			result &= this.lastName.equals(otherStudent.lastName);
			result &= this.phone.equals(otherStudent.phone);
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(final Student anotherStudent)
	{
		int result = this.firstName.compareTo(anotherStudent.firstName);
		if (result != 0)
		{
			return result;
		}
		result = this.lastName.compareTo(anotherStudent.lastName);
		if (result != 0)
		{
			return result;
		}
		result = this.phone.compareTo(anotherStudent.phone);
		
		return result;
	}
}
