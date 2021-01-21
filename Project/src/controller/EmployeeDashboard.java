package controller;

import bo.BOFactory;
import bo.custom.DashBoardBO;
import com.jfoenix.controls.JFXTextField;
import dto.DashBoardDTO;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.input.KeyEvent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EmployeeDashboard {
    public BarChart barChart;
    public CategoryAxis horizontalBar;
    public NumberAxis verticalBar;
    public JFXTextField txtNoOfDays;

    int noOfDates=7;

    DashBoardBO dashBoardBO = (DashBoardBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DASHBOARD);

    public void initialize() throws Exception {
        txtNoOfDays.setText(noOfDates+"");
        barChartLoad();
    }

    public void barChartLoad() throws Exception {
        String date = generateDate();
        if(txtNoOfDays.getText().length()==0){
            txtNoOfDays.setText(noOfDates+"");
        }
        DashBoardDTO dashBoardDTO = new DashBoardDTO(date,Integer.parseInt(txtNoOfDays.getText()));
        ArrayList<DashBoardDTO> dashBoardDTOS = dashBoardBO.loadBarChart(dashBoardDTO);

        XYChart.Series dataSeries1 = new XYChart.Series<>();
        barChart.getData().clear();
        for (DashBoardDTO d : dashBoardDTOS) {
            dataSeries1.getData().add(new XYChart.Data(d.getDate(),d.getNoOfDays()));
        }

        barChart.getData().addAll(dataSeries1);
    }

    public String generateDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }


    public void changeNoOfDays(KeyEvent keyEvent) throws Exception {
        barChartLoad();
    }
}
