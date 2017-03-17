package com.mattihew.cswk.programming2.controller;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.UUID;

import javax.swing.JDialog;

import com.mattihew.cswk.programming2.controller.undo.UndoController;
import com.mattihew.cswk.programming2.model.Student;
import com.mattihew.cswk.programming2.model.Teacher;
import com.mattihew.cswk.programming2.model.interfaces.TripProvider;
import com.mattihew.cswk.programming2.model.tableModel.RecordCache;
import com.mattihew.cswk.programming2.model.tableModel.RecordCacheTableModel;
import com.mattihew.cswk.programming2.model.trip.StandardTrip;
import com.mattihew.cswk.programming2.model.trip.Trip;
import com.mattihew.cswk.programming2.util.ArrayUtils;
import com.mattihew.cswk.programming2.view.TablePanel;
import com.mattihew.cswk.programming2.view.TripDialog;

/**
 * Controller
 * 
 * @author Matt Rayner
 */
public class TripController extends TablePanelUIController<Trip>
{
	private final UndoController undoController;
	private final MainController mainController;
	
	private final RecordCache<Trip> tripCache = new RecordCache<>();
	private final Collection<Student> students;
	private final Collection<? extends TripProvider> tripProviders;
	private final Collection<Teacher> teachers;
	
	/**
	 * Class Constructor.
	 *
	 * @param undoController the undo controller to use for the actions
	 * @param mainController a reference to the mainController.
	 * @param students a collection of students that can be added to trips
	 * @param tripProviders a collection of teachers that can be added to trips
	 */
	public TripController(final UndoController undoController, final MainController mainController,
			final Collection<Student> students, final Collection<? extends TripProvider> tripProviders,
			final Collection<Teacher> teachers)
	{
		super(undoController);
		this.undoController = undoController;
		this.mainController = mainController;
		this.students = students;
		this.tripProviders = tripProviders;
		this.teachers = teachers;
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
	public RecordCache<Trip> getRecordCache()
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
			return ArrayUtils.toStringArray(this.tripProviders);
		}
		return null;
	}

	@Override
	public void insertNewItem(final Frame owner)
	{
		final Dialog tripDialog = new TripDialog(owner, this, this.tripProviders, this.teachers, null);
		tripDialog.setVisible(true);
	}

	@Override
	public void editExistingItem(final Frame owner, final UUID id)
	{
		final Dialog tripDialog = new TripDialog(owner, this, this.tripProviders, this.teachers, id);
		tripDialog.setVisible(true);
		//TODO
	}
}
