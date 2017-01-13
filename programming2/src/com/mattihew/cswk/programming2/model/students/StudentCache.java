package com.mattihew.cswk.programming2.model.students;

import java.util.Collections;
import java.util.Observable;
import java.util.Set;
import java.util.TreeSet;

public class StudentCache extends Observable
{
	private static StudentCache INSTANCE = null;
	
	private Set<Student> students;
	
	private StudentCache()
	{
		super();
		this.students = new TreeSet<>();
	}
	
	public static StudentCache getInstance()
	{
		if (StudentCache.INSTANCE == null)
		{
			StudentCache.INSTANCE = new StudentCache();
		}
		return StudentCache.INSTANCE;
	}
	
	public Set<Student> getStudents()
	{
		return Collections.unmodifiableSet(this.students);
	}
	
	public boolean addStudent(final Student student)
	{
		final boolean result = this.students.add(student);
		if (result)
		{
			this.setChanged();
			this.notifyObservers(student);
		}
		return result;
	}
}