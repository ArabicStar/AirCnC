
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class Tag {
	public enum Tags {
		Air_Conditioner(1 << 0), Breakfast(1 << 1), Swimming_Pool(1 << 2), Parking(1 << 3), Extra(1 << 4);

		private int bitField;

		private Tags(int bitField) {
			this.bitField = bitField;
		}

		public int getBitField() {
			return bitField;
		}
	}

	private int tags = 0;
	private boolean hasExtra = false;
	private String extra = null;

	private static final BiMap<Integer, Tags> mapping;
	static {
		mapping = HashBiMap.create();
		for (Tags t : Tags.values())
			mapping.put(t.getBitField(), t);
	}

	public void addTag(Tags t) {
		tags = tags | t.getBitField();
		if (t == Tags.Extra)
			hasExtra = true;
	}

	public void setExtra(String extra) {
		if (hasExtra)
			this.extra = extra;
	}

	public void removeTag(Tags t) {
		tags = tags ^ t.getBitField();
		if (t == Tags.Extra) {
			hasExtra = false;
			extra = null;
		}
	}

	public void printTags() {
		int mask = 1;
		for (int i = 1; i <= 32; i++) {
			int key = mask & tags;
			if (key != 0) {
				System.out.println(mapping.get(key).name());
				if (key == Tags.Extra.getBitField())
					System.out.println(extra);
			}
			mask = mask << 1;
		}
	}

	public static void main(String[] args) {
		Tag t = new Tag();
		t.addTag(Tags.Air_Conditioner);
		t.addTag(Tags.Parking);
		t.addTag(Tags.Extra);
		t.setExtra("Big_Room");
		t.printTags();
		System.out.println();
		t.removeTag(Tags.Parking);
		t.removeTag(Tags.Extra);
		t.addTag(Tags.Swimming_Pool);
		t.printTags();
	}
}
