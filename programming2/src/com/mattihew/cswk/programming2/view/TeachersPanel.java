package com.mattihew.cswk.programming2.view;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

import com.mattihew.cswk.programming2.controller.interfaces.UIController;
import com.mattihew.cswk.programming2.model.RecordCache;
import com.mattihew.cswk.programming2.model.teachers.Teacher;
import com.mattihew.cswk.programming2.view.abstracts.TablePanel;

public class TeachersPanel extends TablePanel implements Observer
{
	/** serialVersionUID */
	private static final long serialVersionUID = 5870316368738488041L;

	private final UIController<Teacher> controller;
	
	private final Frame owner;
	/**
	 * Create the frame.
	 */
	public TeachersPanel(final Frame owner, final UIController<Teacher> controller)
	{
		super(Arrays.asList("First Name","Last Name"));
		this.owner = owner;
		this.controller = controller;
		for (final Entry<UUID, Teacher> teacherEntry : this.controller.getRecordCache().getRecords().entrySet())
		{
			this.addToTable(teacherEntry.getKey(), teacherEntry.getValue());
		}
		this.controller.getRecordCache()
		.addObserver(this);
		this.setVisible(true);
	}
	
	private void addToTable(final UUID id, final Teacher teacher)
	{
		final List<Object> data = new ArrayList<>();
		data.add(0, id);
		data.addAll(teacher.toTableColumnValues());
		this.tableModel.addRow(data.toArray());
	}
	
	private void replaceInTable(final UUID id, final Teacher teacher)
	{
		for (int i = 0; i < this.tableModel.getRowCount(); i++)
		{
			if (this.tableModel.getValueAt(i, 0).equals(id))
			{
				if (Objects.isNull(teacher))
				{
					this.tableModel.removeRow(i);
				}
				else
				{
					final List<Object> columns = teacher.toTableColumnValues();
					for (int j = 0; j < columns.size(); j++)
					{
						this.tableModel.setValueAt(columns.get(j), i, j+1);
					}
				}
				return;
			}
		}
		this.addToTable(id, teacher);
	}
	
	@Override
	public void update(final Observable o, final Object arg)
	{
		if (o instanceof RecordCache && arg instanceof Collection)
		{
			for (final Object id : (Collection<?>) arg)
			{
				if (id instanceof UUID)
				{
					this.replaceInTable((UUID) id, this.controller.getRecordCache().getRecord((UUID) id));
				}
			}
		}
	}

	@Override
	protected void editActionPerformed(final ActionEvent e)
	{
		final UUID id = (UUID) this.tableModel.getValueAt(this.table.getSelectedRow(), 0);
		final Dialog newTeacher = new EditDialog<>(this.owner, this.controller, this.controller.getRecordCache().getRecord(id), id);
		newTeacher.setVisible(true);
	}

	@Override
	protected void removeActionPerformed(final ActionEvent e)
	{
		final UUID id = (UUID) this.tableModel.getValueAt(this.table.getSelectedRow(), 0);
		this.controller.removeRecord(id);
	}
}
