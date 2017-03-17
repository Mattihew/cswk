package com.mattihew.cswk.programming2.view;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
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
import com.mattihew.cswk.programming2.model.tableModel.RecordCacheTableModel;
import com.mattihew.cswk.programming2.view.actions.CloseActionEvent;
import com.mattihew.cswk.programming2.view.actions.OkActionEvent;

public class EditDialog<R extends TableRecord> extends JDialog
{
	/** serialVersionUID */
	private static final long serialVersionUID = 3975565360523394130L;
	private final List<JComponent> components = new ArrayList<>();
	private final TablePanelUIController<R> controller;
	
	/**
	 * Class Constructor.
	 *
	 * @param owner the frame that this dialog is a child of.
	 * @param controller the controller this UI interacts with.
	 */
	public EditDialog(final Frame owner, final TablePanelUIController<R> controller)
	{
		this(owner, controller, null, null);
	}
	
	/**
	 * Class Constructor.
	 *
	 * @param owner the frame that this dialog is a child of.
	 * @param controller the controller this UI interacts with.
	 * @param record the record to populate the inputs with.
	 */
	public EditDialog(final Frame owner, final TablePanelUIController<R> controller, final R record)
	{
		this(owner, controller, record, null);
	}
	
	/**
	 * Class Constructor.
	 *
	 * @param owner the frame that this dialog is a child of.
	 * @param controller the controller this UI interacts with.
	 * @param record the record to populate the inputs with.
	 * @param id the id of the record being edited.
	 * @wbp.parser.constructor
	 */
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
		}
		else
		{
			this.setTitle("Edit " + this.controller.getRecordName());
		}
		this.setValues(record);
		JButton btnOk = new JButton("OK");
		this.getContentPane().add(btnOk);
		this.getRootPane().setDefaultButton(btnOk);
		btnOk.addActionListener(new OkActionEvent<>(this, this.controller, this.components, id));
		
		JButton btnCancel = new JButton("Cancel");
		this.getContentPane().add(btnCancel);
		btnCancel.addActionListener(new CloseActionEvent(this));
	}
	
	/**
	 * Populates the UI with values from a Record.
	 * 
	 * @param record the record to read values from.
	 */
	private final void setValues(final R record)
	{
		final RecordCacheTableModel tableModel = this.controller.getTableModel();
		final int columnCount = tableModel.getColumnCount() - 1;
		
		for (int i = 0; i < columnCount; i++)
		{
			JLabel lbl = new JLabel(tableModel.getColumnName(i + 1));
			this.getContentPane().add(lbl);
			
			final JComponent comp;
			final String[] options = this.controller.comboOptions(i);
			if (Objects.nonNull(options))
			{
				comp = new JComboBox<>(options);
			}
			else
			{
				final JTextField txtField = new JTextField();
				if (Objects.nonNull(record))
				{
					txtField.setText(Objects.toString(record.getValueAt(i),""));
				}
				txtField.setColumns(10);
				comp = txtField;
			}
			this.components.add(comp);
			this.getContentPane().add(comp);
		}
		this.setBounds(100, 100, 250, 35*(columnCount+1));
	}
}
