package com.mattihew.cswk.programming2.view.abstracts;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.mattihew.cswk.programming2.model.students.Student;
import com.mattihew.cswk.programming2.model.students.StudentCache;

public abstract class EditDialog<E> extends JDialog
{
	/** serialVersionUID */
	private static final long serialVersionUID = 3975565360523394130L;
	private final List<JTextField> textFields = new ArrayList<>();
	private final JTextField txtFirstName;
	private final JTextField txtLastName;
	private final JTextField txtPhoneNum;
	private final UUID id;
	
	public EditDialog(final Frame owner)
	{
		this(owner, null, null);
	}
	
	public EditDialog(final Frame owner, final E element)
	{
		this(owner, element, null);
	}
	
	/** @wbp.parser.constructor */
	public EditDialog(final Frame owner, final E element, final UUID existingId)
	{
		super(owner, "New Student", true);
		this.setResizable(false);
		this.setBounds(100, 100, 250, 150);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(new GridLayout(0, 2, 0, 2));
		
		// First Name
		JLabel lblFirstName = new JLabel("First Name:");
		this.getContentPane().add(lblFirstName);
		
		this.txtFirstName = new JTextField();
		this.getContentPane().add(this.txtFirstName);
		this.txtFirstName.setColumns(10);
		
		// Last Name
		JLabel lblLastName = new JLabel("Last Name:");
		this.getContentPane().add(lblLastName);
		
		this.txtLastName = new JTextField();
		this.getContentPane().add(this.txtLastName);
		this.txtLastName.setColumns(10);
		
		//Phone Number
		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		this.getContentPane().add(lblPhoneNumber);
		
		this.txtPhoneNum = new JTextField();
		this.getContentPane().add(this.txtPhoneNum);
		this.txtPhoneNum.setColumns(10);
		
		this.id = existingId;
		if (element != null)
		{
			insertValues(element);
		}
		
		JButton btnOk = new JButton("OK");
		this.getContentPane().add(btnOk);
		this.getRootPane().setDefaultButton(btnOk);
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				final Student student = new Student(
						EditDialog.this.txtFirstName.getText(),
						EditDialog.this.txtLastName.getText(),
						EditDialog.this.txtPhoneNum.getText());
				if (EditDialog.this.id == null)
				{
					StudentCache.getInstance().addStudent(student);
				}
				else
				{
					StudentCache.getInstance().putStudent(EditDialog.this.id, student);
				}
				EditDialog.this.dispatchEvent(new WindowEvent(EditDialog.this, WindowEvent.WINDOW_CLOSING));
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		this.getContentPane().add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				EditDialog.this.dispatchEvent(new WindowEvent(EditDialog.this, WindowEvent.WINDOW_CLOSING));
			}
		});
		
		this.setVisible(true);
	}
	
	protected abstract void insertValues(final E element);
	
	protected final void setValues(final String[] labelValues, final String[] textValues)
	{
		if (textValues.length > labelValues.length)
		{
			throw new IllegalArgumentException("number of labels exceeds number of input boxes");
		}
		for (int i = 0; i < labelValues.length; i++)
		{
			JLabel lbl = new JLabel(labelValues[i]);
			this.getContentPane().add(lbl);
			
			final JTextField txtField = new JTextField();
			this.textFields.add(txtField);
			this.getContentPane().add(txtField);
			this.txtFirstName.setColumns(10);
		}
		
	}
	
}
