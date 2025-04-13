package joshie.harvest.knowledge.letter;

import java.util.Set;

import joshie.harvest.api.core.Letter;

public abstract class LetterData {
	public abstract Set<Letter> getLetters();

	public abstract boolean hasUnreadLetters();
}
