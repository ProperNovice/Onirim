package utils;

public enum Combination {

	INSTANCE;

	private ArrayList<ArrayList<Integer>> list = new ArrayList<>();
	private int total, permutations;

	private Combination() {

	}

	public void create(int total, int permutations) {

		this.list.clear();

		this.total = total;
		this.permutations = permutations;

		for (int counter = 1; counter <= (this.total - this.permutations + 1); counter++) {

			this.list.addLast(new ArrayList<>());
			this.list.getLast().addLast(counter);

		}

		addLeaves();

		Logger.INSTANCE.log("permutations " + this.permutations + " of " + this.total + " created");
		Logger.INSTANCE.log("elements " + this.list.size());
		Logger.INSTANCE.newLine();

	}

	private void addLeaves() {

		ArrayList<Integer> listToProcess = this.list.getFirst();

		if (listToProcess.size() == this.permutations)
			return;

		this.list.remove(listToProcess);
		int integerLast = listToProcess.getLast();

		for (int counter = integerLast + 1; counter <= this.total; counter++) {

			ArrayList<Integer> listToProcessClone = listToProcess.clone();

			listToProcessClone.addLast(counter);

			if (!listIsValid(listToProcessClone))
				break;

			this.list.addLast(listToProcessClone);

		}

		addLeaves();

	}

	private boolean listIsValid(ArrayList<Integer> list) {

		int integerLast = list.getLast();
		int permutationsLeftToAdd = this.permutations - list.size();
		int numbersLeftToAdd = this.total - integerLast;

		return numbersLeftToAdd >= permutationsLeftToAdd;

	}

	public void print() {

		Logger.INSTANCE.log("permutations " + this.permutations + " of " + this.total);

		for (ArrayList<Integer> listTemp : this.list) {

			for (Integer integer : listTemp)
				System.out.print(integer + " ");

			System.out.println();

		}

		Logger.INSTANCE.newLine();

	}

	public ArrayList<ArrayList<Integer>> get() {
		return this.list;
	}

	public void calculate(int total, int permutations) {

		Logger.INSTANCE.log("permutations " + permutations + " of " + total + " calculated");

		ArrayList<Numeric> up = new ArrayList<>();
		ArrayList<Numeric> down = new ArrayList<>();

		// create numerics

		for (int counter = total; counter > 1; counter--)
			up.addLast(new Numeric(counter));

		for (int counter = permutations; counter > 1; counter--)
			down.addLast(new Numeric(counter));

		for (int counter = total - permutations; counter > 1; counter--)
			down.addLast(new Numeric(counter));

		// simplify

		removeSameNumbers(up, down);
		ArrayList<Numeric> primitives = getPrimitives(total);
		simplify(up, down, primitives);

		// calculate result

		int result = 1;

		for (Numeric numeric : up)
			result *= numeric.get();

		Logger.INSTANCE.log("elements " + result);
		Logger.INSTANCE.newLine();

	}

	private void simplify(ArrayList<Numeric> up, ArrayList<Numeric> down,
			ArrayList<Numeric> primitives) {

		if (down.isEmpty())
			return;

		for (Numeric numericUp : up.clone()) {

			for (Numeric numericDown : down.clone()) {

				for (Numeric primitive : primitives) {

					if (numericUp.get() % primitive.get() > 0)
						continue;

					if (numericDown.get() % primitive.get() > 0)
						continue;

					numericUp.set(numericUp.get() / primitive.get());
					numericDown.set(numericDown.get() / primitive.get());

					if (numericUp.get() == 1)
						up.remove(numericUp);

					if (numericDown.get() == 1)
						down.remove(numericDown);

					simplify(up, down, primitives);
					return;

				}

			}

		}

		primitives.removeFirst();
		simplify(up, down, primitives);

	}

	private void removeSameNumbers(ArrayList<Numeric> up, ArrayList<Numeric> down) {

		for (Numeric numericUp : up.clone()) {

			for (Numeric numericDown : down.clone()) {

				if (numericUp.get() != numericDown.get())
					continue;

				up.remove(numericUp);
				down.remove(numericDown);
				removeSameNumbers(up, down);
				return;

			}

		}

	}

	private ArrayList<Numeric> getPrimitives(int number) {

		ArrayList<Numeric> primitives = new ArrayList<>();

		for (int divisible = 2; divisible <= number; divisible++) {

			boolean primitive = true;

			for (int divisor = 2; divisor < divisible; divisor++) {

				if (divisible % divisor > 0)
					continue;

				primitive = false;
				break;

			}

			if (primitive)
				primitives.addLast(new Numeric(divisible));

		}

		return primitives;

	}

}
