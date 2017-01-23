package com.mattihew.cswk.programming2.controller.undo;

import java.util.Objects;
import java.util.UUID;

import com.mattihew.cswk.programming2.model.students.Student;
import com.mattihew.cswk.programming2.model.students.StudentCache;

/**
 * Adds a student to the student storage.
 * 
 * @author Matt Rayner
 */
public class CreateStudentAction implements UndoableAction
{
	private final Student newStudent;
	private Student oldStudent;
	private UUID id;
	
	/**
	 * Class Constructor.
	 *
	 * @param student the new student to add.
	 */
	public CreateStudentAction(final Student student)
	{
		this(student, null);
	}
	
	/**
	 * Class Constructor.
	 * 
	 * Potentially use {@link EditStudentAction#EditStudentAction(UUID, Student)} instead.
	 * 
	 * @param student the new student to add.
	 * @param id the id to assign to the student.
	 */
	public CreateStudentAction(final Student student, final UUID id)
	{
		this.newStudent = Objects.requireNonNull(student, "Student required for creation");
		this.id = id;
	}
	
	@Override
	public void doAction()
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
	public void undoAction()
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
