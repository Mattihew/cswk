package com.mattihew.cswk.programming2.view;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.mattihew.cswk.programming2.controller.interfaces.UIController;
import com.mattihew.cswk.programming2.model.interfaces.TableRecord;

public class EditDialog<R extends TableRecord> extends JDialog
{
	/** serialVersionUID */
	private static final long serialVersionUID = 3975565360523394130L;
	private final List<JTextField> textFields = new ArrayList<>();
	
	public EditDialog(final Frame owner, final UIController<R> controller)
	{
		this(owner, controller, null, null);
	}
	
	public EditDialog(final Frame owner, final UIController<R> controller, final R record)
	{
		this(owner, controller, record, null);
	}
	
	/** @wbp.parser.constructor */
	public EditDialog(final Frame owner, final UIController<R> controller, final R record, final UUID id)
	{
		super(owner, true);
		this.setResizable(false);
		this.setBounds(100, 100, 250, 150);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(new GridLayout(0, 2, 0, 2));
		
		if (Objects.isNull(record))
		{
			this.setTitle("New " + controller.getRecordName());
			this.setValues(controller.getTableHeadings());
		}
		else
		{
			this.setTitle("Edit " + controller.getRecordName());
			this.setValues(controller.getTableHeadings(), record.toTableColumnValues());
		}
		
		JButton btnOk = new JButton("OK");
		this.getContentPane().add(btnOk);
		this.getRootPane().setDefaultButton(btnOk);
		btnOk.addActionListener(new OkActionEvent<>(this, controller, id, this.textFields));
		
		JButton btnCancel = new JButton("Cancel");
		this.getContentPane().add(btnCancel);
		btnCancel.addActionListener(new CancelActionEvent(this));
	}
	
	private final void setValues(final List<String> labelValues)
	{
		this.setValues(labelValues, Collections.emptyList());
	}
	
	private final void setValues(final List<String> labelValues, final List<Object> textValues)
	{
		if (textValues.size() > labelValues.size())
		{
			throw new IndexOutOfBoundsException("number of input boxes exceeds number of labels");
		}
		for (final ListIterator<String> i = labelValues.listIterator(); i.hasNext();)
		{
			JLabel lbl = new JLabel(i.next());
			this.getContentPane().add(lbl);
			
			final JTextField txtField = new JTextField();
			if (i.previousIndex() < textValues.size())
			{
				txtField.setText(textValues.get(i.previousIndex()).toString());
			}
			this.textFields.add(txtField);
			this.getContentPane().add(txtField);
			txtField.setColumns(10);
		}
	}
	
	private class CancelActionEvent implements ActionListener
	{
		private final Dialog dialog;
		public CancelActionEvent(final Dialog dialog)
		{
			this.dialog = dialog;
		}
		@Override
		public void actionPerformed(final ActionEvent e)
		{
			this.dialog.dispatchEvent(new WindowEvent(this.dialog, WindowEvent.WINDOW_CLOSING));
		}
	}
	
	private class OkActionEvent<E> extends CancelActionEvent
	{
		private final UIController<E> controller;
		private final List<JTextField> textFields;
		private final UUID id;
		
		public OkActionEvent(final Dialog dialog, final UIController<E> controller,  final UUID id, final List<JTextField> textFields)
		{
			super(dialog);
			this.controller = controller;
			this.id = id;
			this.textFields = textFields;
		}
		
		@Override
		public void actionPerformed(final ActionEvent e)
		{
			final String[] stringValues = new String[this.textFields.size()];
			int i = 0;
			for (final JTextField textField : this.textFields)
			{
				stringValues[i++] = textField.getText();
			}
			if (Objects.isNull(this.id))
			{
				this.controller.createRecord(stringValues);
			}
			else
			{
				this.controller.editRecord(this.id, stringValues);
			}
			super.actionPerformed(e);
		}
		
	}
}
