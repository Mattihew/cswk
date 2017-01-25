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
import com.mattihew.cswk.programming2.model.teachers.Teacher;
import com.mattihew.cswk.programming2.view.abstracts.EditDialog;

public class NewTeacherDialog extends EditDialog<Teacher>
{
	/** serialVersionUID */
	private static final long serialVersionUID = -4888205041366029324L;
	
	private static final List<String> TABLE_HEADINGS = Collections.unmodifiableList(Arrays.asList("First Name:", "Last Name:"));
	
	private final UUID id;
	
	private final UIController<Teacher> controller;

	public NewTeacherDialog(final Frame owner, final UIController<Teacher> controller)
	{
		this(owner, controller, null, null);
	}
	
	public NewTeacherDialog(final Frame owner, final UIController<Teacher> controller, final Teacher teacher)
	{
		this(owner, controller, teacher, null);
	}
	
	public NewTeacherDialog(final Frame owner, final UIController<Teacher> controller, final Teacher teacher, final UUID existingId)
	{
		super(owner, "New Teacher", teacher);
		this.controller = controller;
		this.id = existingId;
	}

	@Override
	protected void insertValues(final Teacher teacher)
	{
		if (Objects.isNull(teacher))
		{
			this.setValues(NewTeacherDialog.TABLE_HEADINGS);
		}
		else
		{
			this.setTitle("Edit Teacher");
			this.setValues(NewTeacherDialog.TABLE_HEADINGS,
					Arrays.asList(teacher.getFirstName(), teacher.getLastName()));
		}
	}

	@Override
	protected void okActionPerformed(final List<JTextField> textFields, final ActionEvent e)
	{
		final Iterator<JTextField> textItr = textFields.iterator();
		final Teacher teacher = new Teacher(
				textItr.next().getText(), // First Name
				textItr.next().getText()); // Last Name
		if (Objects.isNull(this.id))
		{
			this.controller.createRecord(teacher);
		}
		else
		{
			this.controller.editRecord(this.id, teacher);
		}
	}
	
	
}
