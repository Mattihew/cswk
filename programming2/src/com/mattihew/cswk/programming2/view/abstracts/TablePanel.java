package com.mattihew.cswk.programming2.view.abstracts;

import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mattihew.cswk.programming2.view.util.UneditableDefaultTableModel;

public abstract class TablePanel extends Panel
{
	/** serialVersionUID */
	private static final long serialVersionUID = 5870316368738488041L;
	protected final JTable table;
	protected final DefaultTableModel tableModel;
	
	/**
	 * Create the frame.
	 * @wbp.parser.constructor
	 */
	public TablePanel(final List<String> tableHeadings)
	{
		super(new BorderLayout());
		
		final List<String> headings = new LinkedList<String>(tableHeadings);
		headings.add(0, "ID");
		this.tableModel = new UneditableDefaultTableModel(headings.toArray(), 0);
		this.table = new JTable(this.tableModel);
		this.add(this.table.getTableHeader(), BorderLayout.NORTH);
		this.add(this.table, BorderLayout.CENTER);
		
		JPopupMenu popupMenu = new JPopupMenu();
		this.addPopup(this.table, popupMenu);
		
		JMenuItem mntmEdit = new JMenuItem("Edit");
		popupMenu.add(mntmEdit);
		mntmEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				TablePanel.this.editActionPerformed(e);
			}
		});
		
		JMenuItem mntmRemove = new JMenuItem("Remove");
		popupMenu.add(mntmRemove);
		mntmRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				TablePanel.this.removeActionPerformed(e);
			}
		});
	}
	
	protected abstract void editActionPerformed(final ActionEvent e);
	
	protected abstract void removeActionPerformed(final ActionEvent e);

	
	private void addPopup(final JTable table, final JPopupMenu popup) {
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
					TablePanel.this.repaint();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
	}
}
