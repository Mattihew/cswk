package com.mattihew.cswk.programming2.model.booking;

public class ResidentialTripBookingWrapper extends BookingWrapper
{
	private final boolean permissionRecieved;
	
	public ResidentialTripBookingWrapper(final Booking booking, final boolean authorised)
	{
		super(booking);
		this.permissionRecieved = authorised;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see com.mattihew.cswk.programming2.model.booking.BookingWrapper#isApproved()
	 */
	@Override
	public boolean isApproved()
	{
		return this.permissionRecieved && super.isApproved();
	}
}
