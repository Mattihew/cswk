package com.mattihew.cswk.programming2.model;

import com.mattihew.cswk.programming2.model.interfaces.TripProvider;

public class TeacherTripProvider implements TripProvider
{
	private final Teacher teacher;
	
	private final String transport;
	
	private final String venue;
	
	public TeacherTripProvider(final Teacher teacher, final String transport, final String venue)
	{
		this.teacher = teacher;
		this.transport = transport;
		this.venue = venue;
	}
	
	public Teacher getTeacher()
	{
		return this.teacher;
	}
	
	@Override
	public String getTransport()
	{
		return this.transport;
	}

	@Override
	public String getVenue()
	{
		return this.venue;
	}
}
