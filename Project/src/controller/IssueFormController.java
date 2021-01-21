package controller;

import bo.BOFactory;
import bo.custom.IssueBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.IssueDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

public class IssueFormController {
    public Label lblDate;
    public JFXButton btnSearch;
    public JFXTextField txtSearchBooking;
    public Label lblCustomerName;
    public Label lblCustomerNIC;
    public Label lblCustomerAddress;
    public Label lblCustomerMobileNo;
    public TextField txtFromDate;
    public TextField txtToDate;
    public JFXButton btnIssue;
    public JFXButton btnCancel;
    public TableView tblVehicleInfo;
    public TableColumn clmId;
    public TableColumn clmMake;
    public TableColumn clmModel;
    public TableColumn clmVehicleNo;
    public TableColumn clmYear;
    public TableColumn clmColour;
    public TableColumn clmDefaultRate;
    public TableColumn clmKm;
    public TableColumn clmAddingRate;
    public TextField txtStartMileage;
    public DatePicker datePicker;

    IssueDTO ss;
    ArrayList<IssueDTO> searchBooking = new ArrayList<>();

    IssueBO issueBO = (IssueBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ISSUE);

    public void initialize(){
        generateDate();
        txtSearchBooking.requestFocus();
    }

    //Method for Current Date
    public void generateDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        lblDate.setText(dateFormat.format(date));
    }


    //Search Button Actions
    public void searchBtnClickAction(ActionEvent actionEvent) throws Exception {
        if(datePicker.getValue()==null){
            new Alert(Alert.AlertType.WARNING, "Please select date !").show();
            return;
        }
        if(txtSearchBooking.getText().length()==0){
            new Alert(Alert.AlertType.WARNING, "Please enter Customer Mobile No or Customer Name !").show();
            return;
        }
        String fromDate = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String value = txtSearchBooking.getText();

        searchBooking = issueBO.searchBooking(fromDate,value);

        if(searchBooking.isEmpty()){
            new Alert(Alert.AlertType.WARNING, "Not Found! Please Check Date and Customer Detail.").show();
            return;
        }
        this.ss = searchBooking.get(0);

            lblCustomerName.setText(ss.getCustName());
            lblCustomerNIC.setText(ss.getCustNIC());
            lblCustomerAddress.setText(ss.getCustAddress());
            lblCustomerMobileNo.setText(ss.getMobileNo());
            txtFromDate.setText(ss.getFromDate());
            txtToDate.setText(ss.getToDate());

            tblVehicleInfo.setItems(FXCollections.observableArrayList(searchBooking));
            clmMake.setCellValueFactory(new PropertyValueFactory<>("vehicleMake"));
            clmModel.setCellValueFactory(new PropertyValueFactory<>("getVehicleModel"));
            clmVehicleNo.setCellValueFactory(new PropertyValueFactory<>("vehicleNo"));
            clmYear.setCellValueFactory(new PropertyValueFactory<>("vehicleYear"));
            clmColour.setCellValueFactory(new PropertyValueFactory<>("vehicleColour"));
            clmDefaultRate.setCellValueFactory(new PropertyValueFactory<>("defaultRate"));
            clmKm.setCellValueFactory(new PropertyValueFactory<>("km"));
            clmAddingRate.setCellValueFactory(new PropertyValueFactory<>("AddingRate"));

    }

    public void searchBtnEnter(MouseEvent mouseEvent) {
        btnSearch.setStyle("-fx-background-color: "+"#45aaf2");
    }

    public void SearchBtnExit(MouseEvent mouseEvent) {
        btnSearch.setStyle("-fx-background-color: "+"#2d98da");
    }

    
    //Issue Button Actions
    public void issueBtnClickAction(ActionEvent actionEvent) throws Exception {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure ? Do you want issue vehicle ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if(searchBooking.isEmpty()){
            new Alert(Alert.AlertType.WARNING,"Please select Booking !").show();
            return;
        }
        if(txtStartMileage.getText().length()==0){
            new Alert(Alert.AlertType.WARNING,"Please enter Start Mileage !").show();
            return;
        }
        if(!Pattern.compile("[0-9]{1,}").matcher(txtStartMileage.getText()).matches()){
            new Alert(Alert.AlertType.WARNING,"Invalid Mileage").show();
            txtStartMileage.requestFocus();
            return;
        }
        int startMileage = Integer.parseInt(txtStartMileage.getText());
        String resStatus = "Issued";

        IssueDTO issueDTO = new IssueDTO(ss.getResId(), resStatus, ss.getResDetailId(), startMileage);

        boolean isAdded = issueBO.issueVehicle(issueDTO);
        if(isAdded){
            new Alert(Alert.AlertType.INFORMATION, "Issued!").show();
            clearAll();
        }else{
            new Alert(Alert.AlertType.INFORMATION, "Failed!").show();
        }
    }

    public void issueBtnEnter(MouseEvent mouseEvent) {
        btnIssue.setStyle("-fx-background-color: "+"#45aaf2");
    }

    public void issueBtnExit(MouseEvent mouseEvent) {
        btnIssue.setStyle("-fx-background-color: "+"#2d98da");
    }

    
    //Cancel Button Actions
    public void cancelBtnClickAction(ActionEvent actionEvent) {
        clearAll();
    }

    public void cancelBtnEnter(MouseEvent mouseEvent) {
        btnCancel.setStyle("-fx-background-color: "+"#45aaf2");
    }

    public void cancelBtnExit(MouseEvent mouseEvent) {
        btnCancel.setStyle("-fx-background-color: "+"#2d98da");
    }

    //Clear All
    public void clearAll(){
        datePicker.setValue(null);
        txtSearchBooking.clear();
        lblCustomerName.setText("");
        lblCustomerNIC.setText("");
        lblCustomerAddress.setText("");
        lblCustomerMobileNo.setText("");
        searchBooking = new ArrayList<>();

        txtFromDate.clear();
        txtToDate.clear();

        tblVehicleInfo.getItems().clear();
        txtStartMileage.clear();
    }

}
