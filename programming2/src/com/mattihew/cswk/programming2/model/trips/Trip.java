package com.mattihew.cswk.programming2.model.trips;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.mattihew.cswk.programming2.model.Booking;
import com.mattihew.cswk.programming2.model.RecordCache;
import com.mattihew.cswk.programming2.model.interfaces.TableRecord;
import com.mattihew.cswk.programming2.model.interfaces.TripProvider;

public class Trip implements TableRecord
{
	private final RecordCache<Booking> bookings;
	
	private final String destination;
	
	private final TripProvider tripProvider;
	
	public Trip(final String destination, final TripProvider tripProvider)
	{
		super();
		this.bookings = new RecordCache<>();
		this.destination = destination;
		this.tripProvider = tripProvider;
	}
	
	public String getDestination()
	{
		return this.destination;
	}
	
	/**
	 * Add booking to trip.
	 * 
	 * @param booking the booking to add.
	 * @return the <tt>UUID</tt> assigned to the booking or <tt>null</tt> if not stored.
	 */
	public UUID addBooking(final Booking booking)
	{
		return this.bookings.addRecord(booking);
	}
	
	/**
	 * Returns an unmodifiable view of the bookings for this Trip.
	 * 
	 * @return bookings for trip.
	 */
	public RecordCache<Booking> getBookings()
	{
		return this.bookings;
	}

	@Override
	public List<Object> toTableColumnValues()
	{
		final List<Object> result = new ArrayList<>();
		result.add(this.destination);
		result.add(this.tripProvider);
		return result;
	}
	
	@Override
	public String toString()
	{
		return this.destination;
	}
}
