package com.mattihew.cswk.programming2.view;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

import com.mattihew.cswk.programming2.model.students.Student;
import com.mattihew.cswk.programming2.model.students.StudentCache;
import com.mattihew.cswk.programming2.view.abstracts.TableFrame;

public class StudentsView extends TableFrame<Student> implements Observer
{
	/** serialVersionUID */
	private static final long serialVersionUID = 5870316368738488041L;

	/**
	 * Create the frame.
	 */
	public StudentsView()
	{
		super("Students", "Student", Arrays.asList("First Name","Last Name", "Phone Number"));
		for (final Entry<UUID, Student> studentEntry : StudentCache.getInstance().getStudents().entrySet())
		{
			this.addToTable(studentEntry.getKey(), studentEntry.getValue());
		}
		StudentCache.getInstance().addObserver(this);
	}
	
	private void addToTable(final UUID id, final Student student)
	{
		final Object[] data = {id, student.getFirstName(), student.getLastName(), student.getPhoneNum()};
		this.tableModel.addRow(data);
	}
	
	private void replaceInTable(final UUID id, final Student student)
	{
		for (int i = 0; i < this.tableModel.getRowCount(); i++)
		{
			if (this.tableModel.getValueAt(i, 0).equals(id))
			{
				this.tableModel.setValueAt(student.getFirstName(),i, 1);
				this.tableModel.setValueAt(student.getLastName(), i, 2);
				this.tableModel.setValueAt(student.getPhoneNum(), i, 3);
				return;
			}
		}
		this.addToTable(id, student);
	}
	
	@Override
	public void update(final Observable o, final Object arg)
	{
		if (o instanceof StudentCache && arg instanceof Collection)
		{
			for (final Object id : (Collection<?>) arg)
			{
				if (id instanceof UUID)
				{
					this.replaceInTable((UUID) id, StudentCache.getInstance().getStudents().get(id));
				}
			}
		}
	}

	@Override
	protected void newActionPerformed(final ActionEvent e)
	{
		new NewStudentView(this);
	}

	@Override
	protected void editActionPerformed(final ActionEvent e)
	{
		final UUID id = (UUID) this.tableModel.getValueAt(this.table.getSelectedRow(), 0);
		new NewStudentView(this, StudentCache.getInstance().getStudent(id), id);
	}
}
