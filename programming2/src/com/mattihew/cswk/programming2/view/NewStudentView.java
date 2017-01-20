package com.mattihew.cswk.programming2.view;

import java.awt.Frame;
import java.util.UUID;

import com.mattihew.cswk.programming2.model.students.Student;
import com.mattihew.cswk.programming2.view.abstracts.EditDialog;

public class NewStudentView extends EditDialog<Student>
{	
	/** serialVersionUID */
	private static final long serialVersionUID = -4888205041366029324L;

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
		super(owner, student, existingId);
	}

	@Override
	protected void insertValues(final Student student)
	{
		this.setTitle("Edit Student");
		this.txtFirstName.setText(student.getFirstName());
		this.txtLastName.setText(student.getLastName());
		this.txtPhoneNum.setText(student.getPhoneNum());
	}
	
	
}
