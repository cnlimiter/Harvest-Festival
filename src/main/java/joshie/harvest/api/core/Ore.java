package joshie.harvest.api.core;

public class Ore {
	private final String string;
	private MatchType type;

	private Ore(String string) {
		this.string = string;
		this.type = MatchType.FULL;
	}

	public String getOre() {
		return string;
	}

	public MatchType getType() {
		return type;
	}

	public static Ore of(String string) {
		return new Ore(string);
	}

	public Ore setType(MatchType type) {
		this.type = type;
		return this;
	}
}