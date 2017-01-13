package com.mattihew.cswk.programming2.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.SortedSet;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.mattihew.cswk.programming2.model.students.Student;
import com.mattihew.cswk.programming2.model.students.StudentCache;

public class StudentsView extends JFrame implements Observer
{
	/** serialVersionUID */
	private static final long serialVersionUID = 5870316368738488041L;
	private JTable table;
	private DefaultTableModel tableModel;

	/**
	 * Create the frame.
	 */
	public StudentsView()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
			
		}
		this.setTitle("Students");
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		JMenu mnInsert = new JMenu("Insert");
		menuBar.add(mnInsert);
		
		JMenuItem mntmNewStudent = new JMenuItem("New Student");
		mntmNewStudent.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				for (int i = 0; i < StudentsView.this.tableModel.getColumnCount(); i++)
				{
					StudentCache.getInstance().addStudent(new Student("test1", "test2", "0123456789"));
				}
			}
		});
		mnInsert.add(mntmNewStudent);
		
		this.tableModel = new MyTableModel(new String[]{"First Name","Last Name", "Phone Number"}, 0);
		this.table = new JTable(this.tableModel);
		this.getContentPane().add(table.getTableHeader(), BorderLayout.NORTH);
		this.getContentPane().add(table, BorderLayout.CENTER);
		StudentCache.getInstance().addObserver(this);
		this.populateTable();
	}
	
	private void addStudentToTable(final Student student)
	{
		final String[] data = {student.getFirstName(), student.getLastName(), student.getPhoneNum()};
		this.tableModel.addRow(data);
	}

	private void populateTable()
	{
		for (final Student student : StudentCache.getInstance().getStudents())
		{
			this.addStudentToTable(student);
		}
	}
	
	@Override
	public void update(Observable o, Object arg)
	{
		if (o instanceof StudentCache && arg instanceof Student)
		{
			this.addStudentToTable((Student) arg);
		}
	}
}
