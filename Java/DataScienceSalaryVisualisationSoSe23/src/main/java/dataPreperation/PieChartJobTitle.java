package dataPreperation;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

public class PieChartJobTitle extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 505;
/**
 * creates a piechart with the jobtitle descriptions out of the given list
 * @param list given salaries
 * @param join if jobs with an amount  below @threshold should be joined
 * @param threshold all below that threshold are joined in the categorie "other"
 * @param title title of the chart
 * @param dataOrigin origin of the list 
 */
	public void createPieChar(SalariesList list, boolean join, int threshold,
			String title, String dataOrigin) {
		HashMap<String, Integer> jobs = new HashMap<String, Integer>();
		for (int i = 0; i < list.getSalaryEntries(); i++) {
			String currentJobTitle = list.getSalarayAt(i).getJobTitle();
			if (jobs.containsKey(currentJobTitle)) {
				int current = jobs.get(currentJobTitle);
				jobs.put(currentJobTitle, current + 1);
			} else {
				jobs.put(currentJobTitle, 1);
			}
		}

		int rest = 0;
		DefaultPieDataset<String> dataset = new DefaultPieDataset<String>();

		if (join) {
			for (Map.Entry<String, Integer> entry : jobs.entrySet()) {
				if (entry.getValue() > threshold) {
					dataset.setValue(entry.getKey() + ": " + entry.getValue(),
							entry.getValue());
				} else {
					rest++;
				}
			}
			dataset.setValue("others: ", rest);
		} else {
			for (Map.Entry<String, Integer> entry : jobs.entrySet()) {
				dataset.setValue(entry.getKey() + ": " + entry.getValue(),
						entry.getValue());
			}
		}

		// Create chart
		JFreeChart chart = ChartFactory.createPieChart(
				title,
				dataset,
				true,
				false,
				false);
		chart.addSubtitle(new TextTitle(dataOrigin));

		// Format Label
		PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator(
				"{0} ({2})", new DecimalFormat("0"),
				new DecimalFormat("0%"));
		((PiePlot<?>) chart.getPlot()).setLabelGenerator(labelGenerator);

		ChartPanel panel = new ChartPanel(chart);
		// create Frame
		JFrame frame = new JFrame("Parts of");
		frame.setSize(1200, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setContentPane(panel);
	}
}
