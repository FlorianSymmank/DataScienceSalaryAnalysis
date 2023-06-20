package dataPreperation;

public class Salary{

	private int workYear; // 20-23
	private String experienceLevel; // en, mi, se, ex
	private String employmentType; // pt,ft,ct,fl
	private String jobTitle; 
	private double salary; // currency paid
	private String salaryCurrency; //
	private double salaryCurrencyInUSD;
	private String employeeResidence;
	private int remoteRatio; // 0,50,100
	private String companyLocation;
	private char companySize; // s,m,l

	public Salary(int workYear, String experienceLevel, String employmentType,
			String jobTitle, double salary, String salaryCurrency,
			double salaryCurrencyInUSD, String employeeResidence,
			int remoteRatio,
			String companyLocation, char companySize) {

		this.workYear = workYear;
		this.experienceLevel = experienceLevel;
		this.employmentType = employmentType;
		this.jobTitle = jobTitle;
		this.salary = salary;
		this.salaryCurrency = salaryCurrency;
		this.salaryCurrencyInUSD = salaryCurrencyInUSD;
		this.employeeResidence = employeeResidence;
		this.remoteRatio = remoteRatio;
		this.companyLocation = companyLocation;
		this.companySize = companySize;
	}

///////////////////////////////////////
//////////////Getter///////////////////
///////////////////////////////////////
	public int getWorkYear() {
		return workYear;
	}

	public String getExperienceLevel() {
		return experienceLevel;
	}

	public String getEmploymentType() {
		return employmentType;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public double getSalary() {
		return salary;
	}

	public String getSalaryCurrency() {
		return salaryCurrency;
	}

	public double getSalaryCurrencyInUSD() {
		return salaryCurrencyInUSD;
	}

	public String getEmplyeeResidence() {
		return employeeResidence;
	}

	public int getRemoteRatio() {
		return remoteRatio;
	}

	public String getCompanyLocation() {
		return companyLocation;
	}

	public char getCompanySize() {
		return companySize;
	}

	
	public String toString() {
		String s =
				"------Salary------\n" +
				"Work Year: " + workYear +"\n" +
				"experience Level: " + experienceLevel +"\n" +
				"employmentType: " + employmentType + "\n" +
				"JobTitle: " + jobTitle + "\n" +
				"Salary: " +salary+ "\n" +
				"Salary Currency: "+ salaryCurrency +"\n" +
				"Salary Currency in USD: "+salaryCurrencyInUSD +"\n" + 
				"employee Residence: " + employeeResidence+"\n" +
				"remote Ratio: " + remoteRatio+"%\n" +
				"company Location: " +companyLocation +"\n" +
				"company Size: " + companySize + "\n";
		return s;

	}

}
