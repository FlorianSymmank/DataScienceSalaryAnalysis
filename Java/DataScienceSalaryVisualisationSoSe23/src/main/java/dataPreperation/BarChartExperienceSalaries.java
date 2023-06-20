package dataPreperation;

import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChartExperienceSalaries extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	
	/**
	 * creates the bar chart with Average Salaries on income per
	 * year based on experience level
	 */
	public void createBarChart(SalariesList list, String title, String dataOrigin) {
		
		DataScienceCalculationFiltering f = new DataScienceCalculationFiltering();

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		// filter on experience Level
		SalariesList averageEN = f.filterBasedOnOneItem(
				list, "experienceLevel", "EN");
		SalariesList averageMI = f.filterBasedOnOneItem(
				list, "experienceLevel", "MI");
		SalariesList averageSE = f.filterBasedOnOneItem(
				list, "experienceLevel", "SE");
		SalariesList averageEX = f.filterBasedOnOneItem(
				list, "experienceLevel", "EX");

		// filter on year junior
		double avgEN2020 = f.AverageSalaryInUSD(
				f.filterBasedOnOneItem(averageEN, "workYear", "2020"));
		double avgEN2021 = f.AverageSalaryInUSD(
				f.filterBasedOnOneItem(averageEN, "workYear", "2021"));
		double avgEN2022 = f.AverageSalaryInUSD(
				f.filterBasedOnOneItem(averageEN, "workYear", "2022"));
		double avgEN2023 = f.AverageSalaryInUSD(
				f.filterBasedOnOneItem(averageEN, "workYear", "2023"));
		dataset.addValue(avgEN2020, "Junior", "2020");
		dataset.addValue(avgEN2021, "Junior", "2021");
		dataset.addValue(avgEN2022, "Junior", "2022");
		dataset.addValue(avgEN2023, "Junior", "2023");

		
		// filter on year mid level
		double avgMI2020 = f.AverageSalaryInUSD(
				f.filterBasedOnOneItem(averageMI, "workYear", "2020"));
		double avgMI2021 = f.AverageSalaryInUSD(
				f.filterBasedOnOneItem(averageMI, "workYear", "2021"));
		double avgMI2022 = f.AverageSalaryInUSD(
				f.filterBasedOnOneItem(averageMI, "workYear", "2022"));
		double avgMI2023 = f.AverageSalaryInUSD(
				f.filterBasedOnOneItem(averageMI, "workYear", "2023"));
		dataset.addValue(avgMI2020, "Mid-Level", "2020");
		dataset.addValue(avgMI2021, "Mid-Level", "2021");
		dataset.addValue(avgMI2022, "Mid-Level", "2022");
		dataset.addValue(avgMI2023, "Mid-Level", "2023");

		
		// filter on experience senior
		dataset.addValue(f.AverageSalaryInUSD(
				f.filterBasedOnOneItem(averageSE, "workYear", "2020")),
				"Senior", "2020");
		dataset.addValue(f.AverageSalaryInUSD(
				f.filterBasedOnOneItem(averageSE, "workYear", "2021")),
				"Senior", "2021");
		dataset.addValue(f.AverageSalaryInUSD(
				f.filterBasedOnOneItem(averageSE, "workYear", "2022")),
				"Senior", "2022");
		dataset.addValue(f.AverageSalaryInUSD(
				f.filterBasedOnOneItem(averageSE, "workYear", "2023")),
				"Senior", "2023");
		// filter and add for excecutive
		dataset.addValue(f.AverageSalaryInUSD(
				f.filterBasedOnOneItem(averageEX, "workYear", "2020")),
				"Executive", "2020");
		dataset.addValue(f.AverageSalaryInUSD(
				f.filterBasedOnOneItem(averageEX, "workYear", "2021")),
				"Executive", "2021");
		dataset.addValue(f.AverageSalaryInUSD(
				f.filterBasedOnOneItem(averageEX, "workYear", "2022")),
				"Executive", "2022");
		dataset.addValue(f.AverageSalaryInUSD(
				f.filterBasedOnOneItem(averageEX, "workYear", "2023")),
				"Executive", "2023");

		// create actual Chart/diagram
		String xAxisLabel = "Experience Level";
		String yAxisLabel = "Average Salary";
		boolean legend = true;

		JFreeChart chart = ChartFactory.createBarChart(title, xAxisLabel,
				yAxisLabel, dataset, PlotOrientation.VERTICAL, legend, false,
				false);
		chart.addSubtitle(new TextTitle(dataOrigin));
		
		CategoryItemRenderer renderer = ((CategoryPlot) chart.getPlot())
				.getRenderer();
		renderer.setDefaultItemLabelGenerator(
				new StandardCategoryItemLabelGenerator());
		renderer.setDefaultItemLabelsVisible(true);
		ItemLabelPosition position = new ItemLabelPosition(
				ItemLabelAnchor.OUTSIDE12,
				TextAnchor.TOP_CENTER);
		renderer.setDefaultPositiveItemLabelPosition(position);

		ChartPanel panel = new ChartPanel(chart);
		
		// create Frame
		JFrame frame = new JFrame("V0-Average Salary based on Experience");
		frame.setSize(1200, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setContentPane(panel);
	}
}
