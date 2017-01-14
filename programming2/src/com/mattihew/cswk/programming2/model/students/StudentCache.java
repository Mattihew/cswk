package com.mattihew.cswk.programming2.model.students;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Observable;
import java.util.UUID;

public class StudentCache extends Observable
{
	private static StudentCache INSTANCE = null;
	
	private final Map<UUID, Student> students;
	
	private StudentCache()
	{
		super();
		this.students = new LinkedHashMap<>();
	}
	
	public static StudentCache getInstance()
	{
		if (StudentCache.INSTANCE == null)
		{
			StudentCache.INSTANCE = new StudentCache();
		}
		return StudentCache.INSTANCE;
	}
	
	public Map<UUID, Student> getStudents()
	{
		return Collections.unmodifiableMap(this.students);
	}
	
	public boolean addStudent(final Student student)
	{
		final UUID studentID = UUID.randomUUID();
		final boolean result = student.equals(this.students.put(studentID, student));
		if (result)
		{
			this.setChanged();
			this.notifyObservers(studentID);
		}
		return result;
	}
}