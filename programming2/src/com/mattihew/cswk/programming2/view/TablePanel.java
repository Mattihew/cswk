package com.mattihew.cswk.programming2.view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mattihew.cswk.programming2.controller.interfaces.UIController;
import com.mattihew.cswk.programming2.model.RecordCache;
import com.mattihew.cswk.programming2.model.interfaces.TableRecord;
import com.mattihew.cswk.programming2.view.util.UneditableDefaultTableModel;

public class TablePanel extends Panel implements Observer
{
	/** serialVersionUID */
	private static final long serialVersionUID = 5870316368738488041L;
	protected final JTable table;
	protected final DefaultTableModel tableModel;
	
	/**
	 * Create the frame.
	 * @wbp.parser.constructor
	 */
	public TablePanel(final Frame owner, final List<String> tableHeadings, final UIController<? extends TableRecord> controller)
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
				final UUID id = (UUID) TablePanel.this.tableModel.getValueAt(TablePanel.this.table.getSelectedRow(), 0);
				controller.editExistingItem(owner, id);
			}
		});
		
		JMenuItem mntmRemove = new JMenuItem("Remove");
		popupMenu.add(mntmRemove);
		mntmRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				final UUID id = (UUID) TablePanel.this.tableModel.getValueAt(TablePanel.this.table.getSelectedRow(), 0);
				controller.removeExistingItem((Frame) TablePanel.this.getParent(), id);
			}
		});
		
		for (final Entry<UUID, ? extends TableRecord> entry : controller.getRecordCache().getRecords().entrySet())
		{
			this.addToTable(entry.getKey(), entry.getValue());
		}
		
		controller.getRecordCache().addObserver(this);
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
	
	private void addToTable(final UUID id, final TableRecord record)
	{
		final List<Object> data = new ArrayList<>();
		data.add(0, id);
		data.addAll(record.toTableColumnValues());
		this.tableModel.addRow(data.toArray());
	}
	
	private void replaceInTable(final UUID id, final TableRecord record)
	{
		for (int i = 0; i < this.tableModel.getRowCount(); i++)
		{
			if (this.tableModel.getValueAt(i, 0).equals(id))
			{
				if (Objects.isNull(record))
				{
					this.tableModel.removeRow(i);
				}
				else
				{
					final List<Object> columns = record.toTableColumnValues();
					for (int j = 0; j < columns.size(); j++)
					{
						this.tableModel.setValueAt(columns.get(j),i, j+1);
					}
				}
				return;
			}
		}
		this.addToTable(id, record);
	}
	
	@Override
	public void update(final Observable o, final Object arg)
	{
		if (o instanceof RecordCache && arg instanceof Collection)
		{
			for (final Object id : (Collection<?>) arg)
			{
				if (id instanceof UUID)
				{
					this.replaceInTable((UUID) id, ((RecordCache<TableRecord>) o).getRecord((UUID) id));
				}
			}
		}
	}
}
