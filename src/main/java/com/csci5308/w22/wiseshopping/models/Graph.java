package com.csci5308.w22.wiseshopping.models;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Elizabeth James
 */
public class Graph {
    public JFreeChart createChart(XYDataset dataset, String chartName) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Product demand trend",
                "Month",
                "items sold",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Product demand trend",
                        new Font("Serif", java.awt.Font.BOLD, 18)
                )
        );
        try {
            ChartUtils.saveChartAsPNG(new File(chartName + ".png"), chart, 450, 400);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return chart;
    }

}
/**
 * reference: https://www.jfree.org/jfreechart/
 * https://zetcode.com/java/jfreechart/
 */
