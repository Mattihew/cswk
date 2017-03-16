package com.mattihew.cswk.programming2.model.trip;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import com.mattihew.cswk.programming2.model.interfaces.TableRecord;
import com.mattihew.cswk.programming2.model.interfaces.TripProvider;

public class StandardTrip implements Trip, TableRecord
{
	private final Collection<UUID> bookings;
	
	private final String destination;
	
	private final TripProvider tripProvider;
	
	private final String accommodation;
	
	private final int cost;
	
	StandardTrip(final String destination, final TripProvider tripProvider, final Collection<UUID> bookingIds, final String accommodation)
	{
		super();
		this.bookings = bookingIds;
		this.destination = destination;
		this.tripProvider = tripProvider;
		this.accommodation = accommodation;
		this.cost = 0;
	}
	
	@Override
	public String getDestination()
	{
		return this.destination;
	}
	
	@Override
	public Collection<UUID> getBookingIds()
	{
		return Collections.unmodifiableCollection(this.bookings);
	}
	
	@Override
	public int getCost()
	{
		return this.cost;
	}
	
	@Override
	public Object getValueAt(final int columnIndex)
	{
		switch (columnIndex)
		{
			case 0:
				return this.destination;
			case 1:
				return this.tripProvider;
			case 2:
				return this.accommodation;
			default:
				return null;
		}
	}

	@Override
	public int getColumnCount()
	{
		return 3;
	}

	public static class TripBuilder
	{
		private Collection<UUID> bookings = Collections.emptyList();
		
		private String destination = "";
		
		private TripProvider tripProvider = null;
		
		private String accommodation = null;
		
		public TripBuilder copyFrom(final StandardTrip trip)
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

		public StandardTrip toTrip()
		{
			return this.build();
		}
		
		public StandardTrip build()
		{
			return new StandardTrip(this.destination, this.tripProvider, this.bookings, this.accommodation);
		}
	}
}
