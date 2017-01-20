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

import com.mattihew.cswk.programming2.model.students.Student;
import com.mattihew.cswk.programming2.model.students.StudentCache;
import com.mattihew.cswk.programming2.view.abstracts.EditDialog;

public class NewStudentView extends EditDialog<Student>
{
	/** serialVersionUID */
	private static final long serialVersionUID = -4888205041366029324L;
	
	private static final List<String> TABLE_HEADINGS = Collections.unmodifiableList(Arrays.asList("First Name:", "Last Name:", "Phone Num:"));
	
	private final UUID id;

	public NewStudentView(final Frame owner)
	{
		this(owner, null, null);
	}
	
	public NewStudentView(final Frame owner, final Student student)
	{
		this(owner, student, null);
	}
	
	public NewStudentView(final Frame owner, final Student student, final UUID existingId)
	{
		super(owner, "New Student", student);
		this.id = existingId;
	}

	@Override
	protected void insertValues(final Student student)
	{
		if (Objects.isNull(student))
		{
			this.setValues(NewStudentView.TABLE_HEADINGS);
		}
		else
		{
			this.setTitle("Edit Student");
			this.setValues(NewStudentView.TABLE_HEADINGS,
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
		if (Objects.isNull(this.id))
		{
			StudentCache.getInstance().addStudent(student);
		}
		else
		{
			StudentCache.getInstance().putStudent(this.id, student);
		}
		
	}
	
	
}
