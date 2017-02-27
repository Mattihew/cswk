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
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.mattihew.cswk.programming2.controller.TablePanelUIController;
import com.mattihew.cswk.programming2.controller.interfaces.UIController;
import com.mattihew.cswk.programming2.model.interfaces.TableRecord;

public class EditDialog<R extends TableRecord> extends JDialog
{
	/** serialVersionUID */
	private static final long serialVersionUID = 3975565360523394130L;
	private final List<JComponent> components = new ArrayList<>();
	private final TablePanelUIController<R> controller;
	
	public EditDialog(final Frame owner, final TablePanelUIController<R> controller)
	{
		this(owner, controller, null, null);
	}
	
	public EditDialog(final Frame owner, final TablePanelUIController<R> controller, final R record)
	{
		this(owner, controller, record, null);
	}
	
	/** @wbp.parser.constructor */
	public EditDialog(final Frame owner, final TablePanelUIController<R> controller, final R record, final UUID id)
	{
		super(owner, true);
		this.controller = Objects.requireNonNull(controller, "Controller Required");
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(new GridLayout(0, 2, 0, 2));
		
		if (Objects.isNull(record))
		{
			this.setTitle("New " + this.controller.getRecordName());
			this.setValues(null);
		}
		else
		{
			this.setTitle("Edit " + this.controller.getRecordName());
			this.setValues(null, null);
		}
		
		JButton btnOk = new JButton("OK");
		this.getContentPane().add(btnOk);
		this.getRootPane().setDefaultButton(btnOk);
		btnOk.addActionListener(new OkActionEvent<>(this, this.controller, id, this.components));
		
		JButton btnCancel = new JButton("Cancel");
		this.getContentPane().add(btnCancel);
		btnCancel.addActionListener(new CloseActionEvent(this));
	}
	
	protected void setValues(final List<String> labelValues)
	{
		this.setValues(labelValues, Collections.emptyList());
	}
	
	protected void setValues(final List<String> labelValues, final List<Object> textValues)
	{
		if (textValues.size() > labelValues.size())
		{
			throw new IndexOutOfBoundsException("number of input boxes exceeds number of labels");
		}
		for (final ListIterator<String> i = labelValues.listIterator(); i.hasNext();)
		{
			JLabel lbl = new JLabel(i.next());
			this.getContentPane().add(lbl);
			
			final Object[] comboOptions = this.controller.comboOptions(i.previousIndex());
			if (Objects.nonNull(comboOptions))
			{
				final JComboBox<Object> comboField = new JComboBox<>(comboOptions);
				if (i.previousIndex() < textValues.size())
				{
					comboField.setSelectedItem(textValues.get(i.previousIndex()).toString());
				}
				this.components.add(comboField);
				this.getContentPane().add(comboField);
			}
			else
			{
				final JTextField txtField = new JTextField();
				if (i.previousIndex() < textValues.size())
				{
					txtField.setText(textValues.get(i.previousIndex()).toString());
				}
				this.components.add(txtField);
				this.getContentPane().add(txtField);
				txtField.setColumns(10);
			}
		}
		this.setBounds(100, 100, 250, 35*(labelValues.size()+1));
	}
	
	private class CloseActionEvent implements ActionListener
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
	
	private class OkActionEvent<E> extends CloseActionEvent
	{
		private final UIController<E> controller;
		private final List<JComponent> componentFields;
		private final UUID id;
		
		public OkActionEvent(final Dialog dialog, final UIController<E> controller,  final UUID id, final List<JComponent> textFields)
		{
			super(dialog);
			this.controller = controller;
			this.id = id;
			this.componentFields = textFields;
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
}
