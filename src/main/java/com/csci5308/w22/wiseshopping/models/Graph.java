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
    private static final String TITLE = "Product demand trend";
    private static  final String XAXISLABEL = "Month";
    private static final String YAXISLABEL = "items sold";
    private static final float STROKESIZE = 2.0f;
    private static final int CHARTSIZE = 18;
    public JFreeChart createChart(XYDataset dataset, String chartName) {

        JFreeChart chart = ChartFactory.createXYLineChart(TITLE,XAXISLABEL,YAXISLABEL,
                dataset,PlotOrientation.VERTICAL,true,true,false);

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(STROKESIZE));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Product demand trend",
                        new Font("Serif", java.awt.Font.BOLD, CHARTSIZE)
                )
        );
        try {
            int width = 450;
            int height = 400;
            ChartUtils.saveChartAsPNG(new File("./productDemandCharts/"+ chartName + ".png"), chart, width,height);
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
