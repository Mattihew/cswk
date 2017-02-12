package com.mattihew.cswk.programming2.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.mattihew.cswk.programming2.model.interfaces.TableRecord;
import com.mattihew.cswk.programming2.model.interfaces.TripProvider;

public class Trip implements TableRecord
{
	private final Collection<UUID> bookings;
	
	private final String destination;
	
	private final TripProvider tripProvider;
	
	private final String accommodation;
	
	
	private Trip(final String destination, final TripProvider tripProvider, final Collection<UUID> bookingIds, final String accommodation)
	{
		super();
		this.bookings = bookingIds;
		this.destination = destination;
		this.tripProvider = tripProvider;
		this.accommodation = accommodation;
	}
	
	public String getDestination()
	{
		return this.destination;
	}
	
	public Collection<UUID> getBookingIds()
	{
		return Collections.unmodifiableCollection(this.bookings);
	}

	@Override
	public List<Object> toTableColumnValues()
	{
		final List<Object> result = new ArrayList<>();
		result.add(this.destination);
		result.add(this.tripProvider);
		return result;
	}
	
	public static class TripBuilder
	{
		private Collection<UUID> bookings = Collections.emptyList();
		
		private String destination = "";
		
		private TripProvider tripProvider = null;
		
		private String accommodation = null;
		
		public TripBuilder() {}
		
		public TripBuilder copyFrom(final Trip trip)
		{
			this.bookings = trip.bookings;
			this.destination = trip.destination;
			this.tripProvider = trip.tripProvider;
			this.accommodation = trip.accommodation;
			return this;
		}
		
		public TripBuilder setBookings(final Collection<UUID> bookings)
		{
			this.bookings = bookings;
			return this;
		}

		public TripBuilder setDestination(final String destination)
		{
			this.destination = destination;
			return this;
		}

		public TripBuilder setTripProvider(final TripProvider tripProvider)
		{
			this.tripProvider = tripProvider;
			return this;
		}

		public TripBuilder setAccommodation(final String accommodation)
		{
			this.accommodation = accommodation;
			return this;
		}

		public Trip toTrip()
		{
			return this.build();
		}
		
		public Trip build()
		{
			return new Trip(this.destination, this.tripProvider, this.bookings, this.accommodation);
		}
	}
}
