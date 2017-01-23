package com.mattihew.cswk.programming2.controller.undo;

import java.util.Objects;
import java.util.UUID;

import com.mattihew.cswk.programming2.model.students.Student;
import com.mattihew.cswk.programming2.model.students.StudentCache;

public class EditStudentAction implements UndoableAction
{
	private final Student newStudent;
	private Student oldStudent;
	private final UUID id;
	
	public EditStudentAction(final UUID id, final Student student)
	{
		this.newStudent = Objects.requireNonNull(student, "Student required for editing");
		this.id = Objects.requireNonNull(id, "Id of student Required");
	}
	
	@Override
	public void doAction()
	{
		this.oldStudent = StudentCache.getInstance().putStudent(this.id, this.newStudent);
	}

	@Override
	public void undoAction()
	{
		if (Objects.nonNull(this.oldStudent))
		{
			StudentCache.getInstance().putStudent(this.id, this.oldStudent);
		}
		else
		{
			StudentCache.getInstance().removeStudent(this.id);
		}
	}

	@Override
	public String getTitle()
	{
		return "Edit Student";
	}

}
