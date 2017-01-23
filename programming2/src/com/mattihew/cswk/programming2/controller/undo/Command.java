package com.mattihew.cswk.programming2.controller.undo;

public interface Command
{
	void doCommand();
	
	void undoCommand();
	
	default void redoCommand()
	{
		this.doCommand();
	}
	
	String getTitle();
}
