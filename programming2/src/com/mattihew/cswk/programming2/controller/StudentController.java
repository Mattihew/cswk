package com.mattihew.cswk.programming2.controller;

import java.util.UUID;

import com.mattihew.cswk.programming2.controller.interfaces.UIController;
import com.mattihew.cswk.programming2.controller.undo.StoreStudentCommand;
import com.mattihew.cswk.programming2.controller.undo.UndoController;
import com.mattihew.cswk.programming2.model.students.Student;
import com.mattihew.cswk.programming2.model.students.StudentCache;
import com.mattihew.cswk.programming2.view.StudentsView;

public class StudentController implements UIController<Student>
{
	private final UndoController undoController;
	private StudentsView frame;
	
	public StudentController()
	{
		StudentCache.getInstance().addStudent(new Student("Matt","Rayner","01234567890"));
		StudentCache.getInstance().addStudent(new Student("Test1","Test2","123"));
		this.undoController = new UndoController();
	}
	
	public void showUI()
	{
		this.frame = new StudentsView(this);
	}

	@Override
	public void createRecord(final Student student)
	{
		this.createRecord(student, null);
	}

	@Override
	public void createRecord(final Student student, final UUID id)
	{
		this.undoController.doCommand(new StoreStudentCommand(student, id));
	}
	
	@Override
	public UndoController getUndoController()
	{
		return this.undoController;
	}
}
