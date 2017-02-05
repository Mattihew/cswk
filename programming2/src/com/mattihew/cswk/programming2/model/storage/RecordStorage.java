package com.mattihew.cswk.programming2.model.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class RecordStorage<R>
{
	private final String filePath;
	public RecordStorage(final String filePath)
	{
		this.filePath = filePath;
	}
	
	public void writeRecords(final Collection<R> records)
	{
		try (final BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath, false)))
		{
			for (final R record : records)
			{
				this.writeRecord(writer, record);
				writer.newLine();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	protected abstract void writeRecord(final Appendable output, final R record) throws IOException;
	
	public Collection<R> readRecords()
	{
		try (final BufferedReader reader = new BufferedReader(new FileReader(this.filePath)))
		{
			final List<R> records = new ArrayList<>();
			for (String readLine = reader.readLine(); Objects.nonNull(readLine); readLine = reader.readLine())
			{
				records.add(this.readRecord(readLine));
			}
			return records;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	
	protected abstract R readRecord(final String readLine);
}
