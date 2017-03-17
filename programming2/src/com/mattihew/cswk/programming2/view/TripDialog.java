package com.mattihew.cswk.programming2.view;

import java.awt.Frame;
import java.awt.GridLayout;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.mattihew.cswk.programming2.controller.interfaces.UIController;
import com.mattihew.cswk.programming2.model.ExternalProvider;
import com.mattihew.cswk.programming2.model.Teacher;
import com.mattihew.cswk.programming2.model.interfaces.TripProvider;
import com.mattihew.cswk.programming2.model.trip.ResidentialTrip;
import com.mattihew.cswk.programming2.model.trip.Trip;
import com.mattihew.cswk.programming2.util.ArrayUtils;

public class TripDialog extends JDialog
{
	private final UUID id;
	private final Collection<? extends TripProvider> tripProviders;
	private final Collection<Teacher> teachers;
	private final UIController<Trip> controller;
	
	private JTextField nameField;
	private JTextField destination;
	private JSpinner cost;
	private JButton OKButton;
	private JButton CancelButton;
	private JRadioButton rdbtnTeacher;
	private JRadioButton rdbtnExternalProvider;
	private JLabel lblExtProv;
	private JComboBox<String> extProv;
	private JLabel lblTransport;
	private JTextField transport;
	private JLabel lblVenue;
	private JTextField venue;
	private JLabel lblAccomadation;
	private JTextField accomadation;
	private JRadioButton rdbtnDayTrip;
	private JRadioButton rdbtnResidentialTrip;

	/**
	 * Create the application.
	 */
	public TripDialog(final Frame owner, final UIController<Trip> controller, final Collection<? extends TripProvider> tripProviders, final Collection<Teacher> teachers, final UUID id)
	{
		super(owner);
		this.id = id;
		this.tripProviders = tripProviders;
		this.teachers = teachers;
		this.controller = controller;
		this.setBounds(200, 200, 400, 400);
		this.setTitle((Objects.isNull(id) ? "New" : "Edit") + " Trip" );
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(new GridLayout(0, 2, 0, 2));
		this.setValues();
	}
	
	private final void setValues()
	{
		final Trip trip = this.id==null ? null : this.controller.getRecordCache().getRecord(this.id);
		final JLabel lblName = new JLabel("Name:");
		this.getContentPane().add(lblName);
		
		this.nameField = new JTextField(trip == null ? null : trip.getName());
		getContentPane().add(this.nameField);
		this.nameField.setColumns(10);
		
		getContentPane().add(new JLabel("Destination:"));
		
		this.destination = new JTextField(trip == null ? null : trip.getDestination());
		getContentPane().add(this.destination);
		this.destination.setColumns(10);
		
		JLabel lblcost = new JLabel("Cost:");
		getContentPane().add(lblcost);
		
		this.cost = new JSpinner();
		this.cost.setValue(Integer.valueOf(trip == null ? 0 : trip.getCost()));
		getContentPane().add(this.cost);
		
		rdbtnExternalProvider = new JRadioButton("External Provider");
		rdbtnExternalProvider.addActionListener((e) -> setExternalProvider(true));
		getContentPane().add(rdbtnExternalProvider);
		
		rdbtnTeacher = new JRadioButton("Teacher");
		rdbtnTeacher.setSelected(true);
		rdbtnTeacher.addActionListener((e) -> setExternalProvider(false));
		getContentPane().add(rdbtnTeacher);
		if (!Objects.isNull(trip) && trip.getTripProvider() instanceof ExternalProvider)
		{
			rdbtnExternalProvider.setSelected(true);
		}
		else
		{
			rdbtnTeacher.setSelected(true);
		}
		
		this.lblExtProv = new JLabel("Teacher:");
		this.getContentPane().add(this.lblExtProv);
		
		this.extProv = new JComboBox<String>();
		this.getContentPane().add(this.extProv);
		
		final ButtonGroup group = new ButtonGroup();
		group.add(rdbtnExternalProvider);
		group.add(rdbtnTeacher);
		
		lblTransport = new JLabel("Transport:");
		getContentPane().add(lblTransport);
		
		transport = new JTextField(trip == null ? null : trip.getTripProvider().getTransport());
		getContentPane().add(transport);
		transport.setColumns(10);
		
		lblVenue = new JLabel("Venue:");
		getContentPane().add(lblVenue);
		
		venue = new JTextField(trip == null ? null : trip.getTripProvider().getVenue());
		getContentPane().add(venue);
		venue.setColumns(10);
		this.setExternalProvider(rdbtnExternalProvider.isSelected());
		
		rdbtnDayTrip = new JRadioButton("Day Trip");
		rdbtnDayTrip.setSelected(true);
		getContentPane().add(rdbtnDayTrip);
		
		rdbtnResidentialTrip = new JRadioButton("Residential Trip");
		rdbtnResidentialTrip.addActionListener((l) -> this.setAccomodation(this.rdbtnTeacher.isSelected()));
		getContentPane().add(rdbtnResidentialTrip);
		
		final ButtonGroup typeSelector = new ButtonGroup();
		typeSelector.add(rdbtnDayTrip);
		typeSelector.add(rdbtnResidentialTrip);
		
		lblAccomadation = new JLabel("Accomadation:");
		getContentPane().add(lblAccomadation);
		
		accomadation = new JTextField();
		accomadation.setEnabled(false);
		getContentPane().add(accomadation);
		accomadation.setColumns(10);
		
		this.OKButton = new JButton("OK");
		this.getContentPane().add(this.OKButton);
		
		this.CancelButton = new JButton("Cancel");
		this.getContentPane().add(this.CancelButton);
	}
	
	private final void setExternalProvider(final boolean enable)
	{
		if (enable)
		{
			this.lblExtProv.setText("External Provider:");
			this.extProv.setModel(new DefaultComboBoxModel<>(ArrayUtils.toStringArray(this.tripProviders)));
		}
		else
		{
			this.lblExtProv.setText("Teacher:");
			this.extProv.setModel(new DefaultComboBoxModel<>(ArrayUtils.toStringArray(this.teachers)));
		}
		this.lblTransport.setEnabled(!enable);
		this.transport.setEnabled(!enable);
		this.lblVenue.setEnabled(!enable);
		this.venue.setEnabled(!enable);
	}
	
	private final void setAccomodation(final boolean enable)
	{
		this.accomadation.setEnabled(enable);
		this.lblAccomadation.setEnabled(enable);
	}
}
