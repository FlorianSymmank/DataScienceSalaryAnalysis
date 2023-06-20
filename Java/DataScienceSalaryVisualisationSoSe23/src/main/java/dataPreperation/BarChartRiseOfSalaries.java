package dataPreperation;

import javax.swing.JFrame;
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

public class BarChartRiseOfSalaries extends JFrame{

	private DataScienceCalculationFiltering f = new DataScienceCalculationFiltering();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1651l;

	public void createBarChart(SalariesList list) {

		SalariesList en = setupData(list, "EN");
		SalariesList mi = setupData(list, "MI");
		SalariesList se = setupData(list, "SE");
		SalariesList ex = setupData(list, "EX");

		// filter JUNIOR on workyear and calculate the avg salary in this year
		double en2020 = f.AverageSalaryInUSD(
				f.filterBasedOnOneItem(en, "workYear", "2020"));
		double en2021 = f.AverageSalaryInUSD(
				f.filterBasedOnOneItem(en, "workYear", "2021"));
		double en2022 = f.AverageSalaryInUSD(
				f.filterBasedOnOneItem(en, "workYear", "2022"));
		double en2023 = f.AverageSalaryInUSD(
				f.filterBasedOnOneItem(en, "workYear", "2023"));

		// difference between 2020 and the other year
		double diff20_21 = en2021 - en2020;
		double diff21_22 = en2022 - en2021;
		double diff22_23 = en2023 - en2022;
		// calculate the difference as percent
		double inPercent21EN = Math.round((diff20_21 / en2020) * 100);
		double inPercent22EN = Math.round((diff21_22 / en2020) * 100);
		double inPercent23EN = Math.round((diff22_23 / en2020) * 100);

		// filter MID-Level on workyear and calculate the avg salary in this year
		double mi20 = f.AverageSalaryInUSD(
				f.filterBasedOnOneItem(mi, "workYear", "2020"));
		double mi21 = f.AverageSalaryInUSD(
				f.filterBasedOnOneItem(mi, "workYear", "2021"));
		double mi22 = f.AverageSalaryInUSD(
				f.filterBasedOnOneItem(mi, "workYear", "2022"));
		double mi23 = f.AverageSalaryInUSD(
				f.filterBasedOnOneItem(mi, "workYear", "2023"));

		double diffMI20_21 = mi21 - mi20;
		double diffMI21_22 = mi22 - mi21;
		double diffMI22_23 = mi23 - mi22;
		
		double inPercent21MI = Math.round((diffMI20_21 / mi20) * 100);
		double inPercent22MI = Math.round((diffMI21_22 / mi20) * 100);
		double inPercent23MI = Math.round((diffMI22_23 / mi20) * 100);

		// filter SENIOR on workyear and calculate the avg salary in this year
		double se20 = f.AverageSalaryInUSD(
				f.filterBasedOnOneItem(se, "workYear", "2020"));
		double se21 = f.AverageSalaryInUSD(
				f.filterBasedOnOneItem(se, "workYear", "2021"));
		double se22 = f.AverageSalaryInUSD(
				f.filterBasedOnOneItem(se, "workYear", "2022"));
		double se23 = f.AverageSalaryInUSD(
				f.filterBasedOnOneItem(se, "workYear", "2023"));

		double diffSE20_21 = se21 - se20;
		double diffSE21_22 = se22 - se21;
		double diffSE22_23 = se23 - se22;
		
		double inPercent21SE = Math.round((diffSE20_21 / se20) * 100);
		double inPercent22SE = Math.round((diffSE21_22 / se20) * 100);
		double inPercent23SE = Math.round((diffSE22_23 / se20) * 100);

		// filter EXECUTIVE on workyear and calculate the avg salary in this year
		double ex20 = f.AverageSalaryInUSD(
				f.filterBasedOnOneItem(ex, "workYear", "2020"));
		double ex21 = f.AverageSalaryInUSD(
				f.filterBasedOnOneItem(ex, "workYear", "2021"));
		double ex22 = f.AverageSalaryInUSD(
				f.filterBasedOnOneItem(ex, "workYear", "2022"));
		double ex23 = f.AverageSalaryInUSD(
				f.filterBasedOnOneItem(ex, "workYear", "2023"));

		double diffEX20_21 = ex21 - ex20;
		double diffEX21_22 = ex22 - ex21;
		double diffEX22_23 = ex23 - ex22;
		
		double inPercent21EX = Math.round((diffEX20_21 / ex20) * 100);
		double inPercent22EX = Math.round((diffEX21_22 / ex20) * 100);
		double inPercent23EX = Math.round((diffEX22_23 / ex20) * 100);

		// add data to dataset, which is displayed as the actual chart
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(inPercent21EN, "Junior", "2021");
		dataset.addValue(inPercent22EN, "Junior", "2022");
		dataset.addValue(inPercent23EN, "Junior", "2023");

		dataset.addValue(inPercent21MI, "Mid-Level", "2021");
		dataset.addValue(inPercent22MI, "Mid-Level", "2022");
		dataset.addValue(inPercent23MI, "Mid-Level", "2023");

		dataset.addValue(inPercent21SE, "Senior", "2021");
		dataset.addValue(inPercent22SE, "Senior", "2022");
		dataset.addValue(inPercent23SE, "Senior", "2023");

		dataset.addValue(inPercent21EX, "Executive", "2021");
		dataset.addValue(inPercent22EX, "Executive", "2022");
		dataset.addValue(inPercent23EX, "Executive", "2023");

		// create actual Chart/diagram
		String title = "Change of Salaries in percent from the previous year";
		String xAxisLabel = "Experience Level";
		String yAxisLabel = "Average Salarychange in Percent";
		boolean legend = true;

		JFreeChart chart = ChartFactory.createBarChart(title, xAxisLabel,
				yAxisLabel, dataset, PlotOrientation.VERTICAL, legend, false,
				false);
		chart.addSubtitle(new TextTitle("ai-jobs.net"));
		
		// add numbers
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
		JFrame frame = new JFrame("Average SalaryChange");
		frame.setSize(1200, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setContentPane(panel);
	}

		/**
		 * filter given list on a specific experience level
		 * @param list
		 * @param ex
		 * @return
		 */
	private SalariesList setupData(SalariesList list, String ex) {
		return f.filterBasedOnOneItem(list, "experienceLevel", ex);
	}

}
