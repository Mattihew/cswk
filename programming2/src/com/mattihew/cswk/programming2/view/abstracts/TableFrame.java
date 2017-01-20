package com.mattihew.cswk.programming2.view.abstracts;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mattihew.cswk.programming2.view.util.UneditableDefaultTableModel;

public abstract class TableFrame<E> extends JFrame
{
	/** serialVersionUID */
	private static final long serialVersionUID = 5870316368738488041L;
	protected final JTable table;
	protected final DefaultTableModel tableModel;

	/**
	 * Create the frame.
	 */
	public TableFrame(final String title, final String itemName, final List<String> tableHeadings)
	{
		super(title);
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		JMenu mnInsert = new JMenu("Insert");
		menuBar.add(mnInsert);
		
		JMenuItem mntmNewItem = new JMenuItem("New " + itemName);
		mntmNewItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				TableFrame.this.newActionPerformed(e);
			}
		});
		mnInsert.add(mntmNewItem);
		final List<String> headings = new LinkedList<String>(tableHeadings);
		headings.add(0, "ID");
		this.tableModel = new UneditableDefaultTableModel(headings.toArray(), 0);
		this.table = new JTable(this.tableModel);
		this.getContentPane().add(this.table.getTableHeader(), BorderLayout.NORTH);
		this.getContentPane().add(this.table, BorderLayout.CENTER);
		
		JPopupMenu popupMenu = new JPopupMenu();
		TableFrame.addPopup(this.table, popupMenu);
		
		JMenuItem mntmEdit = new JMenuItem("Edit");
		popupMenu.add(mntmEdit);
		mntmEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				TableFrame.this.editActionPerformed(e);
			}
		});
		this.setVisible(true);
	}
	
	protected abstract void newActionPerformed(final ActionEvent e);
	
	protected abstract void editActionPerformed(final ActionEvent e);

	
	private static void addPopup(final JTable table, final JPopupMenu popup) {
		table.addMouseListener(new MouseAdapter() {
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
				int row = table.rowAtPoint(e.getPoint());
				if (row != -1)
				{
					table.setRowSelectionInterval(row, row);
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
	}
}
