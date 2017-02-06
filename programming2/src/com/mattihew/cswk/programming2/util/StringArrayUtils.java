package com.mattihew.cswk.programming2.util;

public class StringArrayUtils
{
	private StringArrayUtils(){}
	
	public static String getIndexOrDefault(final String[] array, final int index)
	{
		return StringArrayUtils.getIndexOrDefault(array, index, "");
	}
	
	public static String getIndexOrDefault(final String[] array, final int index, final String defaultString)
	{
		return array.length > index ? array[0] : defaultString;
	}
}
