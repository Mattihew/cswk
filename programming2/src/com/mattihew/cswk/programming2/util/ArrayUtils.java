package com.mattihew.cswk.programming2.util;

public class ArrayUtils
{
	/**
	 * Private Constructor as this utility class shouldn't be instantiated.
	 */
	private ArrayUtils(){}
	
	/**
	 * gets a particular element out of an array or <tt>null</tt> if index is outside of array.
	 * 
	 * @param <T> any array type.
	 * @param array the array to read from.
	 * @param index the index of the array to look at.
	 * @return the array element or <tt>null</tt>
	 */
	public static <T> T getOrDefault(final T[] array, final int index)
	{
		return ArrayUtils.getOrDefault(array, index, null);
	}
	
	public static <T> T getOrDefault(final T[] array, final int index, final T defaultValue)
	{
		if (index >= 0 && array != null && array.length > index)
		{
			return array[index];
		}
		return defaultValue;
	}
	
	public static String getOrEmpty(final String[] array, final int index)
	{
		return ArrayUtils.getOrDefault(array, index, "");
	}
}
