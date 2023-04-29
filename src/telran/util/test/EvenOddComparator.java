package telran.util.test;

import java.util.Comparator;

public class EvenOddComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {

		//int remanderO1 = o1 < 0 ? (-o1) % 2 : o1 % 2;
		int remanderO1 = Math.abs(o1 % 2);
		//int remanderO2 = o2 < 0 ? (-o2) % 2 : o2 % 2;
		int remanderO2 = Math.abs(o2 % 2);

		int res = Integer.compare(remanderO1, remanderO2);
		if (res == 0) {
			res = (remanderO1 == 0)? o1.compareTo(o2): o2.compareTo(o1);			
		}
		return res;
	}

}
