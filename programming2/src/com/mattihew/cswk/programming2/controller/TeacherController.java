package com.mattihew.cswk.programming2.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.mattihew.cswk.programming2.controller.undo.UndoController;
import com.mattihew.cswk.programming2.model.RecordCache;
import com.mattihew.cswk.programming2.model.storage.RecordStorage;
import com.mattihew.cswk.programming2.model.teachers.Teacher;
import com.mattihew.cswk.programming2.util.StringArrayUtils;

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
	public List<String> getTableHeadings()
	{
		return Arrays.asList("First name", "Last name");
	}

	@Override
	public void dispose()
	{
		this.teacherStorage.writeRecords(this.teacherCache.getRecords());
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
			final String firstName = StringArrayUtils.getIndexOrDefault(lineParts, 0);
			final String lastName = StringArrayUtils.getIndexOrDefault(lineParts, 1);
			return new Teacher(firstName, lastName);
		}
		
	}

}
