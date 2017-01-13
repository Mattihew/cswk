package com.mattihew.cswk.programming2.model;

import java.util.Objects;

import com.mattihew.cswk.programming2.model.students.Student;

public class Booking implements Comparable<Booking>
{
	private final Student student;
	
	private final boolean paid;
	
	private final boolean permisionRecieved;
	
	public Booking(final Student student, final boolean hasPaid, final boolean hasPermission)
	{
		super();
		this.student = student;
		this.paid = hasPaid;
		this.permisionRecieved = hasPermission;
	}
	
	public Booking(final Student student, final boolean hasPaid)
	{
		this(student, hasPaid, false);
	}
	
	public Student getStudent()
	{
		return this.student;
	}
	
	public boolean hasPaid()
	{
		return this.paid;
	}
	
	public boolean permisionRecieved()
	{
		return this.permisionRecieved;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(final Booking otherBooking)
	{
		return this.student.compareTo(otherBooking.student);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return Objects.hash(this.student, this.paid, this.permisionRecieved);
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
		else if (!(other instanceof Booking))
		{
			result = false;
		}
		else
		{
			final Booking otherBooking = (Booking) other;
			result = this.student.equals(otherBooking.student);
		}
		return result;
	}
	

}
