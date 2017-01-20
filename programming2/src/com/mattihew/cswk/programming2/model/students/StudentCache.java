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
	
	public Student getStudent(final UUID id)
	{
		return this.students.get(id);
	}
	
	public Map<UUID, Student> getStudents()
	{
		return Collections.unmodifiableMap(this.students);
	}
	
	public Student removeStudent(final UUID id)
	{
		final Student result = this.students.remove(id);
		this.setChanged();
		this.notifyObservers(Collections.singleton(id));
		return result;
	}
	
	public UUID addStudent(final Student student)
	{
		if (this.students.containsValue(student))
		{
			return null;
		}
		final UUID studentID = UUID.randomUUID();
		this.students.put(studentID, student);
		this.setChanged();
		this.notifyObservers(Collections.singleton(studentID));
		return studentID;
	}
	
	public Student putStudent(final UUID id, final Student student)
	{
		final Student result = this.students.put(id, student);
		if (!student.equals(result))
		{
			this.setChanged();
			this.notifyObservers(Collections.singleton(id));
		}
		return result;
	}
}