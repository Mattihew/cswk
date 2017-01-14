package com.mattihew.cswk.programming2.controller;

import java.awt.EventQueue;

import javax.swing.UIManager;

import com.mattihew.cswk.programming2.model.students.Student;
import com.mattihew.cswk.programming2.model.students.StudentCache;
import com.mattihew.cswk.programming2.view.StudentsView;
import com.mattihew.cswk.programming2.view.TripsView;

public class Controller
{
	/**
	 * Launch the application.
	 */
	public static void main(final String[] args)
	{
		StudentCache.getInstance().addStudent(new Student("Matt","Rayner","01234567890"));
		EventQueue.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				}
				catch (Exception e)
				{
					
				}
				TripsView tripsWindow = new TripsView();
				StudentsView studentsWindow = new StudentsView();
				studentsWindow.setVisible(true);
			}
		});
	}
}
