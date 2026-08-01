package com.legacy.reporting;

import com.legacy.analysis.JavaParserAnalyzer;
import com.legacy.analysis.RiskGraph;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class RiskHeatmapGenerator {
    private RiskGraph riskGraph;

    public RiskHeatmapGenerator(RiskGraph riskGraph) {
        this.riskGraph = riskGraph;
    }

    public void generateHeatmap() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        riskGraph.getRiskNodes().forEach((className, riskLevel) -> {
            dataset.addValue(riskLevel.getRiskLevel(), "Risk Level", className);
        });

        JFreeChart chart = ChartFactory.createBarChart(
                "Risk Heatmap",
                "Class Name",
                "Risk Level",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));

        JFrame frame = new JFrame("Risk Heatmap");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
