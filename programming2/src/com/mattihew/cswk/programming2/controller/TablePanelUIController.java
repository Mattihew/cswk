package com.mattihew.cswk.programming2.controller;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Panel;
import java.util.UUID;

import com.mattihew.cswk.programming2.controller.interfaces.UIController;
import com.mattihew.cswk.programming2.controller.undo.UndoController;
import com.mattihew.cswk.programming2.model.interfaces.TableRecord;
import com.mattihew.cswk.programming2.model.tableModel.RecordCacheTableModel;
import com.mattihew.cswk.programming2.view.EditDialog;
import com.mattihew.cswk.programming2.view.TablePanel;

/**
 * 
 * 
 * @author Matt Rayner
 * @param <E> the type of the Record
 */
public abstract class TablePanelUIController<E extends TableRecord> extends RecordController<E> implements UIController<E>
{
	/**
	 * Class Constructor.
	 *
	 * @param undoController the undo controller to use for actions this controller performs.
	 */
	public TablePanelUIController(final UndoController undoController)
	{
		super(undoController);
	}

	@Override
	public Panel getUIPanel(final Frame owner)
	{
		return new TablePanel(owner, this);
	}
	
	@Override
	public void insertNewItem(final Frame owner)
	{
		final Dialog newStudent = new EditDialog<>(owner, this);
		newStudent.setVisible(true);
	}
	
	@Override
	public void editExistingItem(final Frame owner, final UUID id)
	{
		final Dialog newDialog = new EditDialog<>(owner, this, this.getRecordCache().getRecord(id), id);
		newDialog.setVisible(true);
	}

	@Override
	public void removeExistingItem(final Frame owner, final UUID id)
	{
		this.removeRecord(id);
	}
	
	/**
	 * Gets the table model that reprsents this controllers data.
	 * 
	 * @return the RecordCacheTableModel
	 */
	public abstract RecordCacheTableModel getTableModel();

	/**
	 * Gets the options for a combo box in the edit dialog.
	 * 
	 * @param attributeIndex the column index to get values for.
	 * @return array of values to display in the combo box or <code>null</code> to display a text box allowing any value.
	 */
	public String[] comboOptions(final int attributeIndex)
	{
		return null;
	}
}
