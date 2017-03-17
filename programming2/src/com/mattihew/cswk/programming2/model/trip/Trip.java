package com.mattihew.cswk.programming2.model.trip;

import java.util.Collection;
import java.util.UUID;

import com.mattihew.cswk.programming2.model.interfaces.TableRecord;
import com.mattihew.cswk.programming2.model.interfaces.TripProvider;

public interface Trip extends TableRecord
{
	String getName();
	
	String getDestination();
	
	Collection<UUID> getBookingIds();
	
	int getCost();

	TripProvider getTripProvider();
}
