package id_318247822;

import java.util.Vector;

public class Functions {

	public static int searchByPrimariz(Vector<Candidate> arr, int primariz) {
		int start = 0;
		int end = arr.size() - 1;
		while (start <= end) {
			int m = start + (end - start) / 2;
			if (arr.get(m) != null && arr.get(m).getPrimarizNum() == primariz)
				return m;
			if (arr.get(m) != null && arr.get(m).getPrimarizNum() < primariz)
				start = m + 1;
			else
				end = m - 1;
		}
		return -1;

	}

}
