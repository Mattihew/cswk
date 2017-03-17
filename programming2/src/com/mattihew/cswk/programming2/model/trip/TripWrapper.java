package com.mattihew.cswk.programming2.model.trip;

import java.util.Collection;
import java.util.UUID;

import com.mattihew.cswk.programming2.model.interfaces.TripProvider;

public class TripWrapper implements Trip
{
	private final Trip trip;
	
	public TripWrapper(final Trip trip)
	{
		this.trip = trip;
	}
	
	@Override
	public String getName()
	{
		return this.trip.getName();
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

	@Override
	public Object getValueAt(int columnIndex)
	{
		return this.trip.getValueAt(columnIndex);
	}

	@Override
	public int getColumnCount()
	{
		return this.trip.getColumnCount();
	}

	@Override
	public TripProvider getTripProvider()
	{
		return this.trip.getTripProvider();
	}
}
