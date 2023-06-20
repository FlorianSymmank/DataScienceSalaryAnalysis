package dataPreperation;

public class DataScienceCalculationFiltering{

	/**
	 * average of salaries
	 * 
	 * @param list a list with Salaries
	 * @return average of all salaries in the list
	 */
	public double AverageSalaryInUSD(SalariesList list) {
		double average = 0.0;
		for (int i = 0; i < list.getSalaryEntries(); i++) {
			average += list.getSalarayAt(i).getSalaryCurrencyInUSD(); // mistake																// currency
		}
		average /= list.getSalaryEntries();
		return average;
	}

/**
 * filters the given list 
 * @param list 
 * @param item column to be filtered
 * @param filter actual value
 * @return
 */
	public SalariesList filterBasedOnOneItem(SalariesList list, String item,
			String filter) {
		// SalariesList filtered = new SalariesList();
		switch (item) {
		case "workYear":
			int year = Integer.parseInt(filter);
			return filterAfterYear(list, year);
		case "experienceLevel":
			return filterExperienceLevel(list, filter);
		case "employmentType":
			return filterEmploymentType(list, filter);
		case "jobTitle":
			return filterJobTitle(list, filter);
		case "salary":
			int salary = Integer.parseInt(filter);
			return filterSalaray(list, salary);
		case "salaryCurrency":
			return filterSalaryCurrency(list, filter);
		case "salaryCurrencyInUSD":
			double currencyUSD = Double.parseDouble(filter);
			return filterSalaryCurrencyInUSD(list, currencyUSD);
		case "employeeResidence":
			return filterEmployeeResidence(list, filter);
		case "remotioRatio":
			int remoteRatio = Integer.parseInt(filter);
			return filterRemoteRatio(list, remoteRatio);
		case "companyLocation":
			return filterCompanyLocation(list, filter);
		case "companySize":
			char companySize = filter.charAt(0);
			return filterCompanySize(list, companySize);
		default:
			System.err.println("enteredItem does not exist in this DataSet");
			break;
		}

		return null;
	}

	public SalariesList filterAfterYear(SalariesList list, int year) {
		SalariesList newList = new SalariesList();
		for (int i = 0; i < list.getSalaryEntries(); i++) {
			if (list.getSalarayAt(i).getWorkYear() == year) {
				newList.addSalary(list.getSalarayAt(i));
			}
		}
		return newList;
	}

	private SalariesList filterExperienceLevel(SalariesList list,
			String filter) {
		SalariesList filtered = new SalariesList();
		for (int i = 0; i < list.getSalaryEntries(); i++) {
			if (list.getSalarayAt(i).getExperienceLevel().equals(filter)) {
				filtered.addSalary(list.getSalarayAt(i));
			}
		}
		return filtered;
	}

	private SalariesList filterEmploymentType(SalariesList list,
			String filter) {
		SalariesList filtered = new SalariesList();
		for (int i = 0; i < list.getSalaryEntries(); i++) {
			if (list.getSalarayAt(i).getEmploymentType().equals(filter)) {
				filtered.addSalary(list.getSalarayAt(i));
			}
		}
		return filtered;
	}

	private SalariesList filterJobTitle(SalariesList list, String filter) {
		SalariesList filtered = new SalariesList();
		for (int i = 0; i < list.getSalaryEntries(); i++) {
			if (list.getSalarayAt(i).getJobTitle().equals(filter)) {
				filtered.addSalary(list.getSalarayAt(i));
			}
		}
		return filtered;
	}

	private SalariesList filterSalaray(SalariesList list, double filter) {
		SalariesList filtered = new SalariesList();
		for (int i = 0; i < list.getSalaryEntries(); i++) {
			if (list.getSalarayAt(i).getSalary() == filter) {
				filtered.addSalary(list.getSalarayAt(i));
			}
		}
		return filtered;
	}

	private SalariesList filterSalaryCurrency(SalariesList list,
			String filter) {
		SalariesList filtered = new SalariesList();
		for (int i = 0; i < list.getSalaryEntries(); i++) {
			if (list.getSalarayAt(i).getSalaryCurrency().equals(filter)) {
				filtered.addSalary(list.getSalarayAt(i));
			}
		}
		return filtered;
	}

	private SalariesList filterSalaryCurrencyInUSD(SalariesList list,
			double filter) {
		SalariesList filtered = new SalariesList();
		for (int i = 0; i < list.getSalaryEntries(); i++) {
			if (list.getSalarayAt(i).getSalaryCurrencyInUSD() == filter) {
				filtered.addSalary(list.getSalarayAt(i));
			}
		}
		return filtered;
	}

	private SalariesList filterEmployeeResidence(SalariesList list,
			String filter) {
		SalariesList filtered = new SalariesList();
		for (int i = 0; i < list.getSalaryEntries(); i++) {
			if (list.getSalarayAt(i).getEmplyeeResidence().equals(filter)) {
				filtered.addSalary(list.getSalarayAt(i));
			}
		}
		return filtered;
	}

	private SalariesList filterRemoteRatio(SalariesList list, int filter) {
		SalariesList filtered = new SalariesList();
		for (int i = 0; i < list.getSalaryEntries(); i++) {
			if (list.getSalarayAt(i).getRemoteRatio() == filter) {
				filtered.addSalary(list.getSalarayAt(i));
			}
		}
		return filtered;
	}

	private SalariesList filterCompanyLocation(SalariesList list,
			String filter) {
		SalariesList filtered = new SalariesList();
		for (int i = 0; i < list.getSalaryEntries(); i++) {
			if (list.getSalarayAt(i).getCompanyLocation().equals(filter)) {
				filtered.addSalary(list.getSalarayAt(i));
			}
		}
		return filtered;
	}

	private SalariesList filterCompanySize(SalariesList list, char filter) {
		SalariesList filtered = new SalariesList();
		for (int i = 0; i < list.getSalaryEntries(); i++) {
			if (list.getSalarayAt(i).getCompanySize() == filter) {
				filtered.addSalary(list.getSalarayAt(i));
			}
		}
		return filtered;
	}

}
