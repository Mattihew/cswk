package com.mattihew.cswk.programming2.controller;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Panel;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.mattihew.cswk.programming2.controller.interfaces.UIController;
import com.mattihew.cswk.programming2.controller.undo.CreateRecordAction;
import com.mattihew.cswk.programming2.controller.undo.EditRecordAction;
import com.mattihew.cswk.programming2.controller.undo.RemoveRecordAction;
import com.mattihew.cswk.programming2.controller.undo.UndoController;
import com.mattihew.cswk.programming2.model.RecordCache;
import com.mattihew.cswk.programming2.model.students.Student;
import com.mattihew.cswk.programming2.view.EditDialog;
import com.mattihew.cswk.programming2.view.TablePanel;

public class StudentController implements UIController<Student>
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
	public Panel getUIPanel(final Frame owner)
	{
		return new TablePanel(this.getTableHeadings(), this);
	}
	
	@Override
	public void insertNewItem(final Frame owner)
	{
		final Dialog newStudent = new EditDialog<>(owner, this);
		newStudent.setVisible(true);
	}

	@Override
	public void editExistingItem(Frame owner, UUID id)
	{
		final Dialog newStudent = new EditDialog<>(owner, this, this.getRecordCache().getRecord(id), id);
		newStudent.setVisible(true);
	}

	@Override
	public void removeExistingItem(Frame owner, UUID id)
	{
		this.removeRecord(id);
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
	public void createRecord(final String[] elementValues, final UUID id)
	{
		this.createRecord(Student.fromTableColumnValues(elementValues), id);
	}

	@Override
	public void editRecord(final UUID id, final String[] elementValues)
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
