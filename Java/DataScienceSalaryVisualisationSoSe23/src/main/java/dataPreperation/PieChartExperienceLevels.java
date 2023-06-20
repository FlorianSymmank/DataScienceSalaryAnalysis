package dataPreperation;

import java.text.DecimalFormat;

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

public class PieChartExperienceLevels extends JFrame{

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	public void createPieChart(SalariesList list, String title, String dataOrigin) {
		int en = 0, mi = 0, se = 0, ex = 0;

		for (int i = 0; i < list.getSalaryEntries(); i++) {
			switch (list.getSalarayAt(i).getExperienceLevel()) {
			case "EN":
				en++;
				break;
			case "MI":
				mi++;
				break;
			case "SE":
				se++;
				break;
			case "EX":
				ex++;
				break;
			}
			
		}

		DefaultPieDataset<String> dataset = new DefaultPieDataset<String>();
		dataset.setValue("Junior: " +en, en); // key - value
		dataset.setValue("Mid-Level: " +mi,mi);
		dataset.setValue("Senior: " + se,se);
		dataset.setValue("Executive:" + ex, ex);

		
		
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
