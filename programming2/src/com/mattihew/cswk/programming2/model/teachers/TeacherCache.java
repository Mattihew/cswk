package com.mattihew.cswk.programming2.model.teachers;

import com.mattihew.cswk.programming2.model.abstracts.RecordCache;

public class TeacherCache extends RecordCache<Teacher>
{
	private static TeacherCache INSTANCE = null;
	
	private TeacherCache()
	{
		super();
	}
	
	public static TeacherCache getInstance()
	{
		if (TeacherCache.INSTANCE == null)
		{
			TeacherCache.INSTANCE = new TeacherCache();
		}
		return TeacherCache.INSTANCE;
	}

	@Override
	public String getRecordName()
	{
		return "Teacher";
	}

	@Override
	public String getRecordNamePlural()
	{
		return "Teachers";
	}
}