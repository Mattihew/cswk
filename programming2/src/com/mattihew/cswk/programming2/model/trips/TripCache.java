package com.mattihew.cswk.programming2.model.trips;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.UUID;

public class TripCache extends Observable
{
	private static TripCache INSTANCE = null;
	
	private final Map<UUID, Trip> trips;
	
	private TripCache()
	{
		super();
		this.trips = new HashMap<>();
	}
	
	public static TripCache getInstance()
	{
		if (TripCache.INSTANCE == null)
		{
			TripCache.INSTANCE = new TripCache();
		}
		return TripCache.INSTANCE;
	}
	
	public Trip getTrip(final UUID id)
	{
		return this.trips.get(id);
	}
	
	public Map<UUID, Trip> getTrips()
	{
		return Collections.unmodifiableMap(this.trips);
	}
	
	public Trip removeTrip(final UUID id)
	{
		final Trip result = this.trips.remove(id);
		this.setChanged();
		this.notifyObservers(Collections.singleton(id));
		return result;
	}
	
	public UUID addTrip(final Trip trip)
	{
		if (this.trips.containsValue(trip))
		{
			return null;
		}
		final UUID tripID = UUID.randomUUID();
		this.trips.put(tripID, trip);
		this.setChanged();
		this.notifyObservers(Collections.singleton(tripID));
		return tripID;
	}
	
	public Trip putTrip(final UUID id, final Trip trip)
	{
		final Trip result = this.trips.put(id, trip);
		if (!trip.equals(result))
		{
			this.setChanged();
			this.notifyObservers(Collections.singleton(id));
		}
		return result;
	}
}
