package xmlparser;

public class Vod implements Comparable<Vod> {
	private String id;
	private String name;
	private String sequence;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vod other = (Vod) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int compareTo(Vod vod) {
		// TODO Auto-generated method stub
		Integer a = 0;
		Integer b = 0;
		try {
			a = Integer.parseInt(sequence);
			b = Integer.parseInt(vod.getSequence());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (a.compareTo(b) != 0) {
			return a.compareTo(b);
		} else {
			// System.out.println(name + "," + vod.getName());
			for (int i = 0; i < name.length(); i++) {
				if (i >= vod.getName().length()) {
					break;
				}
				char ch1 = name.charAt(i);
				char ch2 = vod.getName().charAt(i);
				// System.out.println(ch1 + "," + ch2);
				if ((ch1 >= '0' && ch1 <= '9') && (ch2 >= '0' && ch2 <= '9')) {
					int i1 = getNumber(name.substring(i));
					int i2 = getNumber(vod.getName().substring(i));
					// System.out.println(i1 + "," + i2);
					if (i1 == i2) {
						continue;
					} else {
						return i1 - i2;
					}
				} else {
					if (ch1 != ch2) {
						break;
					}
				}
			}
			return name.compareTo(vod.getName());
		}
	}

	private int getNumber(String str) {
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