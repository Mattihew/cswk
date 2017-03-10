package com.mattihew.cswk.programming2.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.swing.table.TableModel;

import com.mattihew.cswk.programming2.controller.undo.UndoController;
import com.mattihew.cswk.programming2.model.Booking;
import com.mattihew.cswk.programming2.model.Student;
import com.mattihew.cswk.programming2.model.Trip;
import com.mattihew.cswk.programming2.model.tableModel.RecordCache;
import com.mattihew.cswk.programming2.model.tableModel.RecordCacheTableModel;

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
		this.bookings = null;
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
	public RecordCacheTableModel getTableModel()
	{
		return new RecordCacheTableModel(this.bookings, new String[]{}, new Class<?>[]{});
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
