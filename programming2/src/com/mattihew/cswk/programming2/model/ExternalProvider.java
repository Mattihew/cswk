/**
 * 
 */
package com.mattihew.cswk.programming2.model;

import com.mattihew.cswk.programming2.model.interfaces.TableRecord;
import com.mattihew.cswk.programming2.model.interfaces.TripProvider;

/**
 * @author Matt Rayner
 */
public class ExternalProvider implements TripProvider, TableRecord
{
	private final String name;
	
	private final String transport;
	
	private final String venue;
	
	public ExternalProvider(final String name, final String transport, final String venue)
	{
		this.name = name;
		this.transport = transport;
		this.venue = venue;
	}
	
	@Override
	public String getTransport()
	{
		return this.transport;
	}

	@Override
	public String getVenue()
	{
		return this.venue;
	}

	@Override
	public Object getValueAt(int columnIndex)
	{
		switch (columnIndex)
		{
			case 0:
				return this.name;
			case 1:
				return this.transport;
			case 2:
				return this.venue;
			default:
				return "";
		}
	}
	
	public static ExternalProvider fromValues(final Object[] values)
	{
		return new ExternalProvider((String) values[0], (String) values[1], (String) values[2]);
	}

	@Override
	public int getColumnCount()
	{
		return 3;
	}
	
	@Override
	public String toString()
	{
		return this.name;
	}
}
