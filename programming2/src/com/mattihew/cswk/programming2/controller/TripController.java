package com.mattihew.cswk.programming2.controller;

import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.mattihew.cswk.programming2.controller.undo.UndoController;
import com.mattihew.cswk.programming2.model.RecordCache;
import com.mattihew.cswk.programming2.model.students.Student;
import com.mattihew.cswk.programming2.model.teachers.Teacher;
import com.mattihew.cswk.programming2.model.trips.Trip;
import com.mattihew.cswk.programming2.view.TablePanel;

public class TripController extends TablePanelUIController<Trip>
{
	private final UndoController undoController;
	private final MainController mainController;
	
	private final RecordCache<Trip> tripCache = new RecordCache<>();
	private final Collection<Student> students;
	
	public TripController(final UndoController undoController, final MainController mainController, final Collection<Student> students)
	{
		super(undoController);
		this.undoController = undoController;
		this.mainController = mainController;
		this.students = students;
		this.tripCache.addRecord(new Trip("Home", new Teacher("bob", "smith")));
	}
	
	@Override
	public Panel getUIPanel(final Frame owner)
	{
		final TablePanel tablePanel = new TablePanel(owner, this.getTableHeadings(), this);
		tablePanel.addPopupMenuItem("Show Bookings", new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				final UUID id = tablePanel.getSelectedUUID();
				final BookingController bookingController = new BookingController(
						TripController.this.undoController,
						TripController.this.tripCache.getRecord(id),
						TripController.this.students);
				TripController.this.mainController.addUIController(bookingController);
			}
		});
		return tablePanel;
	}

	@Override
	public void createRecord(final Object[] elementValues, final UUID id)
	{
		
		
	}

	@Override
	public void editRecord(final UUID id, final Object[] elementValues)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getRecordName()
	{
		return "Trip";
	}

	@Override
	public String getRecordNamePlural()
	{
		return "Trips";
	}

	@Override
	public RecordCache<Trip> getRecordCache()
	{
		return this.tripCache;
	}

	@Override
	public List<String> getTableHeadings()
	{
		return Arrays.asList("Destination","Trip Provider");
	}

	@Override
	public String[] comboOptions(final int attributeIndex)
	{
		if (attributeIndex == 1)
		{
			return new String[] {};
		}
		return null;
	}

}
