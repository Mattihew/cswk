package com.mattihew.cswk.programming2.view.abstracts;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public abstract class EditDialog<E> extends JDialog
{
	/** serialVersionUID */
	private static final long serialVersionUID = 3975565360523394130L;
	private final List<JTextField> textFields = new ArrayList<>();
	
	public EditDialog(final Frame owner, final String title)
	{
		this(owner, title, null);
	}
	
	/** @wbp.parser.constructor */
	public EditDialog(final Frame owner, final String title, final E element)
	{
		super(owner, title, true);
		this.setResizable(false);
		this.setBounds(100, 100, 250, 150);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(new GridLayout(0, 2, 0, 2));
		
		this.insertValues(element);
		
		JButton btnOk = new JButton("OK");
		this.getContentPane().add(btnOk);
		this.getRootPane().setDefaultButton(btnOk);
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				EditDialog.this.okActionPerformed(EditDialog.this.textFields, e);
				EditDialog.this.dispatchEvent(new WindowEvent(EditDialog.this, WindowEvent.WINDOW_CLOSING));
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		this.getContentPane().add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				EditDialog.this.dispatchEvent(new WindowEvent(EditDialog.this, WindowEvent.WINDOW_CLOSING));
			}
		});
		
		this.setVisible(true);
	}
	
	protected abstract void okActionPerformed(final List<JTextField> textFields, final ActionEvent e);
	
	protected abstract void insertValues(final E element);
	
	protected final void setValues(final List<String> labelValues)
	{
		this.setValues(labelValues, Collections.emptyList());
	}
	
	protected final void setValues(final List<String> labelValues, final List<String> textValues)
	{
		if (textValues.size() > labelValues.size())
		{
			throw new IllegalArgumentException("number of input boxes exceeds number of labels");
		}
		for (final ListIterator<String> i = labelValues.listIterator(); i.hasNext();)
		{
			JLabel lbl = new JLabel(i.next());
			this.getContentPane().add(lbl);
			
			final JTextField txtField = new JTextField();
			if (i.previousIndex() < textValues.size())
			{
				txtField.setText(textValues.get(i.previousIndex()));
			}
			this.textFields.add(txtField);
			this.getContentPane().add(txtField);
			txtField.setColumns(10);
		}
	}
	
}
