package com.mattihew.cswk.programming2.view;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.swing.JTextField;

import com.mattihew.cswk.programming2.controller.interfaces.UIController;
import com.mattihew.cswk.programming2.model.students.Student;
import com.mattihew.cswk.programming2.view.abstracts.EditDialog;

public class NewStudentDialog extends EditDialog<Student>
{
	/** serialVersionUID */
	private static final long serialVersionUID = -4888205041366029324L;
	
	private static final List<String> TABLE_HEADINGS = Collections.unmodifiableList(Arrays.asList("First Name:", "Last Name:", "Phone Num:"));
	
	private final UUID id;
	
	private final UIController<Student> controller;
	
	private final boolean isEditing;

	public NewStudentDialog(final Frame owner, final UIController<Student> controller)
	{
		this(owner, controller, null, null);
	}
	
	public NewStudentDialog(final Frame owner, final UIController<Student> controller, final Student student)
	{
		this(owner, controller, student, null);
	}
	
	public NewStudentDialog(final Frame owner, final UIController<Student> controller, final Student student, final UUID existingId)
	{
		super(owner, "New Student", student);
		this.controller = controller;
		this.id = existingId;
		this.isEditing = Objects.nonNull(student);
	}

	@Override
	protected void insertValues(final Student student)
	{
		if (Objects.isNull(student))
		{
			this.setValues(NewStudentDialog.TABLE_HEADINGS);
		}
		else
		{
			this.setTitle("Edit Student");
			this.setValues(NewStudentDialog.TABLE_HEADINGS,
					Arrays.asList(student.getFirstName(), student.getLastName(), student.getPhoneNum()));
		}
	}

	@Override
	protected void okActionPerformed(final List<JTextField> textFields, final ActionEvent e)
	{
		final Iterator<JTextField> textItr = textFields.iterator();
		final Student student = new Student(
				textItr.next().getText(), // First Name
				textItr.next().getText(), // Last Name
				textItr.next().getText()); // Phone Num
		if (this.isEditing)
		{
			this.controller.editRecord(this.id, student);
		}
		else
		{
			this.controller.createRecord(student, this.id);
		}
	}
	
	
}
