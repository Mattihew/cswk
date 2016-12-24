package com.mattihew.cswk.programming2.model.trips;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.mattihew.cswk.programming2.model.Booking;
import com.mattihew.cswk.programming2.model.Student;

public class Trip
{
	private final Set<Booking> bookings;
	
	private final String destination;
	
	private final TripType tripType;
	
	private final TripProvider tripProvider;
	
	public Trip(final String destination, final TripType tripType, final TripProvider tripProvider)
	{
		super();
		this.bookings = new HashSet<>();
		this.destination = destination;
		this.tripType = tripType;
		this.tripProvider = tripProvider;
	}
	
	/**
	 * Add booking to trip.
	 * 
	 * @param booking the booking to add.
	 * @return <tt>true</tt> (as specified by {@link Collection#add})
	 */
	public boolean addBooking(final Booking booking)
	{
		return this.bookings.add(booking);
	}
	
	/**
	 * Remove booking from trip.
	 * 
	 * @param booking the booking to remove.
	 * @return <tt>true</tt> (as specified by {@link Collection#remove})
	 */
	public boolean removeBooking(final Booking booking)
	{
		return this.bookings.remove(booking);
	}
	
	public Set<Booking> getBookings()
	{
		return Collections.unmodifiableSet(this.bookings);
	}
	
	public boolean addStudent(final Student student)
	{
		if(!this.containsStudent(student))
		{
			return this.addBooking(new Booking(student));
		}
		return false;
	}
	
	public boolean removeStudent(final Student student)
	{
		for (final Iterator<Booking> i = this.bookings.iterator(); i.hasNext();)
		{
			if(i.next().getStudent().equals(student))
			{
				i.remove();
				return true;
			}
		}
		return false;
	}
	
	public boolean containsStudent(final Student student)
	{
		for (final Booking booking : this.bookings)
		{
			if (booking.getStudent().equals(student))
			{
				return true;
			}
		}
		return false;
	}
}
