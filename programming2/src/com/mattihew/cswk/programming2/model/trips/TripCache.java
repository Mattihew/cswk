package com.mattihew.cswk.programming2.model.trips;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class TripCache
{
	private static TripCache INSTANCE = null;
	
	private Set<Trip> trips;
	
	private TripCache()
	{
		this.trips = new HashSet<>();
	}
	
	public static TripCache getInstance()
	{
		if (TripCache.INSTANCE == null)
		{
			TripCache.INSTANCE = new TripCache();
		}
		return TripCache.INSTANCE;
	}
	
	public Set<Trip> getTrips()
	{
		return Collections.unmodifiableSet(this.trips);
	}
	
	public boolean addTrip(final Trip trip)
	{
		return this.trips.add(trip);
	}
}
