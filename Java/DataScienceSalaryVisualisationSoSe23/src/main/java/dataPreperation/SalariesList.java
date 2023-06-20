package dataPreperation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * creates a list of salaries, hat to be the "salaries.csv"
 *
 */
public class SalariesList{

	private ArrayList<Salary> salaries;

	/**
	 * create an empty list.
	 */
	public SalariesList() {
		salaries = new ArrayList<Salary>();
	}

	/**
	 * 
	 * @param fileName from here the data is imported
	 */
	public SalariesList(String fileName) {
		salaries = new ArrayList<Salary>();

		try {
			BufferedReader read = new BufferedReader(new FileReader(fileName));
			read.readLine(); // first line which is filled with the title of the
								// data
			while (read.ready()) {
				salaries.add(addSalaryEntry(read.readLine()));
			}
			read.close();
		} catch (IOException e) {
			System.err.println("IO-Exception. file output corrupted");
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			System.err.println(
					"couldn´t add to List because the fileinput has a entity missmatch");
		}

	}

	/**
	 * should always much the columns at the csv-1
	 * 
	 * @return entries of the salaries
	 */
	public int getSalaryEntries() {
		return salaries.size();
	}

	/**
	 * 
	 * @param index
	 * @return the Salary from the index
	 */
	public Salary getSalarayAt(int index) {
		return salaries.get(index);
	}

	/**
	 * adds a salary to the list
	 * 
	 * @param s the salary
	 */
	public void addSalary(Salary s) {
		salaries.add(s);
	}

	/**
	 * Converts one line from csv into internal Salary Object
	 * 
	 * @param data one line from csv
	 * @return the salary Object
	 */
	private Salary addSalaryEntry(String data)
					throws IndexOutOfBoundsException {
		String[] tmp = data.split(",");

		int workYear = Integer.parseInt(tmp[0]); // 20-23
		String experienceLevel = tmp[1]; // en, mi, se, ex
		String employmentType = tmp[2]; // pt,ft,ct,fl
		String jobTitle = tmp[3];
		double salary = Double.parseDouble(tmp[4]); // currency paid
		String salaryCurrency = tmp[5]; //
		double salaryCurrencyInUSD = Double.parseDouble(tmp[6]);

		String employeeResidence = tmp[7];
		int remoteRatio = -1;
		try {
			remoteRatio = Integer.parseInt(tmp[8]); // 0,50,100
		} catch (NumberFormatException e) {
			remoteRatio = (int) Float.parseFloat(tmp[8]);// 0.0 , 50.0 , 100.0
		}
		
		String companyLocation = tmp[9];
		char companySize = 'U';
		try {
			
			 companySize = tmp[10].charAt(0); // s,m,l
		}catch(IndexOutOfBoundsException e) {
			companySize = 'U';
		}

		return new Salary(workYear, experienceLevel, employmentType, jobTitle,
				salary, salaryCurrency, salaryCurrencyInUSD, employeeResidence,
				remoteRatio, companyLocation, companySize);
	}

}
