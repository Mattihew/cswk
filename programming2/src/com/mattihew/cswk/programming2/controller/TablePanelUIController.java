package com.mattihew.cswk.programming2.controller;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Panel;
import java.util.UUID;

import com.mattihew.cswk.programming2.controller.interfaces.UIController;
import com.mattihew.cswk.programming2.model.interfaces.TableRecord;
import com.mattihew.cswk.programming2.view.EditDialog;
import com.mattihew.cswk.programming2.view.TablePanel;

public abstract class TablePanelUIController<E extends TableRecord> implements UIController<E>
{
	@Override
	public Panel getUIPanel(final Frame owner)
	{
		return new TablePanel(owner, this.getTableHeadings(), this);
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

	public String[] comboOptions(final int attributeIndex)
	{
		return null;
	}
	
}
