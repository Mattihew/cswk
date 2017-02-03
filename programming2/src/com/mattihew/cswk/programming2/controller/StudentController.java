package com.mattihew.cswk.programming2.controller;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.mattihew.cswk.programming2.controller.undo.CreateRecordAction;
import com.mattihew.cswk.programming2.controller.undo.EditRecordAction;
import com.mattihew.cswk.programming2.controller.undo.RemoveRecordAction;
import com.mattihew.cswk.programming2.controller.undo.UndoController;
import com.mattihew.cswk.programming2.model.RecordCache;
import com.mattihew.cswk.programming2.model.students.Student;

public class StudentController extends TablePanelUIController<Student>
{
	private final UndoController undoController;
	
	private final RecordCache<Student> studentCache = new RecordCache<>();
	
	public StudentController(final UndoController undoController)
	{
		this.studentCache.addRecord(new Student("Matt","Rayner","01234567890"));
		this.studentCache.addRecord(new Student("Test1","Test2","123"));
		this.undoController = undoController;
	}

	@Override
	public void createRecord(final Student student, final UUID id)
	{
		this.undoController.doCommand(new CreateRecordAction<>(this.studentCache, this.getRecordName(), student, id));
	}
	
	@Override
	public void editRecord(final UUID id, final Student student)
	{
		this.undoController.doCommand(new EditRecordAction<>(this.studentCache,this.getRecordName(), id, student));
	}
	
	@Override
	public void removeRecord(final UUID id)
	{
		this.undoController.doCommand(new RemoveRecordAction<>(this.studentCache, this.getRecordName(), id));
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
}
