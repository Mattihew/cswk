package com.mattihew.cswk.programming2.controller;

import java.util.Objects;
import java.util.UUID;

import com.mattihew.cswk.programming2.controller.interfaces.UIController;
import com.mattihew.cswk.programming2.model.students.Student;
import com.mattihew.cswk.programming2.model.students.StudentCache;
import com.mattihew.cswk.programming2.view.StudentsView;

public class StudentController implements UIController<Student>
{
	private StudentsView frame;
	
	public StudentController()
	{
		StudentCache.getInstance().addStudent(new Student("Matt","Rayner","01234567890"));
		StudentCache.getInstance().addStudent(new Student("Test1","Test2","123"));
	}
	
	public void showUI()
	{
		this.frame = new StudentsView(this);
	}

	@Override
	public void createRecord(final Student element)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createRecord(final Student student, final UUID id)
	{
		if (Objects.isNull(id))
		{
			StudentCache.getInstance().addStudent(student);
		}
		else
		{
			StudentCache.getInstance().putStudent(id, student);
		}
	}
}
