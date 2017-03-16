package com.mattihew.cswk.programming2.model.trip;

import java.util.Collection;
import java.util.UUID;

public class TripWrapper implements Trip
{
	private final Trip trip;
	
	public TripWrapper(final Trip trip)
	{
		this.trip = trip;
	}
	
	@Override
	public String getDestination()
	{
		return this.trip.getDestination();
	}

	@Override
	public Collection<UUID> getBookingIds()
	{
		return this.trip.getBookingIds();
	}

	@Override
	public int getCost()
	{
		return this.trip.getCost();
	}
}
