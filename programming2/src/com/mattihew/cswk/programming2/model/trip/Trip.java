package com.mattihew.cswk.programming2.model.trip;

import java.util.Collection;
import java.util.UUID;

public interface Trip
{
	String getDestination();
	
	Collection<UUID> getBookingIds();
	
	int getCost();
}
