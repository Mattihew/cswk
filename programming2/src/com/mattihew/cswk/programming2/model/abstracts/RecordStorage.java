package com.mattihew.cswk.programming2.model.abstracts;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.UUID;

public abstract class RecordStorage<R>
{
	private final String filePath;
	public RecordStorage(final String filePath)
	{
		this.filePath = filePath;
	}
	
	public void writeRecords(final Map<UUID, R> records)
	{
		try (final BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath, false)))
		{
			for (final Entry<UUID, R> record : records.entrySet())
			{
				writer.append(record.getKey().toString());
				writer.append(',');
				this.writeRecord(writer, record.getValue());
				writer.newLine();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	protected abstract void writeRecord(final Appendable output, final R record) throws IOException;
	
	public Map<UUID, R> readRecords()
	{
		try (final BufferedReader reader = new BufferedReader(new FileReader(this.filePath)))
		{
			final Map<UUID, R> records = new LinkedHashMap<>();
			for (String readLine = reader.readLine(); Objects.nonNull(readLine); readLine = reader.readLine())
			{
				final String[] entry = readLine.split(",", 2);
				records.put(UUID.fromString(entry[0]), this.readRecord(entry[1]));
			}
			return records;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return Collections.emptyMap();
	}
	
	protected abstract R readRecord(final String readLine);
}
