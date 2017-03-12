package com.mattihew.cswk.programming2.controller;

import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.UUID;

import com.mattihew.cswk.programming2.controller.undo.UndoController;
import com.mattihew.cswk.programming2.model.Student;
import com.mattihew.cswk.programming2.model.Teacher;
import com.mattihew.cswk.programming2.model.tableModel.RecordCache;
import com.mattihew.cswk.programming2.model.tableModel.RecordCacheTableModel;
import com.mattihew.cswk.programming2.model.trip.StandardTrip;
import com.mattihew.cswk.programming2.util.ArrayUtils;
import com.mattihew.cswk.programming2.view.TablePanel;

public class TripController extends TablePanelUIController<StandardTrip>
{
	private final UndoController undoController;
	private final MainController mainController;
	
	private final RecordCache<StandardTrip> tripCache = new RecordCache<>();
	private final Collection<Student> students;
	private final Collection<Teacher> teachers;
	
	public TripController(final UndoController undoController, final MainController mainController, final Collection<Student> students, final Collection<Teacher> teachers)
	{
		super(undoController);
		this.undoController = undoController;
		this.mainController = mainController;
		this.students = students;
		this.teachers = teachers;
		this.tripCache.addRecord(new StandardTrip.TripBuilder().setTripProvider(new Teacher("Bob", "Smith")).setDestination("Somewhere").toTrip());
	}
	
	@Override
	public Panel getUIPanel(final Frame owner)
	{
		final TablePanel tablePanel = new TablePanel(owner, this);
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
	public RecordCache<StandardTrip> getRecordCache()
	{
		return this.tripCache;
	}
	
	@Override
	public RecordCacheTableModel getTableModel()
	{
		return new RecordCacheTableModel(this.tripCache, new String[]{"Destination","Trip Provider","Accomodation"}, new Class<?>[]{String.class, String.class, String.class});
	}

	@Override
	public String[] comboOptions(final int attributeIndex)
	{
		if (attributeIndex == 1)
		{
			return ArrayUtils.toStringArray(this.teachers);
		}
		return null;
	}

}
