import java.util.ArrayList;
import java.util.List;

/**
 * This class gives an implementation of a triple merge sort
 * 
 * @author Nolan Knight
 *
 */
public class TripleMergeSort {
	/**
	 * The client entry point for the triple merge sort implementation
	 * 
	 * @param <T>  the generic type
	 * @param data the list to be sorted (in descending order)
	 */
	public static <T extends Comparable<T>> void myTripleMergeSort(List<T> data) {
		if (data == null)
			return;

		int listSize = data.size();
		ArrayList<T> aux = new ArrayList<T>(listSize);
		for (int i = 0; i < listSize; i++) {
			aux.add(null);
			aux.set(i, data.get(i));
		}

		for (int i = 0; i < aux.size(); i++)
			aux.set(i, data.get(i));

		myTripleMergeSortHelper(aux, 0, data.size(), data);

		for (int i = 0; i < aux.size(); i++)
			data.set(i, aux.get(i));
	}

	/**
	 * Recursively breaks up the list into groups of 3 then merges the sorted arrays
	 * 
	 * Closed Form Recurrence Solution: T(N) = Theta(N log N)
	 * 
	 * @param <T>  the generic type
	 * @param aux  the auxiliary list used to temporarily store the list being
	 *             sorted
	 * @param low  the index of the low point
	 * @param high the index of the high point
	 * @param data the original list to be sorted
	 */
	private static <T extends Comparable<T>> void myTripleMergeSortHelper(List<T> aux, int low, int high,
			List<T> data) {
		if (high - low < 2)
			return;

		int mid1 = low + ((high - low) / 3);
		int mid2 = low + (((high - low) / 3) << 1) + 1;

		myTripleMergeSortHelper(data, low, mid1, aux);
		myTripleMergeSortHelper(data, mid1, mid2, aux);
		myTripleMergeSortHelper(data, mid2, high, aux);

		myMerge(data, low, mid1, mid2, high, aux);
	}

	/**
	 * Merges the sorted ranges
	 * 
	 * @param <T>  the generic type
	 * @param data the original list to be sorted
	 * @param low  the index of the low point
	 * @param mid1 the index of the first mid point (or first 3rd)
	 * @param mid2 the index of the second mid point (or second 3rd)
	 * @param high the index of the high point
	 * @param aux  the auxiliary list used to temporarily store the list being
	 *             sorted
	 */
	private static <T extends Comparable<T>> void myMerge(List<T> data, int low, int mid1, int mid2, int high,
			List<T> aux) {
		int i = low, j = mid1, k = mid2, l = low;

		while ((i < mid1) && (j < mid2) && (k < high)) {
			if (data.get(i).compareTo(data.get(j)) > 0) {
				if (data.get(i).compareTo(data.get(k)) > 0)
					aux.set(l++, data.get(i++));

				else
					aux.set(l++, data.get(k++));
			} else {
				if (data.get(j).compareTo(data.get(k)) > 0)
					aux.set(l++, data.get(j++));
				else
					aux.set(l++, data.get(k++));
			}
		}

		while ((i < mid1) && (j < mid2)) {
			if (data.get(i).compareTo(data.get(j)) > 0)
				aux.set(l++, data.get(i++));
			else
				aux.set(l++, data.get(j++));
		}

		while ((j < mid2) && (k < high)) {
			if (data.get(j).compareTo(data.get(k)) > 0)
				aux.set(l++, data.get(j++));

			else
				aux.set(l++, data.get(k++));
		}

		while ((i < mid1) && (k < high)) {
			if (data.get(i).compareTo(data.get(k)) > 0)
				aux.set(l++, data.get(i++));
			else
				aux.set(l++, data.get(k++));
		}

		while (i < mid1)
			aux.set(l++, data.get(i++));

		while (j < mid2)
			aux.set(l++, data.get(j++));

		while (k < high)
			aux.set(l++, data.get(k++));
	}
}
