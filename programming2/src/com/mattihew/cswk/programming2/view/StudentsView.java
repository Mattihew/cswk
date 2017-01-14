package com.mattihew.cswk.programming2.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mattihew.cswk.programming2.model.students.Student;
import com.mattihew.cswk.programming2.model.students.StudentCache;
import com.mattihew.cswk.programming2.view.util.UneditableDefaultTableModel;

public class StudentsView extends JFrame implements Observer
{
	/** serialVersionUID */
	private static final long serialVersionUID = 5870316368738488041L;
	private final JTable table;
	private final DefaultTableModel tableModel;

	/**
	 * Create the frame.
	 */
	public StudentsView()
	{
		super("Students");
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
			public void actionPerformed(final ActionEvent e)
			{
				final NewStudentView newStudent = new NewStudentView(StudentsView.this);
			}
		});
		mnInsert.add(mntmNewStudent);
		
		this.tableModel = new UneditableDefaultTableModel(new String[]{"ID", "First Name","Last Name", "Phone Number"}, 0);
		this.table = new JTable(this.tableModel);
		this.getContentPane().add(this.table.getTableHeader(), BorderLayout.NORTH);
		this.getContentPane().add(this.table, BorderLayout.CENTER);
		
		JPopupMenu popupMenu = new JPopupMenu();
		StudentsView.addPopup(this.table, popupMenu);
		
		JMenuItem mntmEdit = new JMenuItem("Edit");
		popupMenu.add(mntmEdit);
		StudentCache.getInstance().addObserver(this);
		this.populateTable();
	}
	
	private void addStudentToTable(final UUID id, final Student student)
	{
		final String[] data = {id.toString(), student.getFirstName(), student.getLastName(), student.getPhoneNum()};
		this.tableModel.addRow(data);
	}

	private void populateTable()
	{
		for (final Entry<UUID, Student> studentEntry : StudentCache.getInstance().getStudents().entrySet())
		{
			this.addStudentToTable(studentEntry.getKey(), studentEntry.getValue());
		}
	}
	
	@Override
	public void update(final Observable o, final Object arg)
	{
		if (o instanceof StudentCache && arg instanceof UUID)
		{
			this.addStudentToTable((UUID) arg, StudentCache.getInstance().getStudents().get(arg));
		}
	}
	
	private static void addPopup(final Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
				if (e.isPopupTrigger()) {
					this.showMenu(e);
				}
			}
			@Override
			public void mouseReleased(final MouseEvent e) {
				if (e.isPopupTrigger()) {
					this.showMenu(e);
				}
			}
			private void showMenu(final MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
