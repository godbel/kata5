package software.ulpgc.kata5.application;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import software.ulpgc.kata5.architecture.viewmodel.Histogram;

import javax.swing.*;

public class Desktop extends JFrame {

    private Desktop() {
        this.setTitle("Histogram");
        this.setResizable(false);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
    }

    public static Desktop creat() {
        return new Desktop();
    }

    public Desktop display(Histogram h) {
        this.getContentPane().add(chartPanelWith(h));
        return this;
    }

    private ChartPanel chartPanelWith(Histogram h) {
        return new ChartPanel(chartWith(h));
    }

    private JFreeChart chartWith(Histogram h) {
        return ChartFactory.createHistogram(h.title(), h.xAxis(), h.yAxis(), datasetWith(h));
    }

    private XYSeriesCollection datasetWith(Histogram h) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(seriesIn(h));
        return dataset;
    }

    private XYSeries seriesIn(Histogram h) {
        XYSeries series = new XYSeries(h.legend());
        for (int bin : h) {
            series.add(bin, h.count(bin));
        }
        return series;
    }

}