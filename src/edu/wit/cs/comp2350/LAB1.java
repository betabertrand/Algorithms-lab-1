package edu.wit.cs.comp2350;

/* Sorts integers from command line using various algorithms 
 * 
 * Wentworth Institute of Technology
 * COMP 2350
 * Programming Assignment 0
 * 
 */

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class LAB1 {

	public final static int MAX_INPUT = 524287;
	public final static int MIN_INPUT = 0;

	// TODO: document this method
	public static int[] countingSort(int[] a) {
		//TODO: implement this method
		int max = a[0];

		for (int i = 1; i < a.length; i++)
			if (a[i] > max)
				max = a[i];

		int[] count = new int[max > a.length ? max + 1 : a.length + 1];
		int[] sorted = new int[a.length];

		for (int element = 0; element < a.length; element++)
			count[a[element]]++;

		for (int i = 0, j = 0; i < count.length; i++)
		{
			while (count[i] != 0)
			{
				sorted[j] = i;
				count[i]--;
				j++;

			}
		}

		return sorted;
	}

	// TODO: document this method
	public static int[] radixSort(int[] a) {
		// TODO: implement this method

		int max = a[0];
		int num = 1;

		int[] arr2 = new int[10];

		for (int i = 1; i < a.length; i++)
		{
			if (a[i] > max)
			{
				max = a[i];
			}
		}
		// System.out.println("hello");
		// int d = String.valueOf(max).length();

		while (max / num > 0) {

			int[] arr = new int[10];

			for (int i = 0; i < a.length; i++)
				arr[((a[i] / num) % 10)]++;

			for (int i = 1; i < arr.length; i++)
				arr[i] += arr[i - 1];

			for (int i = a.length - 1; i >= 0; i--)
				arr2[--arr[(a[i] / num) % 10]] = a[i];

			for (int i = 0; i < a.length; i++)
				a[i] = arr2[i];

			num *= 10;
		}

		return a;
	}

	/********************************************
	 * 
	 * You shouldn't modify anything past here
	 * 
	********************************************/
	
	// example sorting algorithm
	public static int[] insertionSort(int[] a) {

		for (int i = 1; i < a.length; i++) {
			int tmp = a[i];
			int j;
			for (j = i-1; j >= 0 && tmp < a[j]; j--)
				a[j+1] = a[j];
			a[j+1] = tmp;
		}
		
		return a;
	}

	/* Implementation note: The sorting algorithm is a Dual-Pivot Quicksort by Vladimir Yaroslavskiy,
	 *  Jon Bentley, and Joshua Bloch. This algorithm offers O(n log(n)) performance on many data 
	 *  sets that cause other quicksorts to degrade to quadratic performance, and is typically 
	 *  faster than traditional (one-pivot) Quicksort implementations. */
	public static int[] systemSort(int[] a) {
		Arrays.sort(a);
		return a;
	}

	// read ints from a Scanner
	// returns an array of the ints read
	private static int[] getInts(Scanner s) {
		ArrayList<Integer> a = new ArrayList<Integer>();

		while (s.hasNextInt()) {
			int i = s.nextInt();
			if ((i <= MAX_INPUT) && (i >= MIN_INPUT))
				a.add(i);
		}

		return toIntArray(a);
	}

	// copies an ArrayList to an array
	private static int[] toIntArray(ArrayList<Integer> a) {
		int[] ret = new int[a.size()];
		for(int i = 0; i < ret.length; i++)
			ret[i] = a.get(i);
		return ret;
	}

	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.printf("Enter the sorting algorithm to use ([c]ounting, [r]adix, [i]nsertion, or [s]ystem): ");
		char algo = s.next().charAt(0);
		
		System.out.printf("Enter the integers that you would like sorted: ");
		int[] unsorted_values = getInts(s);
		int[] sorted_values = {};

		s.close();

		switch (algo) {
		case 'c':
			sorted_values = countingSort(unsorted_values);
			break;
		case 'r':
			sorted_values = radixSort(unsorted_values);
			break;
		case 'i':
			sorted_values = insertionSort(unsorted_values);
			break;
		case 's':
			sorted_values = systemSort(unsorted_values);
			break;
		default:
			System.out.println("Invalid sorting algorithm");
			System.exit(0);
			break;
		}
		
		System.out.println(Arrays.toString(sorted_values));
		
	}

}
