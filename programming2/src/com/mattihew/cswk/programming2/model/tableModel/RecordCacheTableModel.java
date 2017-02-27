package com.mattihew.cswk.programming2.model.tableModel;

import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

import javax.swing.table.AbstractTableModel;

import com.mattihew.cswk.programming2.model.interfaces.TableRecord;

public class RecordCacheTableModel extends AbstractTableModel implements Observer
{
	/** serialVersionUID. */
	private static final long serialVersionUID = 3705986368544657347L;
	private final RecordCache<? extends TableRecord> recordCache;
	private final List<String> columnNames;
	private final Class<?>[] classes;
	
	public RecordCacheTableModel(final RecordCache<? extends TableRecord> cache, final String[] columnNames, final Class<?>[] columnTypes)
	{
		this.recordCache = cache;
		this.columnNames = Arrays.asList(columnNames);
		this.classes = Arrays.copyOf(columnTypes, columnTypes.length);
		this.recordCache.addObserver(this);
	}
	
	@Override
	public int getRowCount()
	{
		return this.recordCache.size();
	}

	@Override
	public int getColumnCount()
	{
		return this.columnNames.size() + 1;
	}

	@Override
	public String getColumnName(final int column)
	{
		if (column == 0)
		{
			return "ID";
		}
		else if (column <= this.columnNames.size())
		{
			return this.columnNames.get(column - 1);
		}
		return super.getColumnName(column);
	}

	@Override
	public int findColumn(final String columnName)
	{
		if ("ID".equals(columnName))
		{
			return 0;
		}
		else if (this.columnNames.contains(columnName))
		{
			return this.columnNames.indexOf(columnName) + 1;
		}
		return super.findColumn(columnName);
	}



	@Override
	public Class<?> getColumnClass(final int columnIndex)
	{
		if (columnIndex == 0)
		{
			return UUID.class;
		}
		else if (columnIndex <= this.classes.length)
		{
			return this.classes[columnIndex - 1];
		}
		return super.getColumnClass(columnIndex);
	}



	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex)
	{
		int i = 0;
		for (Entry<UUID, ? extends TableRecord> entry : this.recordCache.entrySet())
		{
			if (i++ == rowIndex)
			{
				if (columnIndex == 0)
				{
					return entry.getKey();
				}
				else
				{
					return entry.getValue().getValueAt(columnIndex - 1);
				}
			}
		}
		return null;
	}

	@Override
	public void update(final Observable o, final Object arg)
	{
		if (o instanceof RecordCache)
		{
			this.fireTableDataChanged();
		}
	}
}