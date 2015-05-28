package xmlparser;

import java.util.Set;
import java.util.TreeSet;

public class TestSet {
	public static void main(String[] args) {
		Set<String> set = new TreeSet<String>();
		set.add("skdfj");
		set.add("b");
		set.add("b");
		set.add("a");
		set.add("c");
		set.add("爱");
		set.add("不好");
		set.add("什么");
		set.add("不会");
		set.add("一");
		set.add("我");
//		for (String s : set) {
//			System.out.println(s);
//		}

		Set<Vod> vodSet = new TreeSet<Vod>();
		Vod vod = new Vod();
		vod.setName("不好");
		vod.setSequence("947");
		vodSet.add(vod);
		vod = new Vod();
		vod.setName("不好");
		vod.setSequence("947");
		vodSet.add(vod);
		vod = new Vod();
		vod.setName("一不好");
		vod.setSequence("250");
		vodSet.add(vod);
		vod = new Vod();
		vod.setName("a");
		vod.setSequence("256");
		vodSet.add(vod);
		vod = new Vod();
		vod.setName("b");
		vod.setSequence("971");
		vodSet.add(vod);
		for (Vod vod1 : vodSet) {
			System.out.println(vod1.getName());
		}
	}

}
