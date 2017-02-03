package com.mattihew.cswk.programming2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.mattihew.cswk.programming2.model.interfaces.TableRecord;
import com.mattihew.cswk.programming2.model.students.Student;
import com.mattihew.cswk.programming2.model.trips.Trip;

public class Booking implements Comparable<Booking>, TableRecord
{
	private final Student student;
	
	private final Trip trip;
	
	private final boolean paid;
	
	private final Boolean permisionRecieved;
	
	public Booking(final Student student, final Trip trip, final boolean hasPaid)
	{
		this(student, trip, hasPaid, null);
	}
	
	public Booking(final Student student, final Trip trip, final boolean hasPaid, final Boolean hasPermission)
	{
		super();
		this.student = student;
		this.trip = trip;
		this.paid = hasPaid;
		this.permisionRecieved = hasPermission;
	}
	
	public Student getStudent()
	{
		return this.student;
	}
	
	public Trip getTrip()
	{
		return this.trip;
	}
	
	public boolean hasPaid()
	{
		return this.paid;
	}
	
	public Boolean permisionRecieved()
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

	@Override
	public List<Object> toTableColumnValues()
	{
		final List<Object> result = new ArrayList<>();
		result.add(this.student);
		result.add(this.trip);
		result.add(this.paid);
		result.add(this.permisionRecieved);
		return result;
	}
}
