package com.mattihew.cswk.programming2.view.actions;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;

import com.mattihew.cswk.programming2.controller.interfaces.UIController;

/**
 * Creates/Edits the record by submitting to the controller.
 * 
 * @author Matt Rayner
 * @param <E> the type of record the controller is for.
 */
public class OkActionEvent<E> extends CloseActionEvent
{
	private final UIController<E> controller;
	private final List<JComponent> componentFields;
	private final UUID id;
	
	public OkActionEvent(final Dialog dialog, final UIController<E> controller, final List<JComponent> textFields, final UUID id)
	{
		super(dialog);
		this.controller = controller;
		this.componentFields = textFields;
		this.id = id;
	}
	
	@Override
	public void actionPerformed(final ActionEvent e)
	{
		final Object[] values = new String[this.componentFields.size()];
		int i = 0;
		for (final JComponent componentField : this.componentFields)
		{
			if (componentField instanceof JTextField)
			{
				values[i++] = ((JTextField)componentField).getText();
			}
			else if (componentField instanceof JComboBox)
			{
				values[i++] = ((JComboBox<?>)componentField).getSelectedItem();
			}
		}
		if (Objects.isNull(this.id))
		{
			this.controller.createRecord(values);
		}
		else
		{
			this.controller.editRecord(this.id, values);
		}
		super.actionPerformed(e);
	}
	
}