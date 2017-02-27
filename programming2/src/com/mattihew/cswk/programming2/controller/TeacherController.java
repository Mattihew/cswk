package com.mattihew.cswk.programming2.controller;

import java.io.IOException;
import java.util.UUID;

import javax.swing.table.TableModel;

import com.mattihew.cswk.programming2.controller.undo.UndoController;
import com.mattihew.cswk.programming2.model.Teacher;
import com.mattihew.cswk.programming2.model.abstracts.RecordStorage;
import com.mattihew.cswk.programming2.model.tableModel.RecordCache;
import com.mattihew.cswk.programming2.model.tableModel.RecordCacheTableModel;
import com.mattihew.cswk.programming2.util.ArrayUtils;

public class TeacherController extends TablePanelUIController<Teacher>
{

	private final RecordCache<Teacher> teacherCache = new RecordCache<>();
	private final RecordStorage<Teacher> teacherStorage = new TeacherStorage("./data/teachers.csv");
	public TeacherController(final UndoController undoController)
	{
		super(undoController);
		this.teacherCache.putRecords(this.teacherStorage.readRecords());
	}
	
	@Override
	public void createRecord(final Object[] elementValues, final UUID id)
	{
		this.createRecord(Teacher.FromTableColumnValues(elementValues), id);
	}

	@Override
	public void editRecord(final UUID id, final Object[] elementValues)
	{
		this.editRecord(id, Teacher.FromTableColumnValues(elementValues));
	}

	@Override
	public RecordCache<Teacher> getRecordCache()
	{
		return this.teacherCache;
	}

	@Override
	public String getRecordName()
	{
		return "Teacher";
	}

	@Override
	public String getRecordNamePlural()
	{
		return "Teachers";
	}

	@Override
	public void dispose()
	{
		this.teacherStorage.writeRecords(this.teacherCache.getRecords());
	}
	
	@Override
	public TableModel getTableModel()
	{
		return new RecordCacheTableModel(this.teacherCache, new String[]{"First Name", "Last Name"}, new Class<?>[]{String.class, String.class});
	}
	
	private class TeacherStorage extends RecordStorage<Teacher>
	{
		public TeacherStorage(final String filePath)
		{
			super(filePath);
		}

		@Override
		protected void writeRecord(final Appendable output, final Teacher record) throws IOException
		{
			output.append(record.getFirstName());
			output.append(',');
			output.append(record.getLastName());
		}

		@Override
		protected Teacher readRecord(final String readLine)
		{
			final String[] lineParts = readLine.split(",");
			final String firstName = ArrayUtils.getOrEmpty(lineParts, 0);
			final String lastName = ArrayUtils.getOrEmpty(lineParts, 1);
			return new Teacher(firstName, lastName);
		}
		
	}

}
