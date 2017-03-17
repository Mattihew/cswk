package com.mattihew.cswk.programming2.controller;

import java.io.IOException;
import java.util.UUID;

import com.mattihew.cswk.programming2.controller.undo.UndoController;
import com.mattihew.cswk.programming2.model.ExternalProvider;
import com.mattihew.cswk.programming2.model.Student;
import com.mattihew.cswk.programming2.model.abstracts.RecordStorage;
import com.mattihew.cswk.programming2.model.tableModel.RecordCache;
import com.mattihew.cswk.programming2.model.tableModel.RecordCacheTableModel;
import com.mattihew.cswk.programming2.util.ArrayUtils;

/**
 * Controller for External Providers
 * 
 * @author Matt Rayner
 */
public class ExternalProviderController extends TablePanelUIController<ExternalProvider>
{
	private final RecordCache<ExternalProvider> providerCache = new RecordCache<>();
	
	private final RecordStorage<ExternalProvider> providerStorage = new ExternalProviderStorage("./data/externalProviders.csv");
	
	/**
	 * Class Constructor
	 *
	 * @param undoController the undo controller to use for doing actions.
	 */
	public ExternalProviderController(final UndoController undoController)
	{
		super(undoController);
		this.providerCache.putRecords(this.providerStorage.readRecords());
	}

	@Override
	public RecordCache<ExternalProvider> getRecordCache()
	{
		return this.providerCache;
	}

	@Override
	public void createRecord(final Object[] elementValues, final UUID id)
	{
		this.createRecord(ExternalProvider.fromValues(elementValues), id);
	}

	@Override
	public void editRecord(final UUID id, final Object[] elementValues)
	{
		this.editRecord(id, ExternalProvider.fromValues(elementValues));
	}

	@Override
	public String getRecordName()
	{
		return "External Provider";
	}
	
	@Override
	public void dispose()
	{
		this.providerStorage.writeRecords(this.providerCache.getRecords());
	}
	
	@Override
	public RecordCacheTableModel getTableModel()
	{
		return new RecordCacheTableModel(this.providerCache, new String[]{"name", "Transport", "venue"}, new Class<?>[]{String.class, String.class, String.class});
	}

	/**
	 * Permanent Storage class.
	 * 
	 * @author Matt Rayner
	 */
	private class ExternalProviderStorage extends RecordStorage<ExternalProvider>
	{
		/**
		 * Class Constructor.
		 *
		 * @param filePath the file path of the csv file.
		 */
		public ExternalProviderStorage(final String filePath)
		{
			super(filePath);
		}

		@Override
		protected void writeRecord(final Appendable output, final ExternalProvider record) throws IOException
		{
			output.append(record.toString());
			output.append(',');
			output.append(record.getTransport());
			output.append(',');
			output.append(record.getVenue());
		}

		@Override
		protected ExternalProvider readRecord(final String readLine)
		{
			final String[] lineParts = readLine.split(",");
			final String name = ArrayUtils.getOrEmpty(lineParts, 0);
			final String transport = ArrayUtils.getOrEmpty(lineParts, 1);
			final String venue = ArrayUtils.getOrEmpty(lineParts, 2);
			return new ExternalProvider(name, transport, venue);
		}
	}
}
