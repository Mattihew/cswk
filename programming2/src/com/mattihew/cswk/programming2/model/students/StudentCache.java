package com.mattihew.cswk.programming2.model.students;

import com.mattihew.cswk.programming2.model.abstracts.RecordCache;

public class StudentCache extends RecordCache<Student>
{
	private static StudentCache INSTANCE = null;
	
	private StudentCache()
	{
		super();
	}
	
	public static StudentCache getInstance()
	{
		if (StudentCache.INSTANCE == null)
		{
			StudentCache.INSTANCE = new StudentCache();
		}
		return StudentCache.INSTANCE;
	}

	@Override
	public String getRecordName()
	{
		return "Student";
	}

	@Override
	public String getRecordNamePlural()
	{
		return "Students";
	}
}