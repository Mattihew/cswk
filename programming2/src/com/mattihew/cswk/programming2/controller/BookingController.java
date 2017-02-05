package com.mattihew.cswk.programming2.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.mattihew.cswk.programming2.controller.undo.UndoController;
import com.mattihew.cswk.programming2.model.Booking;
import com.mattihew.cswk.programming2.model.RecordCache;
import com.mattihew.cswk.programming2.model.students.Student;
import com.mattihew.cswk.programming2.model.trips.Trip;

public class BookingController extends TablePanelUIController<Booking>
{
	private static final List<String> TABLE_HEADINGS = Arrays.asList("Student","Trip", "has paid", "has Permission");
	private final String tripName;
	private final RecordCache<Booking> bookings;
	private final Collection<Student> students;
	
	public BookingController(final UndoController undoController, final Trip trip, final Collection<Student> students)
	{
		super(undoController);
		this.tripName = trip.getDestination();
		this.bookings = trip.getBookings();
		this.students = students;
	}
	
	@Override
	public void createRecord(final Object[] elementValues, final UUID id)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editRecord(final UUID id, final Object[] elementValues)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getRecordName()
	{
		return this.tripName + " Booking";
	}

	@Override
	public String getRecordNamePlural()
	{
		return this.tripName + " Bookings";
	}

	@Override
	public RecordCache<Booking> getRecordCache()
	{
		return this.bookings;
	}

	@Override
	public List<String> getTableHeadings()
	{
		return BookingController.TABLE_HEADINGS;
	}

	@Override
	public String[] comboOptions(final int attributeIndex)
	{
		switch (BookingController.TABLE_HEADINGS.get(attributeIndex))
		{
			case "Student":
				return BookingController.toStringArray(this.students);
			case "Trip":
				return null;
			default:
				return new String[] {Boolean.toString(true), Boolean.toString(false)};
		}
	}
	
	private static String[] toStringArray(final Collection<? extends Object> objects)
	{
		final String[] result = new String[objects.size()];
		int i = 0;
		for (final Object object : objects)
		{
			result[i++] = object.toString();
		}
		return result;
	}
}
