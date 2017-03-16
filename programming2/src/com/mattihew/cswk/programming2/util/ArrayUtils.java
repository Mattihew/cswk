package com.mattihew.cswk.programming2.util;

import java.util.Collection;

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
	
	/**
	 * gets a particular element out of an array or <tt>defaultValue</tt> if index is outside of array.
	 * 
	 * @param <T> any array type.
	 * @param array the array to read from.
	 * @param index the index of the array to look at.
	 * @param defaultValue the value to return if index is out of bounds or {@code null}.
	 * @return the array element or <tt>defaultValue</tt>
	 */
	public static <T> T getOrDefault(final T[] array, final int index, final T defaultValue)
	{
		if (index >= 0 && array != null && array.length > index)
		{
			return array[index];
		}
		return defaultValue;
	}
	
	/**
	 * gets a particular String out of an array or a blank String if index is outside of array.
	 * 
	 * @param array the array to read from.
	 * @param index the index of the array to look at.
	 * @return the array element or a blank String.
	 */
	public static String getOrEmpty(final String[] array, final int index)
	{
		return ArrayUtils.getOrDefault(array, index, "");
	}
	
	/**
	 * Gets a String array generated from the {@link Object#toString()} method of each element.
	 * 
	 * @param collection the collection of objects to use
	 * @return a String array of each objects toString method.
	 */
	public static String[] toStringArray (final Collection<?> collection)
	{
		final String[] result = new String[collection.size()];
		int i = 0;
		for (final Object object : collection)
		{
			result[i++] = object.toString();
		}
		return result;
	}
}
