package com.mattihew.cswk.programming2.controller.undo;

import java.util.Objects;
import java.util.UUID;

import com.mattihew.cswk.programming2.model.students.Student;
import com.mattihew.cswk.programming2.model.students.StudentCache;

public class CreateStudentCommand implements Command
{
	private final Student newStudent;
	private Student oldStudent;
	private UUID id;
	
	public CreateStudentCommand(final Student student)
	{
		this(student, null);
	}
	
	public CreateStudentCommand(final Student student, final UUID id)
	{
		this.newStudent = Objects.requireNonNull(student, "Student required for creation");
		this.id = id;
	}
	
	@Override
	public void doCommand()
	{
		if (Objects.isNull(this.id))
		{
			this.id = StudentCache.getInstance().addStudent(this.newStudent);
		}
		else
		{
			this.oldStudent = StudentCache.getInstance().putStudent(this.id, this.newStudent);
		}
		
	}

	@Override
	public void undoCommand()
	{
		if (Objects.isNull(this.oldStudent))
		{
			StudentCache.getInstance().removeStudent(this.id);
		}
		else
		{
			StudentCache.getInstance().putStudent(this.id, this.oldStudent);
		}
	}

	@Override
	public String getTitle()
	{
		return "Create Student";
	}

}
