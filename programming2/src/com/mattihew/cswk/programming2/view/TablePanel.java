package com.mattihew.cswk.programming2.view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.UUID;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import com.mattihew.cswk.programming2.controller.TablePanelUIController;
import com.mattihew.cswk.programming2.model.interfaces.TableRecord;

public class TablePanel extends Panel
{
	/** serialVersionUID */
	private static final long serialVersionUID = 5870316368738488041L;
	private final JTable table;
	private final JPopupMenu popupMenu;
	
	/**
	 * Create the frame.
	 * @wbp.parser.constructor
	 */
	public TablePanel(final Frame owner, final TablePanelUIController<? extends TableRecord> controller)
	{
		super(new BorderLayout());
		
		this.table = new JTable(controller.getTableModel());
		this.add(this.table.getTableHeader(), BorderLayout.NORTH);
		this.add(this.table, BorderLayout.CENTER);
		
		this.popupMenu = new JPopupMenu();
		this.addPopup(this.table, this.popupMenu);
		
		this.addPopupMenuItem("Edit", new ActionListener()
		{
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				final UUID id = TablePanel.this.getSelectedUUID();
				controller.editExistingItem(owner, id);
			}
		});
		
		this.addPopupMenuItem("Remove", new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				final UUID id = TablePanel.this.getSelectedUUID();
				controller.removeExistingItem(owner, id);
			}
		});
	}
	
	public UUID getSelectedUUID()
	{
		return (UUID) this.table.getValueAt(this.table.getSelectedRow(), 0);
	}
	
	public void addPopupMenuItem(final String name, final ActionListener listener)
	{
		JMenuItem mntmItem = new JMenuItem(name);
		mntmItem.addActionListener(listener);
		this.popupMenu.add(mntmItem);
	}
	
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
