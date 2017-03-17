package com.mattihew.cswk.programming2.view.actions;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * Close dialog action.
 * 
 * @author Matt Rayner
 */
public class CloseActionEvent implements ActionListener
{
	private final Dialog dialog;
	public CloseActionEvent(final Dialog dialog)
	{
		this.dialog = dialog;
	}
	@Override
	public void actionPerformed(final ActionEvent e)
	{
		this.dialog.dispatchEvent(new WindowEvent(this.dialog, WindowEvent.WINDOW_CLOSING));
	}
}
