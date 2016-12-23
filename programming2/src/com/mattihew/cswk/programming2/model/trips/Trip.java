package com.mattihew.cswk.programming2.model.trips;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.mattihew.cswk.programming2.model.Student;

public abstract class Trip
{
	private final Set<Student> students = new HashSet<>();
	
	private final String destination;
	
	private final TripType tripType;
	
	private final TripProvider tripProvider;
	
	public Trip(final String destination, final TripType tripType, final TripProvider tripProvider)
	{
		this.destination = destination;
		this.tripType = tripType;
		this.tripProvider = tripProvider;
	}
	
	/**
	 * Add student to trip.
	 * 
	 * @param student the student to add.
	 * @return <tt>true</tt> (as specified by {@link Collection#add})
	 */
	public boolean addStudent(final Student student)
	{
		return this.students.add(student);
	}
	
	/**
	 * Remove student from trip.
	 * 
	 * @param student the student to remove.
	 * @return <tt>true</tt> (as specified by {@link Collection#remove})
	 */
	public boolean removeStudent(final Student student)
	{
		return this.students.remove(student);
	}
}
