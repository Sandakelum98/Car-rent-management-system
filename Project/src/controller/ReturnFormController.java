package controller;

import bo.BOFactory;
import bo.custom.ReturnBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import dto.*;
import entity.Payment;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

public class ReturnFormController {
    public Label lblDate;
    public TableView tblCustomerAndVehicleInto;
    public TableColumn clmCustomerName;
    public TableColumn clmModel;
    public TableColumn clmFromDate;
    public TableColumn clmToDate;
    public TableColumn clmDefaultRate;
    public TableColumn clmKm;
    public TableColumn clmAddingRate;
    public TableColumn clmStartMileage;
    public JFXTextField txtSearch;
    public JFXButton btnSearch;
    public JFXButton btnDone;
    public TextField txtEndMileage;
    public Label lblDefaultRate;
    public Label lblNoOfDays;
    public Label lblDaysCharge;
    public Label lblAddingRate;
    public Label lblAddingKM;
    public Label lblAddingKMCharge;
    public Label lblTotalAmount;
    public Label lblAdvance;
    public Label lblBalance;
    public JFXRadioButton rdoCash;
    public JFXRadioButton rdoCard;
    public TextField txtCardNumber;
    public JFXButton btnComplete;
    public Label lblMulti2;
    public Label lblMulti1;
    public JFXButton btnCancel;
    public Label lblCardNumber;
    public JFXButton btnEditReturnDate;

    ReturnDTO returnDtoOb;
    ArrayList<ReturnDTO> returnDTOS = new ArrayList<>();

    ReturnBO returnBO = (ReturnBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RETURN);

    //Start
    public void initialize(){
        generateDate();
        lblMulti1.setVisible(false);
        lblMulti2.setVisible(false);
        rdoCash.setSelected(true);
        lblCardNumber.setVisible(false);
        txtCardNumber.setVisible(false);

    }

    //Generate Date
    private void generateDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        lblDate.setText(dateFormat.format(date));
    }


    //Search Button Actions
    public void searchBtnClickAction(ActionEvent actionEvent) throws Exception {
        if(txtSearch.getText().length()==0){
            new Alert(Alert.AlertType.WARNING,"Please enter Customer name or Mobile No.").show();
            return;
        }

        String value = txtSearch.getText();

        returnDTOS = returnBO.searchBooking(value);
        if(returnDTOS.isEmpty()){
            new Alert(Alert.AlertType.WARNING,"Not Found! Please check Customer details.").show();
            return;
        }

        this.returnDtoOb = returnDTOS.get(0);

        tblCustomerAndVehicleInto.setItems(FXCollections.observableArrayList(returnDTOS));
        clmCustomerName.setCellValueFactory(new PropertyValueFactory<>("custName"));
        clmModel.setCellValueFactory(new PropertyValueFactory<>("vehicleModel"));
        clmFromDate.setCellValueFactory(new PropertyValueFactory<>("fromDate"));
        clmToDate.setCellValueFactory(new PropertyValueFactory<>("toDate"));
        clmDefaultRate.setCellValueFactory(new PropertyValueFactory<>("defaultRate"));
        clmKm.setCellValueFactory(new PropertyValueFactory<>("km"));
        clmAddingRate.setCellValueFactory(new PropertyValueFactory<>("addingRate"));
        clmStartMileage.setCellValueFactory(new PropertyValueFactory<>("startMileage"));
    }
    
    public void searchBtnEnter(MouseEvent mouseEvent) {
        btnSearch.setStyle("-fx-background-color: " + "#45aaf2");
    }

    public void searchBtnExit(MouseEvent mouseEvent) {
        btnSearch.setStyle("-fx-background-color: " + "#2d98da");
    }
    
    
    //Done Button Actions
    public void doneBtnClickAction(ActionEvent actionEvent) throws Exception {
        if(returnDTOS.isEmpty()){
            new Alert(Alert.AlertType.WARNING,"No Customer & Vehicle Info !").show();
            return;
        }
        if(!Pattern.compile("[0-9]{1,}").matcher(txtEndMileage.getText()).matches()){
            new Alert(Alert.AlertType.WARNING,"Invalid Mileage").show();
            //txtStartMileage.setFocusColor(Paint.valueOf("red"));
            txtEndMileage.requestFocus();
            return;
        }
        int endMileage = Integer.parseInt(txtEndMileage.getText());

        boolean isAddedEndMileage = returnBO.saveEndMileage(returnDtoOb.getResDetailId(),endMileage);
        
        if(isAddedEndMileage){
            double defaultRate = returnDtoOb.getDefaultRate();
            int totalDays = returnDtoOb.getTotalDays();
            double daysCharge = defaultRate*totalDays;//*
            lblDefaultRate.setText(defaultRate+"");
            lblNoOfDays.setText(totalDays+"");
            lblDaysCharge.setText(daysCharge+"");

            double addingRate = returnDtoOb.getAddingRate();
            lblAddingRate.setText(addingRate+"");

            int starMileage = returnDtoOb.getStartMileage();
            int totalKM = endMileage-starMileage;

            int totalFreeKm = returnDtoOb.getKm()*totalDays;
            double addingKMCharge=0; //*

            if(totalKM>totalFreeKm){
                int addingKM = totalKM-totalFreeKm;
                lblAddingKM.setText(addingKM+"");
                addingKMCharge = addingRate*addingKM;
                lblAddingKMCharge.setText(addingKMCharge+"");
                lblMulti2.setVisible(true);
            }else{
                lblAddingRate.setText("");
                lblAddingKM.setText("");
                lblAddingKMCharge.setText("");
                lblMulti2.setVisible(false);
            }

            double totalAmount = daysCharge+addingKMCharge;
            lblTotalAmount.setText(totalAmount+"");
            double advance = returnDtoOb.getAdvanceAmount();
            lblAdvance.setText(advance+"");

            double balance = totalAmount-advance;
            lblBalance.setText(balance+"");

            lblMulti1.setVisible(true);
        }
    }

    public void doneBtnEnter(MouseEvent mouseEvent) {
        btnDone.setStyle("-fx-background-color: " + "#45aaf2");
    }

    public void doneBtnExit(MouseEvent mouseEvent) {
        btnDone.setStyle("-fx-background-color: " + "#2d98da");
    }
    
    
    //Complete Button Actions
    public void completeBtnClickAction(ActionEvent actionEvent) throws Exception {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure ? Do you want complete booking ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if(returnDTOS.isEmpty()){
            new Alert(Alert.AlertType.WARNING,"No Customer & Vehicle Info !").show();
            return;
        }
        if(lblTotalAmount.getText().length()==0){
            new Alert(Alert.AlertType.WARNING,"Please enter end mileage and click Done !").show();
            return;
        }

        //Reservation Table
        String resStatus = "Complete";
        ReservationDTO reservationDTO = new ReservationDTO(returnDtoOb.getResId(), resStatus);

        //Reservation Detail table
        double totalAmount = Double.parseDouble(lblTotalAmount.getText());
        ReservationDetailDTO reservationDetailDTO = new ReservationDetailDTO(returnDtoOb.getResDetailId(), totalAmount);

        //Payment Table
        String paymentDate = lblDate.getText();
        String paymentType = "Full Payment";
        double balance = Double.parseDouble(lblBalance.getText());
        PaymentDTO paymentDTO = new PaymentDTO(returnDtoOb.getResId(), paymentDate, paymentType, balance);

        //Payment Detail Table
        String paymentType2;
        int cardNumber = 0;
        if(rdoCash.isSelected()){
            paymentType2="Cash";
        }else{
            paymentType2="Card";
            if(txtCardNumber.getText().length()==0){
                new Alert(Alert.AlertType.WARNING,"Please Enter Card Number !").show();
                return;
            }
            cardNumber = Integer.parseInt(txtCardNumber.getText());
        }

        PaymentDetailDTO paymentDetailDTO = new PaymentDetailDTO(balance, paymentType2, cardNumber);

        ReturnDTO returnDTO = new ReturnDTO(
                reservationDTO,
                reservationDetailDTO,
                paymentDTO,
                paymentDetailDTO
        );

        boolean isReturned = returnBO.returnVehicle(returnDTO);
        if(isReturned){
            new Alert(Alert.AlertType.WARNING, "Complete").show();
            clearAll();
        }else{
            new Alert(Alert.AlertType.WARNING, "Failed").show();
        }

    }

    public void completeBtnEnter(MouseEvent mouseEvent) {
        btnComplete.setStyle("-fx-background-color: " + "#45aaf2");
    }

    public void completeBtnExit(MouseEvent mouseEvent) {
        btnComplete.setStyle("-fx-background-color: " + "#2d98da");
    }


    //Cancel Btton Actions
    public void cancelBtnClickAction(ActionEvent actionEvent) {
        clearAll();
    }

    public void cancelBtnEnter(MouseEvent mouseEvent) {
        btnCancel.setStyle("-fx-background-color: " + "#45aaf2");
    }

    public void cancelBtnExit(MouseEvent mouseEvent) {
        btnCancel.setStyle("-fx-background-color: " + "#2d98da");
    }


    //Cash Radio Button Action
    public void cardRadioBtnAction(ActionEvent actionEvent) {
        lblCardNumber.setVisible(true);
        txtCardNumber.setVisible(true);
    }

    public void cashRadioBtnAction(ActionEvent actionEvent) {
        txtCardNumber.setVisible(false);
        lblCardNumber.setVisible(false);
    }


    //Edit Return Date Button actions
    public void editReturnDateBtnClickAction(ActionEvent actionEvent) throws IOException {
        if(returnDTOS.isEmpty()){
            new Alert(Alert.AlertType.WARNING,"No Customer & Vehicle Info !").show();
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/EditReturnDateForm.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        EditReturnDateFormController controller = fxmlLoader.getController();
        controller.getParent(returnDtoOb.getResDetailId(),returnDtoOb.getFromDate(),returnDtoOb.getToDate(),this);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void editReturnDateBtnEnter(MouseEvent mouseEvent) {
        btnEditReturnDate.setStyle("-fx-background-color: " + "#45aaf2");
    }

    public void editReturnDateBtnExit(MouseEvent mouseEvent) {
        btnEditReturnDate.setStyle("-fx-background-color: " + "#2d98da");
    }

    public void clearAll(){
        txtSearch.clear();
        tblCustomerAndVehicleInto.getItems().clear();
        txtEndMileage.clear();

        rdoCash.setSelected(true);
        txtCardNumber.clear();
        lblCardNumber.setVisible(false);
        txtCardNumber.setVisible(false);

        lblDefaultRate.setText("");
        lblMulti1.setVisible(false);
        lblNoOfDays.setText("");
        lblDaysCharge.setText("");

        lblAddingRate.setText("");
        lblMulti2.setVisible(false);
        lblAddingKM.setText("");
        lblAddingKMCharge.setText("");

        lblTotalAmount.setText("");
        lblAdvance.setText("");
        lblBalance.setText("");
    }
}
