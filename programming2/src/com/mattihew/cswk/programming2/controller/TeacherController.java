package com.mattihew.cswk.programming2.controller;

import java.awt.Frame;
import java.awt.Panel;
import java.util.UUID;

import com.mattihew.cswk.programming2.controller.interfaces.UIController;
import com.mattihew.cswk.programming2.model.Teacher;

public class TeacherController implements UIController<Teacher>
{
	public TeacherController()
	{
		super();
	}
	
	@Override
	public String getItemName()
	{
		return "Teacher";
	}

	@Override
	public void createRecord(final Teacher element)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createRecord(final Teacher element, final UUID id)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editRecord(final UUID id, final Teacher element)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeRecord(final UUID id)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Panel getUIPanel(final Frame owner)
	{
		// TODO Auto-generated method stub
		return new Panel();
	}

	@Override
	public void insertNewItem(final Frame owner)
	{
		// TODO Auto-generated method stub
		
	}



}
