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

/**
 * A class for reading from and writing records to a csv file.
 * 
 * @author Matt Rayner
 * @param <R> the Record type.
 */
public abstract class RecordStorage<R>
{
	private final String filePath;
	
	/**
	 * Class Constructor.
	 *
	 * @param filePath the file path of the file to use.
	 */
	public RecordStorage(final String filePath)
	{
		this.filePath = filePath;
	}
	
	/**
	 * Writes a Map of Records to a file.
	 * 
	 * @param records records to write to the file.
	 */
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
	
	/**
	 * Writes an individual Record.
	 * 
	 * @param output the object to append each record field to.
	 * @param record the record to write.
	 * @throws IOException If an I/O error occurs
	 */
	protected abstract void writeRecord(final Appendable output, final R record) throws IOException;
	
	/**
	 * Reads the Records from the file
	 * 
	 * @return the records and id read from the file.
	 */
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
	
	/**
	 * Creates a Record from its line in the csv file. pattern is defined in {@link #writeRecord(Appendable, Object)}
	 * 
	 * @param readLine the line from the csv file.
	 * @return a new Record.
	 */
	protected abstract R readRecord(final String readLine);
}
