package com.mattihew.cswk.programming2.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.mattihew.cswk.programming2.controller.undo.UndoController;
import com.mattihew.cswk.programming2.model.Student;
import com.mattihew.cswk.programming2.model.abstracts.RecordStorage;
import com.mattihew.cswk.programming2.model.booking.StandardBooking;
import com.mattihew.cswk.programming2.model.tableModel.RecordCache;
import com.mattihew.cswk.programming2.model.tableModel.RecordCacheTableModel;
import com.mattihew.cswk.programming2.model.trip.StandardTrip;
import com.mattihew.cswk.programming2.util.ArrayUtils;

/**
 * Controller for the Bookings of a Trip.
 * 
 * @author Matt Rayner
 */
public class BookingController extends TablePanelUIController<StandardBooking>
{
	private static final List<String> TABLE_HEADINGS = Arrays.asList("Student","Trip", "has paid", "has Permission");
	private final String tripName;
	private final RecordCache<StandardBooking> bookings;
	private final Collection<Student> students;
	private final RecordStorage<StandardBooking> bookingStorage;
	
	/**
	 * Class Constructor
	 *
	 * @param undoController the undocontroller to use for doing actions.
	 * @param trip the trip to get the bookings for.
	 * @param students the students to look values from.
	 */
	public BookingController(final UndoController undoController, final StandardTrip trip, final Collection<Student> students)
	{
		super(undoController);
		this.tripName = trip.getDestination();
		this.bookings = new RecordCache<>();
		this.students = students;
		this.bookingStorage = new BookingStorage(String.format("./data/%s_bookings.csv", this.tripName));
		this.bookings.putRecords(this.bookingStorage.readRecords());
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
	public RecordCache<StandardBooking> getRecordCache()
	{
		return this.bookings;
	}
	
	@Override
	public RecordCacheTableModel getTableModel()
	{
		return new RecordCacheTableModel(this.bookings, new String[]{"Student", "amount paid", "permission recieved"}, new Class<?>[]{String.class, Integer.class, Boolean.class});
	}

	@Override
	public String[] comboOptions(final int attributeIndex)
	{
		switch (BookingController.TABLE_HEADINGS.get(attributeIndex))
		{
			case "Student":
				return ArrayUtils.toStringArray(this.students);
			case "Trip":
				return null;
			default:
				return new String[] {Boolean.toString(true), Boolean.toString(false)};
		}
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @see com.mattihew.cswk.programming2.controller.interfaces.UIController#dispose()
	 */
	@Override
	public void dispose()
	{
		super.dispose();
		this.bookingStorage.writeRecords(this.bookings.getRecords());
	}



	private class BookingStorage extends RecordStorage<StandardBooking>
	{
		public BookingStorage(final String filePath)
		{
			super(filePath);
		}

		@Override
		protected void writeRecord(final Appendable output, final StandardBooking record) throws IOException
		{
			
		}

		@Override
		protected StandardBooking readRecord(final String readLine)
		{
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
