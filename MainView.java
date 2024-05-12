package org.vaadin.example;

import java.util.Random;

import com.storedobject.chart.BarChart;
import com.storedobject.chart.CategoryData;
import com.storedobject.chart.Data;
import com.storedobject.chart.DataType;
import com.storedobject.chart.LineChart;
import com.storedobject.chart.Position;
import com.storedobject.chart.RectangularCoordinate;
import com.storedobject.chart.SOChart;
import com.storedobject.chart.Size;
import com.storedobject.chart.XAxis;
import com.storedobject.chart.YAxis;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and use @Route
 * annotation to announce it in a URL as a Spring managed bean.
 * <p>
 * A new instance of this class is created for every new user and every browser
 * tab/window.
 * <p>
 * The main view contains a text field for getting the user name and a button
 * that shows a greeting message in a notification.
 */
@Route
public class MainView extends Div {


    public MainView() {



        SOChart soChart = new SOChart();
        soChart.setSize("1800px", "1000px");

        // Generating some random values for the bar charts
        Random random = new Random();
        CategoryData xValues = new CategoryData();
        Data yValues1 = new Data();
        Data yValues2 = new Data();
        Data yValues3 = new Data();
        Data yValues4 = new Data();

        for (int x = 0; x <= 6; x++) {
            xValues.add("" + (2010 + x));
            yValues1.add(random.nextInt(100));
            yValues2.add(random.nextInt(100));
            yValues3.add(random.nextInt(100));
            yValues4.add(random.nextInt(100));

        }

        // Define axes
        XAxis xAxis = new XAxis(xValues);
        xAxis.setMinAsMinData();
        YAxis yAxis1 = new YAxis(yValues1);

        // Bar charts are initialized with the generated XY values
        BarChart barChart1 = new BarChart(xValues, yValues1);
        barChart1.setName("BarCh1");


        BarChart barChart2 = new BarChart(xValues, yValues2);
        barChart1.setName("BarCh2");

        BarChart barChart3 = new BarChart(xValues, yValues3);
        barChart1.setName("BarCh3");



        //barchart1 position
        // Use a coordinate system
        RectangularCoordinate rc1 = new RectangularCoordinate(xAxis, yAxis1);
        barChart1.plotOn(rc1); // Specifying axis because there are more axes
        Position p = new Position();
        // p.justifyLeft();
        p.setRight(Size.percentage(55));
        p.setBottom(Size.percentage(55));
        // p.alignTop();
        rc1.setPosition(p);

        //barchart2 position
        // Use a coordinate system
        RectangularCoordinate rc2 = new RectangularCoordinate(xAxis, yAxis1);
        barChart2.plotOn(rc2); // Specifying axis because there are more axes
        p = new Position();
        // p.justifyLeft();
        p.setLeft(Size.percentage(55));
        p.setBottom(Size.percentage(55));
        // p.alignTop();
        rc2.setPosition(p);

        // barchart3 position
        // Use a coordinate system
        RectangularCoordinate rc3 = new RectangularCoordinate(xAxis, yAxis1);
        barChart3.plotOn(rc3); // Specifying axis because there are more axes
        p = new Position();
        // p.justifyLeft();
        p.setRight(Size.percentage(55));
        p.setTop(Size.percentage(55));
        // p.alignTop();
        rc3.setPosition(p);


        //chart4 - linechart

        Data xValuesLC = new Data(), yValuesLC = new Data();

        for (int x = 0; x < 40; x++) {
            xValuesLC.add(x);
            yValuesLC.add(random.nextDouble());
        }

        XAxis xAxisLC = new XAxis(DataType.NUMBER);
        YAxis yAxisLC = new YAxis(DataType.NUMBER);

        xAxisLC.setMax(40);
        LineChart lineChart = new LineChart(xValuesLC, yValuesLC);

        RectangularCoordinate rc4 = new RectangularCoordinate(xAxisLC, yAxisLC);
        lineChart.plotOn(rc4);

        // position bottom right
        p = new Position();
        p.setLeft(Size.percentage(55));
        p.setTop(Size.percentage(55));

        rc4.setPosition(p);



        // Line Chart - rc4 renders only first 6 data points which is rougly the size of other Bar charts Xaxis items
        soChart.add(rc1, rc2, rc3, rc4);

        // changing order of adding charts to SOChart container is a possible workaround but other charts Xaxis 
        // get distorted especially in the case of dynamic data pust

        // soChart.add(rc4, rc2, rc3, rc1);
 

    add(soChart);
    }

}
