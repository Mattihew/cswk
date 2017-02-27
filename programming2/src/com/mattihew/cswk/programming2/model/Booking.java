package com.mattihew.cswk.programming2.model;

import java.util.Objects;

import com.mattihew.cswk.programming2.model.interfaces.TableRecord;

public class Booking implements Comparable<Booking>, TableRecord
{
	private final Student student;
	
	private final Trip trip;
	
	private final long amountPaid;
	
	private final Boolean permisionRecieved;
	
	public Booking(final Student student, final Trip trip, final long pennysPaid)
	{
		this(student, trip, pennysPaid, null);
	}
	
	public Booking(final Student student, final Trip trip, final long pennysPaid, final Boolean hasPermission)
	{
		super();
		this.student = student;
		this.trip = trip;
		this.amountPaid = pennysPaid;
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
	
	public long getAmountPaid()
	{
		return this.amountPaid;
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
		return Objects.hash(this.student, this.amountPaid, this.permisionRecieved);
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
	public Object getValueAt(final int columnIndex)
	{
		switch (columnIndex)
		{
			case 0:
				return this.student;
			case 1:
				return Long.valueOf(this.amountPaid);
			case 2:
				return this.permisionRecieved;
			default:
				return null;
		}
	}

	@Override
	public int getColumnCount()
	{
		return 3;
	}


}
