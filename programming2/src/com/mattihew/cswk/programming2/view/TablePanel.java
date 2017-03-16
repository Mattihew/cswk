package com.mattihew.cswk.programming2.view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.UUID;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import com.mattihew.cswk.programming2.controller.TablePanelUIController;

public class TablePanel extends Panel
{
	/** serialVersionUID */
	private static final long serialVersionUID = 5870316368738488041L;
	private final JTable table;
	private final JPopupMenu popupMenu;
	
	/**
	 * Class Constructor.
	 *
	 * @param owner this panel's owner
	 * @param controller the controller to get Data from
	 * @wbp.parser.constructor
	 */
	public TablePanel(final Frame owner, final TablePanelUIController<?> controller)
	{
		super(new BorderLayout());
		
		this.table = new JTable(controller.getTableModel());
		this.add(this.table.getTableHeader(), BorderLayout.NORTH);
		this.add(this.table, BorderLayout.CENTER);
		
		this.popupMenu = new JPopupMenu();
		this.addPopup(this.table, this.popupMenu);
		
		this.addPopupMenuItem("Edit", (e) -> controller.editExistingItem(owner, TablePanel.this.getSelectedUUID()));
		this.addPopupMenuItem("Remove", (e) -> controller.removeExistingItem(owner, TablePanel.this.getSelectedUUID()));
	}
	
	/**
	 * Gets the UUID of the selected item in the table.
	 * 
	 * @return a UUID.
	 */
	public UUID getSelectedUUID()
	{
		return (UUID) this.table.getValueAt(this.table.getSelectedRow(), 0);
	}
	
	/**
	 * Adds a new popup menu item to the popup menu.
	 * 
	 * @param name the name to display in the popup menu
	 * @param listener the action to perform when menu item clicked
	 */
	public void addPopupMenuItem(final String name, final ActionListener listener)
	{
		JMenuItem mntmItem = new JMenuItem(name);
		mntmItem.addActionListener(listener);
		this.popupMenu.add(mntmItem);
	}
	
	/**
	 * attaches a popup menu to a table.
	 * 
	 * @param table the table to attach the popup to
	 * @param popup the popup to attach
	 */
	private void addPopup(final JTable table, final JPopupMenu popup)
	{
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
