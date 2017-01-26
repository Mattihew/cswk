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
import com.mattihew.cswk.programming2.model.abstracts.RecordCache;
import com.mattihew.cswk.programming2.model.teachers.Teacher;
import com.mattihew.cswk.programming2.model.teachers.TeacherCache;
import com.mattihew.cswk.programming2.view.EditDialog;
import com.mattihew.cswk.programming2.view.TeachersPanel;

public class TeacherController implements UIController<Teacher>
{
	private final UndoController undoController;
	
	private final RecordCache<Teacher> teacherCache = TeacherCache.getInstance();
	public TeacherController(final UndoController undoController)
	{
		super();
		TeacherCache.getInstance().addRecord(new Teacher("teacher1", "teacher2"));
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
		this.undoController.doCommand(new RemoveRecordAction<>(TeacherCache.getInstance(), this.getRecordName(), id));
	}

	@Override
	public Panel getUIPanel(final Frame owner)
	{
		return new TeachersPanel(owner, this);
	}

	@Override
	public void insertNewItem(final Frame owner)
	{
		final Dialog newTeachers =  new EditDialog<>(owner, this);
		newTeachers.setVisible(true);
	}

	@Override
	public RecordCache<Teacher> getRecordCache()
	{
		return TeacherCache.getInstance();
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
