package com.mattihew.cswk.programming2.model.trip;

public class ResidentialTrip extends TripWrapper
{
	private final String accomodation;
	
	public ResidentialTrip(final Trip trip, final String accomodation)
	{
		super(trip);
		this.accomodation = accomodation;
	}
	
	public String getAccomodation()
	{
		return this.accomodation;
	}

}
