package com.mattihew.cswk.programming2.model.booking;

import com.mattihew.cswk.programming2.model.Student;
import com.mattihew.cswk.programming2.model.trip.Trip;

public class BookingWrapper implements Booking
{
	private final Booking booking;
	
	public BookingWrapper(final Booking booking)
	{
		this.booking = booking;
	}
	
	@Override
	public Student getStudent()
	{
		return this.booking.getStudent();
	}

	@Override
	public Trip getTrip()
	{
		return this.booking.getTrip();
	}

	@Override
	public long getAmountPaid()
	{
		return this.booking.getAmountPaid();
	}

	@Override
	public boolean isApproved()
	{
		return this.booking.isApproved();
	}

}
