package com.mattihew.cswk.programming2.model.trip;

import java.util.Collection;
import java.util.UUID;

import com.mattihew.cswk.programming2.model.booking.Booking;
import com.mattihew.cswk.programming2.model.interfaces.TripProvider;
import com.mattihew.cswk.programming2.model.tableModel.RecordCache;

public class StandardTrip implements Trip
{
	private final String name;
	
	private final RecordCache<Booking> bookings;
	
	private final String destination;
	
	private final TripProvider tripProvider;
	
	private final String accommodation;
	
	private final int cost;
	
	StandardTrip(final String name, final String destination, final TripProvider tripProvider, final RecordCache<Booking> bookingIds, final String accommodation)
	{
		super();
		this.name = name;
		this.bookings = bookingIds;
		this.destination = destination;
		this.tripProvider = tripProvider;
		this.accommodation = accommodation;
		this.cost = 0;
	}
	
	@Override
	public String getName()
	{
		return this.name;
	}
	
	@Override
	public String getDestination()
	{
		return this.destination;
	}
	
	@Override
	public TripProvider getTripProvider()
	{
		return this.tripProvider;
	}
	
	@Override
	public Collection<UUID> getBookingIds()
	{
		return this.bookings.getIDs();
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
		private String name = "";
		
		private RecordCache<Booking> bookings = new RecordCache<>();
		
		private String destination = "";
		
		private TripProvider tripProvider = null;
		
		private String accommodation = null;
		
		public TripBuilder copyFrom(final StandardTrip trip)
		{
			this.name = trip.name;
			this.bookings = trip.bookings;
			this.destination = trip.destination;
			this.tripProvider = trip.tripProvider;
			this.accommodation = trip.accommodation;
			return this;
		}
		
		public TripBuilder setName(final String name)
		{
			this.name = name;
			return this;
		}
		
		public TripBuilder addBookings(final Booking booking)
		{
			this.bookings.addRecord(booking);
			return this;
		}
		
		public TripBuilder putBookings(final UUID id, final Booking booking)
		{
			this.bookings.putRecord(id, booking);
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
			return new StandardTrip(this.name, this.destination, this.tripProvider, this.bookings, this.accommodation);
		}
	}
}
