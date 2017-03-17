package com.mattihew.cswk.programming2.model.booking;

import com.mattihew.cswk.programming2.model.Student;
import com.mattihew.cswk.programming2.model.trip.Trip;

public interface Booking
{
	Student getStudent();
	
	Trip getTrip();
	
	long getAmountPaid();
	
	boolean isApproved();
}
