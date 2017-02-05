package com.mattihew.cswk.programming2.controller;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.mattihew.cswk.programming2.controller.undo.UndoController;
import com.mattihew.cswk.programming2.model.RecordCache;
import com.mattihew.cswk.programming2.model.teachers.Teacher;

public class TeacherController extends TablePanelUIController<Teacher>
{

	private final RecordCache<Teacher> teacherCache = new RecordCache<>();
	public TeacherController(final UndoController undoController)
	{
		super(undoController);
		this.teacherCache.addRecord(new Teacher("teacher1", "teacher2"));
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



}
