package com.mattihew.cswk.programming2.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.mattihew.cswk.programming2.controller.undo.UndoController;
import com.mattihew.cswk.programming2.model.RecordCache;
import com.mattihew.cswk.programming2.model.Student;
import com.mattihew.cswk.programming2.model.abstracts.RecordStorage;
import com.mattihew.cswk.programming2.util.ArrayUtils;

public class StudentController extends TablePanelUIController<Student>
{
	private final RecordCache<Student> studentCache = new RecordCache<>();
	
	private final RecordStorage<Student> studentStorage = new StudentStorage("./data/students.csv");
	
	public StudentController(final UndoController undoController)
	{
		super(undoController);
		this.studentCache.putRecords(this.studentStorage.readRecords());
	}

	@Override
	public RecordCache<Student> getRecordCache()
	{
		return this.studentCache;
	}

	@Override
	public void createRecord(final Object[] elementValues, final UUID id)
	{
		this.createRecord(Student.fromTableColumnValues(elementValues), id);
	}

	@Override
	public void editRecord(final UUID id, final Object[] elementValues)
	{
		this.editRecord(id, Student.fromTableColumnValues(elementValues));
	}

	@Override
	public String getRecordName()
	{
		return "Student";
	}

	@Override
	public String getRecordNamePlural()
	{
		return "Students";
	}

	@Override
	public List<String> getTableHeadings()
	{
		return Arrays.asList("First Name", "Last Name", "Phone num");
	}
	
	@Override
	public void dispose()
	{
		this.studentStorage.writeRecords(this.studentCache.getRecords());
	}

	private class StudentStorage extends RecordStorage<Student>
	{
		public StudentStorage(final String filePath)
		{
			super(filePath);
		}

		@Override
		protected void writeRecord(final Appendable output, final Student record) throws IOException
		{
			output.append(record.getFirstName());
			output.append(',');
			output.append(record.getLastName());
			output.append(',');
			output.append(record.getPhoneNum());
		}

		@Override
		protected Student readRecord(final String readLine)
		{
			final String[] lineParts = readLine.split(",");
			final String firstName = ArrayUtils.getOrEmpty(lineParts, 0);
			final String lastName = ArrayUtils.getOrEmpty(lineParts, 1);
			final String phoneNum = ArrayUtils.getOrEmpty(lineParts, 2);
			return new Student(firstName, lastName, phoneNum);
		}
	}
}
