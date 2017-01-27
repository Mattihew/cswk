package com.mattihew.cswk.programming2.model.students;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.mattihew.cswk.programming2.model.abstracts.Person;
import com.mattihew.cswk.programming2.model.interfaces.TableRecord;

public class Student extends Person implements Comparable<Student>
{
	private final String phone;
	
	private List<Object> tableColumns;
	
	/**
	 * Class Constructor.
	 *
	 * @param name the name of the student.
	 * @param phone the phone number of the student.
	 */
	public Student(final String firstName, final String lastName, final String phone)
	{
		super(firstName, lastName);
		this.phone = phone;
	}
	
	public static Student fromTableColumnValues(final Object[] values)
	{
		return new Student((String) values[0], (String) values[1], (String) values[2]);
	}
	
	public String getPhoneNum()
	{
		return this.phone;
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return String.join("\n", super.toString(), this.phone);
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return Objects.hash(super.hashCode(), this.phone);
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
			result = super.equals(otherStudent);
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
		int result = super.compareTo(anotherStudent);
		if (result != 0)
		{
			return result;
		}
		result = this.phone.compareTo(anotherStudent.phone);
		return result;
	}

	@Override
	public List<Object> toTableColumnValues()
	{
		if (Objects.isNull(this.tableColumns))
		{
			this.tableColumns = super.toTableColumnValues();
			this.tableColumns.add(this.phone);
		}
		return Collections.unmodifiableList(this.tableColumns);
	}
}
