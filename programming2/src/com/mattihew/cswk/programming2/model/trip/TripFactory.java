package com.mattihew.cswk.programming2.model.trip;

import com.mattihew.cswk.programming2.model.booking.Booking;
import com.mattihew.cswk.programming2.model.interfaces.TripProvider;
import com.mattihew.cswk.programming2.model.tableModel.RecordCache;

public class TripFactory
{
	public Trip getStandardTrip(final String name, final String destination, final TripProvider tripProvider, final RecordCache<Booking> bookingIds, final String accommodation)
	{
		return new StandardTrip(name, destination, tripProvider, bookingIds, accommodation);
	}
}
