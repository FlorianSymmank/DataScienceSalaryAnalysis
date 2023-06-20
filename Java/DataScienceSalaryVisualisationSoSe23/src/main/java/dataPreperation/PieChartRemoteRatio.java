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

public class PieChartRemoteRatio{

	public void createPieChart(SalariesList list, int year, String title, String dataOrigin) {
		int zero = 0, fifty = 0, hundred = 0;

		SalariesList afterYear = new SalariesList();
		for (int i = 0; i < list.getSalaryEntries(); i++) {
			if (list.getSalarayAt(i).getWorkYear() == year) {
				afterYear.addSalary(list.getSalarayAt(i));
			}
		}
		
		for (int i = 0; i < afterYear.getSalaryEntries(); i++) {
			switch (afterYear.getSalarayAt(i).getRemoteRatio()) {
			case 0:
				zero++;
				break;
			case 50:
				fifty++;
				break;
			case 100:
				hundred++;
				break;
			}
		}

		DefaultPieDataset<String> dataset = new DefaultPieDataset<String>();
		dataset.setValue("0% Remote: " + zero, zero);
		dataset.setValue("50% Remote: " + fifty, fifty);
		dataset.setValue("100% Remote: " + hundred, hundred);

		

		// Create chart
		JFreeChart chart = ChartFactory.createPieChart(
				title + " in " + year,
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
		JFrame frame = new JFrame("PieChart");
		frame.setSize(1200, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setContentPane(panel);
	}

}
