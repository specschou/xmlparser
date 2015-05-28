package xmlparser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestSort {
	public static void main(String[] args) {
		test();
//		System.out.println(getNumber("12kk"));
//		System.out.println(getNumber("3kk"));
	}

	public static void test() {
		List<Vod> list = new ArrayList<Vod>();
		Vod v = new Vod();
		v.setSequence("1");
		v.setName("第1集");
		list.add(v);
		v = new Vod();
		v.setSequence("1");
		v.setName("第2集");
		list.add(v);
		v = new Vod();
		v.setSequence("1");
		v.setName("第3集");
		list.add(v);
		v = new Vod();
		v.setSequence("1");
		v.setName("第11集");
		list.add(v);
		v = new Vod();
		v.setSequence("1");
		v.setName("第22集");
		list.add(v);
		Collections.sort(list);
		for (Vod vod : list) {
			System.out.println(vod.getName());
		}
	}


	public static int getNumber(String str) {
		int num = Integer.MAX_VALUE;
		int bits = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
				bits++;
			} else {
				break;
			}
		}
		if (bits > 0) {
			num = Integer.parseInt(str.substring(0, bits));
		}

		return num;
	}
}
