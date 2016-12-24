package com.mattihew.cswk.programming2.model;

public class Booking implements Comparable<Booking>
{
	private final Student student;
	
	public Booking(final Student student)
	{
		super();
		this.student = student;
	}
	
	public Student getStudent()
	{
		return this.student;
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
}
