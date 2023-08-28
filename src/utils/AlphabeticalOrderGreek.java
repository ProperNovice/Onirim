package utils;

import java.util.ArrayList;

public enum AlphabeticalOrderGreek {

	INSTANCE;

	private ArrayList<String> alphabeticalOrder = new ArrayList<>();

	private AlphabeticalOrderGreek() {

		this.alphabeticalOrder.add(" ");
		this.alphabeticalOrder.add(".");
		this.alphabeticalOrder.add("&");
		this.alphabeticalOrder.add("α");
		this.alphabeticalOrder.add("β");
		this.alphabeticalOrder.add("γ");
		this.alphabeticalOrder.add("δ");
		this.alphabeticalOrder.add("ε");
		this.alphabeticalOrder.add("ζ");
		this.alphabeticalOrder.add("η");
		this.alphabeticalOrder.add("θ");
		this.alphabeticalOrder.add("ι");
		this.alphabeticalOrder.add("κ");
		this.alphabeticalOrder.add("λ");
		this.alphabeticalOrder.add("μ");
		this.alphabeticalOrder.add("ν");
		this.alphabeticalOrder.add("ξ");
		this.alphabeticalOrder.add("ο");
		this.alphabeticalOrder.add("π");
		this.alphabeticalOrder.add("ρ");
		this.alphabeticalOrder.add("σ");
		this.alphabeticalOrder.add("τ");
		this.alphabeticalOrder.add("υ");
		this.alphabeticalOrder.add("φ");
		this.alphabeticalOrder.add("χ");
		this.alphabeticalOrder.add("ψ");
		this.alphabeticalOrder.add("ω");

	}

	public String firstInAlphabeticalOrder(String first, String second) {

		String firstString = first.toLowerCase();
		String secondString = second.toLowerCase();

		firstString = replaceTones(firstString);
		secondString = replaceTones(secondString);

		return getFirstInAlphabeticalOrder(first, second);

	}

	private String replaceTones(String string) {

		string = string.replaceAll("ά", "α");
		string = string.replaceAll("έ", "ε");
		string = string.replaceAll("ή", "η");
		string = string.replaceAll("ί", "ι");
		string = string.replaceAll("ό", "ο");
		string = string.replaceAll("ύ", "υ");
		string = string.replaceAll("ώ", "ω");

		return string;
	}

	private String getFirstInAlphabeticalOrder(String first, String second) {

		String stringToReturn = null;

		for (int counter = 0; counter < Math.min(first.length(), second.length()); counter++) {

			if (this.alphabeticalOrder.indexOf(getStringAtIndex(first,
					counter)) < this.alphabeticalOrder.indexOf(getStringAtIndex(second, counter))) {
				stringToReturn = first;
				break;

			} else if (this.alphabeticalOrder.indexOf(getStringAtIndex(second,
					counter)) < this.alphabeticalOrder.indexOf(getStringAtIndex(first, counter))) {
				stringToReturn = second;
				break;
			}

		}

		if (stringToReturn != null)
			return stringToReturn;

		if (first.length() < second.length())
			return first;

		return second;

	}

	private String getStringAtIndex(String string, int index) {
		return string.substring(index, index + 1);
	}

}
