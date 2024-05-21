package org.vaadin.example;

import java.time.LocalDate;
import java.util.Random;

import com.storedobject.chart.BarChart;
import com.storedobject.chart.ChartException;
import com.storedobject.chart.Data;
import com.storedobject.chart.DateData;
import com.storedobject.chart.Legend;
import com.storedobject.chart.LineChart;
import com.storedobject.chart.RectangularCoordinate;
import com.storedobject.chart.SOChart;
import com.storedobject.chart.Size;
import com.storedobject.chart.Title;
import com.storedobject.chart.XAxis;
import com.storedobject.chart.YAxis;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
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
@PageTitle("Stacked Bar Charts")
public class MainView extends VerticalLayout {

    public MainView() {

        // Creating a chart display area
        SOChart sochart1 = new SOChart();
        sochart1.setSize("800px", "500px");

        // Generating some random values for a LineChart
        Random random = new Random();
        DateData xValues = new DateData();
        Data yValues1 = new Data(), yValues2 = new Data();
        for (int x = 0; x < 12; x++) {
            xValues.add(LocalDate.of(2021, x + 1, 1));
            yValues1.add(random.nextDouble());
            yValues2.add(random.nextDouble());
        }
        xValues.setName("Months of 2021");
        yValues1.setName("Random Values");

        // Bar charts is initialized with the generated XY values
        BarChart barChart1 = new BarChart(xValues, yValues1);
        barChart1.setName("Bar #1");
        barChart1.setStackName(
                "BC"); // Just a name - should be same for all the charts on the same stack
        BarChart barChart2 = new BarChart(xValues, yValues2);
        barChart2.setName("Bar #2");
        barChart2.setStackName(
                "BC"); // Just a name - should be same for all the charts on the same stack

        // Add a line chart too for demo purpose
        LineChart lineChart = new LineChart(xValues, yValues1);
        lineChart.setName("Line #1");

        // Define axes
        YAxis yAxis = new YAxis(yValues1); // Just need the value type as parameter
        XAxis xAxis = new XAxis(xValues);
        xAxis.setMinAsMinData(); // We want to start the X axis from minimum of our data
        xAxis.getLabel(true).setFormatter("{MMM}"); // Format the date
        xAxis.setName("2021");

        // Coordinate system
        RectangularCoordinate rc = new RectangularCoordinate(xAxis, yAxis);
        barChart1.plotOn(rc); // Plot on the rectangular coordinate.
        // barChart2.plotOn(rc); // Also you could do rc.add(barChart1, barChart2, lineChart);
        lineChart.plotOn(rc);

        // Title for the chart
        Title title = new Title("Stacked Bars & a Line Chart");
        title.setSubtext("To demo stacking feature");
        title.getPosition(true).setLeft(Size.percentage(10)); // Leave 10% space on the left side

        // We want to customize the legend's position
        sochart1.disableDefaultLegend();
        Legend legend = new Legend();
        legend.getPosition(true).setRight(Size.percentage(10)); // Leave 10% space on the right

        Button buttonAdd = new Button("Add Chart2",
        e -> {

            barChart2.plotOn(rc);
            // lineChart.plotOn(rc);
            try {
                sochart1.update();
            } catch (ChartException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        Button buttonHide = new Button("Hide  Chart1",
        e -> {

            legend.hide(barChart1);

            try {
                sochart1.update();
            } catch (ChartException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });



        // Add to the chart display area with a simple title and our custom legend
        // (Since rc is added, no need to add the charts already plotted on it)
        sochart1.add(rc, title, legend);

        add(sochart1, buttonAdd, buttonHide);
    }

}
