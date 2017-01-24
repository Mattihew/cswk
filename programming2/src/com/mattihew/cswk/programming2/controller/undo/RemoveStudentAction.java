package com.mattihew.cswk.programming2.controller.undo;

import java.util.UUID;

import com.mattihew.cswk.programming2.model.students.Student;
import com.mattihew.cswk.programming2.model.students.StudentCache;

public class RemoveStudentAction implements UndoableAction
{
	private final UUID id;
	private Student oldStudent;
	public RemoveStudentAction(final UUID id)
	{
		this.id = id;
	}
	
	@Override
	public void doAction()
	{
		this.oldStudent = StudentCache.getInstance().removeStudent(this.id);
	}

	@Override
	public void undoAction()
	{
		StudentCache.getInstance().putStudent(this.id, this.oldStudent);
	}

	@Override
	public String getTitle()
	{
		return "Remove Student";
	}

}