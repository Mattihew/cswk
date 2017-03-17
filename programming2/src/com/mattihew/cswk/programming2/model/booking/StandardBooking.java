package com.mattihew.cswk.programming2.model.booking;

import java.util.Objects;

import com.mattihew.cswk.programming2.model.Student;
import com.mattihew.cswk.programming2.model.interfaces.TableRecord;
import com.mattihew.cswk.programming2.model.trip.Trip;

public class StandardBooking implements Comparable<StandardBooking>, Booking, TableRecord
{
	private final Student student;
	
	private final Trip trip;
	
	private final long amountPaid;
	
	public StandardBooking(final Student student, final Trip trip, final long pennysPaid)
	{
		super();
		this.student = student;
		this.trip = trip;
		this.amountPaid = pennysPaid;
	}
	
	@Override
	public Student getStudent()
	{
		return this.student;
	}
	
	@Override
	public Trip getTrip()
	{
		return this.trip;
	}
	
	@Override
	public long getAmountPaid()
	{
		return this.amountPaid;
	}
	
	@Override
	public boolean isApproved()
	{
		return true;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(final StandardBooking otherBooking)
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
		return Objects.hash(this.student, Long.valueOf(this.amountPaid));
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
		else if (!(other instanceof StandardBooking))
		{
			result = false;
		}
		else
		{
			final StandardBooking otherBooking = (StandardBooking) other;
			result = this.student.equals(otherBooking.student);
		}
		return result;
	}

	@Override
	public Object getValueAt(final int columnIndex)
	{
		switch (columnIndex)
		{
			case 0:
				return this.student;
			case 1:
				return Long.valueOf(this.amountPaid);
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
