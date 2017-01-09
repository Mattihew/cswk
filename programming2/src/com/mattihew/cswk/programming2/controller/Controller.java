package com.mattihew.cswk.programming2.controller;

import java.awt.EventQueue;

import com.mattihew.cswk.programming2.view.TripsWindow;

public class Controller
{
	/**
	 * Launch the application.
	 */
	public static void main(final String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				TripsWindow window = new TripsWindow();
				window.setVisible(true);
			}
		});
	}
}
