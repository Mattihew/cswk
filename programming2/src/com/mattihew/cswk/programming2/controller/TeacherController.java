package com.mattihew.cswk.programming2.controller;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Panel;
import java.util.UUID;

import com.mattihew.cswk.programming2.controller.interfaces.UIController;
import com.mattihew.cswk.programming2.controller.undo.CreateRecordAction;
import com.mattihew.cswk.programming2.controller.undo.EditRecordAction;
import com.mattihew.cswk.programming2.controller.undo.RemoveRecordAction;
import com.mattihew.cswk.programming2.controller.undo.UndoController;
import com.mattihew.cswk.programming2.model.teachers.Teacher;
import com.mattihew.cswk.programming2.model.teachers.TeacherCache;
import com.mattihew.cswk.programming2.view.NewTeacherDialog;
import com.mattihew.cswk.programming2.view.TeachersPanel;

public class TeacherController implements UIController<Teacher>
{
	private final UndoController undoController;
	public TeacherController(final UndoController undoController)
	{
		super();
		TeacherCache.getInstance().addRecord(new Teacher("teacher1", "teacher2"));
		this.undoController = undoController;
	}
	
	@Override
	public String getItemName()
	{
		return "Teacher";
	}

	@Override
	public void createRecord(final Teacher element)
	{
		this.createRecord(element, null);
	}

	@Override
	public void createRecord(final Teacher element, final UUID id)
	{
		this.undoController.doCommand(new CreateRecordAction<>(TeacherCache.getInstance(), element, id));
	}

	@Override
	public void editRecord(final UUID id, final Teacher element)
	{
		this.undoController.doCommand(new EditRecordAction<>(TeacherCache.getInstance(), id, element));
	}

	@Override
	public void removeRecord(final UUID id)
	{
		this.undoController.doCommand(new RemoveRecordAction<>(TeacherCache.getInstance(), id));
	}

	@Override
	public Panel getUIPanel(final Frame owner)
	{
		return new TeachersPanel(owner, this);
	}

	@Override
	public void insertNewItem(final Frame owner)
	{
		final Dialog newTeachers =  new NewTeacherDialog(owner, this);
		newTeachers.setVisible(true);
		
	}



}
