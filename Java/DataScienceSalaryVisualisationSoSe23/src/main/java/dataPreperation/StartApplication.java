package dataPreperation;

public class StartApplication{

	public static void main(String[] args) {

		 createSampleCharts();
		 createUsaCharts();
		// createTestCharts();
	}

	private static void createSampleCharts() {

		SalariesList list = new SalariesList("./merged_file-Stichprobe.csv");
		System.out.println("Sample Entries: " + list.getSalaryEntries());

		DataScienceCalculationFiltering f = new DataScienceCalculationFiltering();
		list = f.filterBasedOnOneItem(list, "employmentType", "FT"); // convention:
																		// only
																		// fulltime
																		// jobs

		BarChartExperienceSalaries bces = new BarChartExperienceSalaries();
		bces.createBarChart(list,
				"avg Salaries based on Experience",
				"de.indeed.com,stepstone.de");
		PieChartExperienceLevels pcel = new PieChartExperienceLevels();
		pcel.createPieChart(list, "Percentage of Experience in the sample",
				"de.indeed.com,stepstone.de");
		// pcel.createPieChart(original);
		PieChartJobTitle pcjt = new PieChartJobTitle();
//		pcjt.createPieChar(list, false, 100, "Jobdescriptions in the sample","de.indeed.com,stepstone.de");
		pcjt.createPieChar(list, true, 2,
				"Jobdescriptions in the sample with >2 Entries",
				"de.indeed.com,stepstone.de");
		PieChartRemoteRatio pcrr = new PieChartRemoteRatio();
		// pcrr.createPieChart(list, 2020);
		// pcrr.createPieChart(list, 2021);
		// pcrr.createPieChart(list, 2022);
		pcrr.createPieChart(list, 2023, "Remote Ratio in the sample",
				"de.indeed.com,stepstone.de");
		BarChartRiseOfSalaries bcros = new BarChartRiseOfSalaries();
		// bcros.createBarChart(list);
	}

	private static void createUsaCharts() {

		SalariesList list = new SalariesList(
				"./salaries.csv");
		// convention: everyDataset is filtered on fulltime
		// only, us only, in USD

		DataScienceCalculationFiltering f = new DataScienceCalculationFiltering();
		list = f.filterBasedOnOneItem(list, "companyLocation", "US");
		list = f.filterBasedOnOneItem(list, "employmentType", "FT");

		BarChartExperienceSalaries bces = new BarChartExperienceSalaries();
		bces.createBarChart(list,
				"Salaries in USD based on Experience Level in the USA",
				"dataset");
		PieChartExperienceLevels pcel = new PieChartExperienceLevels();
		pcel.createPieChart(list, "avg. Salaries based on Experience",
				"ai-jobs.net");
		PieChartJobTitle pcjt = new PieChartJobTitle();
		pcjt.createPieChar(list, true, 100, "Given Jobtitles in the Dataset",
				" ai-jobs.net\"");
		pcjt.createPieChar(list, false, 100,
				"Given Jobtitles in the Dataset without threshold",
				" ai-jobs.net\"");
		PieChartRemoteRatio pcrr = new PieChartRemoteRatio();
		pcrr.createPieChart(list, 2020, "Remote Ratio of Entries",
				"ai-jobs.net");
		pcrr.createPieChart(list, 2021, "Remote Ratio of Entries",
				"ai-jobs.net");
		pcrr.createPieChart(list, 2022, "Remote Ratio of Entries",
				"ai-jobs.net");
		pcrr.createPieChart(list, 2023, "Remote Ratio of Entries",
				"ai-jobs.net");
		BarChartRiseOfSalaries bcros = new BarChartRiseOfSalaries();
		bcros.createBarChart(list);
	}

	private static void createTestCharts() {

		SalariesList list = new SalariesList(
				"./test-Data.csv");
		// convention: everyDataset is filtered on fulltime
		// only, us only, in USD

		DataScienceCalculationFiltering f = new DataScienceCalculationFiltering();
		list = f.filterBasedOnOneItem(list, "companyLocation", "US");
		list = f.filterBasedOnOneItem(list, "employmentType", "FT");

		BarChartExperienceSalaries bces = new BarChartExperienceSalaries();
		bces.createBarChart(list,
				"Salaries in USD based on Experience Level in the USA",
				"dataset");
		PieChartExperienceLevels pcel = new PieChartExperienceLevels();
		pcel.createPieChart(list, "avg. Salaries based on Experience",
				"ai-jobs.net");
		PieChartJobTitle pcjt = new PieChartJobTitle();
		// pcjt.createPieChar(list, true, 100, "Given Jobtitles in
		// the Dataset"," ai-jobs.net\"");
		// pcjt.createPieChar(list, false, 100,"Given Jobtitles in
		// the Dataset without threshold"," ai-jobs.net\"");
		PieChartRemoteRatio pcrr = new PieChartRemoteRatio();
		// pcrr.createPieChart(list, 2020, "Remote Ratio of
		// Entries","ai-jobs.net");
		// pcrr.createPieChart(list, 2021, "Remote Ratio of
		// Entries","ai-jobs.net");
		// pcrr.createPieChart(list, 2022, "Remote Ratio of
		// Entries","ai-jobs.net");
		// pcrr.createPieChart(list, 2023, "Remote Ratio of
		// Entries","ai-jobs.net");
		BarChartRiseOfSalaries bcros = new BarChartRiseOfSalaries();
		bcros.createBarChart(list);
	}

}
