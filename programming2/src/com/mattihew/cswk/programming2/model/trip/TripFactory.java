package com.mattihew.cswk.programming2.model.trip;

import java.util.Collection;
import java.util.UUID;

import com.mattihew.cswk.programming2.model.interfaces.TripProvider;

public class TripFactory
{
	public Trip getStandardTrip(final String destination, final TripProvider tripProvider, final Collection<UUID> bookingIds, final String accommodation)
	{
		return new StandardTrip(destination, tripProvider, bookingIds, accommodation);
	}
}
