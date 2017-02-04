package com.mattihew.cswk.programming2.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.mattihew.cswk.programming2.controller.undo.CreateRecordAction;
import com.mattihew.cswk.programming2.controller.undo.EditRecordAction;
import com.mattihew.cswk.programming2.controller.undo.RemoveRecordAction;
import com.mattihew.cswk.programming2.controller.undo.UndoController;
import com.mattihew.cswk.programming2.model.Booking;
import com.mattihew.cswk.programming2.model.RecordCache;
import com.mattihew.cswk.programming2.model.students.Student;

public class BookingController extends TablePanelUIController<Booking>
{
	private static final List<String> TABLE_HEADINGS = Arrays.asList("Student","Trip", "has paid", "has Permission");
	
	private final UndoController undoController;
	private final RecordCache<Booking> bookings;
	private final Collection<Student> students;
	
	public BookingController(final UndoController undoController, final RecordCache<Booking> bookings, final Collection<Student> students)
	{
		super();
		this.undoController = undoController;
		this.bookings = bookings;
		this.students = students;
	}
	
	@Override
	public void createRecord(final Object[] elementValues, final UUID id)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createRecord(final Booking element, final UUID id)
	{
		this.undoController.doCommand(new CreateRecordAction<>(this.bookings, this.getRecordName(), element, id));
	}

	@Override
	public void editRecord(final UUID id, final Booking element)
	{
		this.undoController.doCommand(new EditRecordAction<>(this.bookings, this.getRecordName(), id, element));
	}

	@Override
	public void editRecord(final UUID id, final Object[] elementValues)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeRecord(final UUID id)
	{
		this.undoController.doCommand(new RemoveRecordAction<>(this.bookings, this.getRecordName(), id));
	}

	@Override
	public String getRecordName()
	{
		return "Booking";
	}

	@Override
	public String getRecordNamePlural()
	{
		return "Bookings";
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
