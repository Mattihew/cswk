package com.mattihew.cswk.programming2.controller;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Panel;
import java.util.UUID;

import com.mattihew.cswk.programming2.controller.interfaces.UIController;
import com.mattihew.cswk.programming2.controller.undo.CreateStudentCommand;
import com.mattihew.cswk.programming2.controller.undo.EditStudentCommand;
import com.mattihew.cswk.programming2.controller.undo.RemoveStudentCommand;
import com.mattihew.cswk.programming2.controller.undo.UndoController;
import com.mattihew.cswk.programming2.model.students.Student;
import com.mattihew.cswk.programming2.model.students.StudentCache;
import com.mattihew.cswk.programming2.view.NewStudentDialog;
import com.mattihew.cswk.programming2.view.StudentsPanel;

public class StudentController implements UIController<Student>
{
	private final UndoController undoController;
	
	public StudentController(final UndoController undoController)
	{
		StudentCache.getInstance().addStudent(new Student("Matt","Rayner","01234567890"));
		StudentCache.getInstance().addStudent(new Student("Test1","Test2","123"));
		this.undoController = undoController;
	}
	
	@Override
	public String getItemName()
	{
		return "Student";
	}
	
	@Override
	public Panel getUIPanel(final Frame owner)
	{
		return new StudentsPanel(owner, this);
	}
	
	@Override
	public void insertNewItem(final Frame owner)
	{
		final Dialog newStudent = new NewStudentDialog(owner, this);
		newStudent.setVisible(true);
	}

	@Override
	public void createRecord(final Student student)
	{
		this.createRecord(student, null);
	}

	@Override
	public void createRecord(final Student student, final UUID id)
	{
		this.undoController.doCommand(new CreateStudentCommand(student, id));
	}
	
	@Override
	public void editRecord(final UUID id, final Student student)
	{
		this.undoController.doCommand(new EditStudentCommand(id, student));
	}
	
	@Override
	public void removeRecord(final UUID id)
	{
		this.undoController.doCommand(new RemoveStudentCommand(id));
	}
}
