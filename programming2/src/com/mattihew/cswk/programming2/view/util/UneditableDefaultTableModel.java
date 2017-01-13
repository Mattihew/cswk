package com.mattihew.cswk.programming2.view.util;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class UneditableDefaultTableModel extends DefaultTableModel
{
	
	/**
	 * Class Constructor.
	 *
	 * @see {@link javax.swing.table.DefaultTableModel#DefaultTableModel()}
	 */
	public UneditableDefaultTableModel()
	{
		super();
	}

	/**
	 * Class Constructor.
	 *
	 * @see {@link javax.swing.table.DefaultTableModel#DefaultTableModel(int, int)}
	 */
	public UneditableDefaultTableModel(final int rowCount, final int columnCount)
	{
		super(rowCount, columnCount);
	}

	/**
	 * Class Constructor.
	 *
	 * @see {@link javax.swing.table.DefaultTableModel#DefaultTableModel(Object[], int)}
	 */
	public UneditableDefaultTableModel(final Object[] columnNames, final int rowCount)
	{
		super(columnNames, rowCount);
	}

	/**
	 * Class Constructor.
	 *
	 * @see {@link javax.swing.table.DefaultTableModel#DefaultTableModel(Object[][], Object[])}
	 */
	public UneditableDefaultTableModel(final Object[][] data, final Object[] columnNames)
	{
		super(data, columnNames);
	}

	/**
	 * Class Constructor.
	 *
	 * @see {@link javax.swing.table.DefaultTableModel#DefaultTableModel(Vector, int)}
	 */
	public UneditableDefaultTableModel(final Vector<?> columnNames, final int rowCount)
	{
		super(columnNames, rowCount);
	}

	/**
	 * Class Constructor.
	 *
	 * @see {@link javax.swing.table.DefaultTableModel#DefaultTableModel(Vector, Vector)}
	 */
	public UneditableDefaultTableModel(final Vector<?> data, final Vector<?> columnNames)
	{
		super(data, columnNames);
	}

	/**
	 * Returns false regardless of parameter values.
	 *
	 * @see javax.swing.table.DefaultTableModel#isCellEditable(int, int)
	 */
	@Override
	public boolean isCellEditable(final int row, final int column)
	{
		return false;
	}
}