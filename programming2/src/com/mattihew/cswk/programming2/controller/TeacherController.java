package com.mattihew.cswk.programming2.controller;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.mattihew.cswk.programming2.controller.undo.CreateRecordAction;
import com.mattihew.cswk.programming2.controller.undo.EditRecordAction;
import com.mattihew.cswk.programming2.controller.undo.RemoveRecordAction;
import com.mattihew.cswk.programming2.controller.undo.UndoController;
import com.mattihew.cswk.programming2.model.RecordCache;
import com.mattihew.cswk.programming2.model.teachers.Teacher;

public class TeacherController extends TablePanelUIController<Teacher>
{
	private final UndoController undoController;
	
	private final RecordCache<Teacher> teacherCache = new RecordCache<>();
	public TeacherController(final UndoController undoController)
	{
		super();
		this.teacherCache.addRecord(new Teacher("teacher1", "teacher2"));
		this.undoController = undoController;
	}
	
	@Override
	public void createRecord(final String[] elementValues, final UUID id)
	{
		this.createRecord(Teacher.FromTableColumnValues(elementValues), id);
	}

	@Override
	public void createRecord(final Teacher element, final UUID id)
	{
		this.undoController.doCommand(new CreateRecordAction<>(this.teacherCache, this.getRecordName(), element, id));
	}

	@Override
	public void editRecord(final UUID id, final String[] elementValues)
	{
		this.editRecord(id, Teacher.FromTableColumnValues(elementValues));
	}
	
	@Override
	public void editRecord(final UUID id, final Teacher element)
	{
		this.undoController.doCommand(new EditRecordAction<>(this.teacherCache, this.getRecordName(), id, element));
	}

	@Override
	public void removeRecord(final UUID id)
	{
		this.undoController.doCommand(new RemoveRecordAction<>(this.teacherCache, this.getRecordName(), id));
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
