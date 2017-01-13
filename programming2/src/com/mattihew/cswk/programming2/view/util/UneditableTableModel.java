package com.mattihew.cswk.programming2.view.util;

import javax.swing.table.DefaultTableModel;

public class UneditableTableModel extends DefaultTableModel
{
	/** serialVersionUID */
	private static final long serialVersionUID = -5942306991810906144L;
	
	@Override
	public boolean isCellEditable(int row, int column)
	{
		return false;
	}
}
