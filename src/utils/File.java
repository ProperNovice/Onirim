package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public enum File {

	INSTANCE;

	private java.io.File file = null;

	private File() {

	}

	public void writeToNewFile(ArrayList<String> list, String fileName) {

		createNewFile(fileName, true);

		ArrayList<String> listClone = list.clone();

		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.file))) {

			while (!listClone.isEmpty()) {

				bufferedWriter.write(listClone.removeFirst());
				bufferedWriter.newLine();

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void writeToNewFile(String string, String fileName) {

		ArrayList<String> list = new ArrayList<String>();
		list.addLast(string);
		writeToNewFile(list, fileName);

	}

	public void addToExistingFile(ArrayList<String> list, String fileName) {

		ArrayList<String> listFile = readFromFile(fileName);

		for (String string : list)
			listFile.addLast(string);

		writeToNewFile(listFile, fileName);

	}

	public void addToExistingFile(String string, String fileName) {

		ArrayList<String> list = new ArrayList<String>();
		list.addLast(string);
		addToExistingFile(list, fileName);

	}

	public ArrayList<String> readFromFile(String fileName) {

		createNewFile(fileName, false);

		this.file = new java.io.File(fileName + ".txt");

		ArrayList<String> list = new ArrayList<>();

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(this.file))) {

			String line = null;

			while ((line = bufferedReader.readLine()) != null)
				list.addLast(line);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;

	}

	public void printFile(String fileName) {

		Logger.INSTANCE.log("printing file - " + fileName + ".txt");

		ArrayList<String> list = readFromFile(fileName);

		for (String string : list)
			Logger.INSTANCE.log(string);

		Logger.INSTANCE.newLine();

	}

	private void createNewFile(String fileName, boolean deleteIfExist) {

		this.file = new java.io.File(fileName + ".txt");

		if (this.file.exists() && deleteIfExist)
			this.file.delete();

		try {
			this.file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
