package com.mattihew.cswk.programming2.util;

public class ArrayUtils
{
	private ArrayUtils(){}
	
	public static <T> T getOrDefault(final T[] array, final int index)
	{
		return ArrayUtils.getOrDefault(array, index, null);
	}
	
	public static <T> T getOrDefault(final T[] array, final int index, final T defaultValue)
	{
		return array.length > index ? array[index] : defaultValue;
	}
}
