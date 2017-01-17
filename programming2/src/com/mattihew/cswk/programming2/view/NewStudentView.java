package com.mattihew.cswk.programming2.view;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.mattihew.cswk.programming2.model.students.Student;
import com.mattihew.cswk.programming2.model.students.StudentCache;

public class NewStudentView extends JDialog
{
	private final JTextField txtFirstName;
	private final JTextField txtLastName;
	private final JTextField txtPhoneNum;
	private final UUID id;
	
	public NewStudentView(final Frame owner)
	{
		this(owner, null, null);
	}
	
	public NewStudentView(final Frame owner, final Student student)
	{
		this(owner, student, null);
	}
	
	/** @wbp.parser.constructor */
	public NewStudentView(final Frame owner, final Student student, final UUID existingId)
	{
		super(owner, "New Student", true);
		this.setResizable(false);
		this.setBounds(100, 100, 250, 150);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(new GridLayout(0, 2, 0, 2));
		
		// First Name
		JLabel lblFirstName = new JLabel("First Name:");
		this.getContentPane().add(lblFirstName);
		
		this.txtFirstName = new JTextField();
		this.getContentPane().add(this.txtFirstName);
		this.txtFirstName.setColumns(10);
		
		// Last Name
		JLabel lblLastName = new JLabel("Last Name:");
		this.getContentPane().add(lblLastName);
		
		this.txtLastName = new JTextField();
		this.getContentPane().add(this.txtLastName);
		this.txtLastName.setColumns(10);
		
		//Phone Number
		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		this.getContentPane().add(lblPhoneNumber);
		
		this.txtPhoneNum = new JTextField();
		this.getContentPane().add(this.txtPhoneNum);
		this.txtPhoneNum.setColumns(10);
		
		this.id = existingId;
		if (student != null)
		{
			this.setTitle("Edit Student");
			this.txtFirstName.setText(student.getFirstName());
			this.txtLastName.setText(student.getLastName());
			this.txtPhoneNum.setText(student.getPhoneNum());
		}
		
		JButton btnOk = new JButton("OK");
		this.getContentPane().add(btnOk);
		this.getRootPane().setDefaultButton(btnOk);
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				final Student student = new Student(
						NewStudentView.this.txtFirstName.getText(),
						NewStudentView.this.txtLastName.getText(),
						NewStudentView.this.txtPhoneNum.getText());
				if (NewStudentView.this.id == null)
				{
					StudentCache.getInstance().addStudent(student);
				}
				else
				{
					StudentCache.getInstance().putStudent(NewStudentView.this.id, student);
				}
				NewStudentView.this.dispatchEvent(new WindowEvent(NewStudentView.this, WindowEvent.WINDOW_CLOSING));
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		this.getContentPane().add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				NewStudentView.this.dispatchEvent(new WindowEvent(NewStudentView.this, WindowEvent.WINDOW_CLOSING));
			}
		});
		
		this.setVisible(true);
	}
	
	
}
