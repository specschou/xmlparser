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
		set.add("��");
		set.add("����");
		set.add("ʲô");
		set.add("����");
		set.add("һ");
		set.add("��");
//		for (String s : set) {
//			System.out.println(s);
//		}

		Set<Vod> vodSet = new TreeSet<Vod>();
		Vod vod = new Vod();
		vod.setName("����");
		vod.setSequence("947");
		vodSet.add(vod);
		vod = new Vod();
		vod.setName("����");
		vod.setSequence("947");
		vodSet.add(vod);
		vod = new Vod();
		vod.setName("һ����");
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
