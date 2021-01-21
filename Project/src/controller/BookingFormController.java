package controller;

import bo.BOFactory;
import bo.custom.BookingBO;
import bo.custom.VehicleBO;
import com.jfoenix.controls.*;
import db.DBConnection;
import directives.Report;
import dto.*;
import entity.Reservation;
import entity.SelectVehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class BookingFormController {
    public JFXComboBox cmbCardTypes;
    public Label lblDate;
    public JFXTextField txtBookingDate;
    public JFXButton btnSelectVehicle;
    public JFXButton btnRemove;
    public JFXButton btnSelectCustomer;
    public JFXButton btnViewAllBooking;
    public JFXButton btnBook;
    public JFXButton btnCancel;
    public JFXDatePicker dateFrom;
    public JFXDatePicker dateTo;
    public JFXTextField txtVehicleModel;
    public JFXComboBox cmbSelectVehileModel;
    public TableView tblSelectedVehicle;
    public TableColumn clmId;
    public TableColumn clmModel;
    public TableColumn clmVehicleNo;
    public TableColumn clmFromDate;
    public TableColumn clmToDate;
    public TableColumn clmDefaultRate;
    public TableColumn clmKm;
    public TableColumn clmAddingRate;
    public Label lblCustomerName;
    public Label lblCustomerNIC;
    public Label lblCustomerAddress;
    public Label lblCustomerMobileNo;
    public Label lblCustId;
    public TextField txtAmount;
    public JFXRadioButton rdoCash;
    public ToggleGroup paymentType;
    public JFXRadioButton rdoCard;
    public TextField txtCardNumber;
    public Label lblCardType;
    public Label lblCardNo;

    ArrayList<SelectVehicleDTO> selectedVehicle=new ArrayList<>();

    VehicleBO vehicleBO = (VehicleBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.VEHICLE);
    BookingBO bookingBO = (BookingBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOKING);

    ObservableList<String> cardTypes = FXCollections.observableArrayList("Master Card", "Visa Card", "");

    public void initialize() throws Exception {
        //cmbCardTypes.setValue("Master Card");
        cmbCardTypes.setItems(cardTypes);
        generateDate();
        loadAllModels();

        rdoCash.setSelected(true);
        lblCardType.setVisible(false);
        cmbCardTypes.setVisible(false);
        lblCardNo.setVisible(false);
        txtCardNumber.setVisible(false);
    }


    //Method for Current Date
    public void generateDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        lblDate.setText(dateFormat.format(date));
    }

    private void loadAllModels() throws Exception {
        ArrayList<VehicleModelDTO> allModels = vehicleBO.getAllModels();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.add("");
        for (VehicleModelDTO v : allModels) {
            observableList.add(v.getModelName());
        }
        cmbSelectVehileModel.setItems(observableList);
    }


    //Get Selected Vehicle
    public void getSelectedVehicle(ArrayList<SelectVehicleDTO> selectedVehicle){
        this.selectedVehicle = selectedVehicle;
        tblSelectedVehicle.setItems(FXCollections.observableArrayList(selectedVehicle));

        clmModel.setCellValueFactory(new PropertyValueFactory<>("modelName"));
        clmVehicleNo.setCellValueFactory(new PropertyValueFactory<>("vehicleNo"));
        clmFromDate.setCellValueFactory(new PropertyValueFactory<>("fromDate"));
        clmToDate.setCellValueFactory(new PropertyValueFactory<>("toDate"));
        clmDefaultRate.setCellValueFactory(new PropertyValueFactory<>("defaultRate"));
        clmKm.setCellValueFactory(new PropertyValueFactory<>("km"));
        clmAddingRate.setCellValueFactory(new PropertyValueFactory<>("addingRate"));
    }

    //Get Selected Customer
    public void getSelectedCustomer(CustomerDTO customerDTO){
        lblCustId.setText(customerDTO.getCustID()+"");
        lblCustomerName.setText(customerDTO.getCustName());
        lblCustomerNIC.setText(customerDTO.getCustNIC());
        lblCustomerAddress.setText(customerDTO.getCustAddress());
        lblCustomerMobileNo.setText(customerDTO.getMobileNo());
    }



    //Select Vehicle Button Actions
    public void selectVehicleBtnClickAction(ActionEvent actionEvent) throws Exception {
        if(dateFrom.getValue()==null || dateTo.getValue()==null){
            new Alert(Alert.AlertType.WARNING, "From Date and To Date required!").show();
            return;
        }
                String fromDate = dateFrom.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String toDate = dateTo.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String selectedModel = (String) cmbSelectVehileModel.getSelectionModel().getSelectedItem()==null?"": (String) cmbSelectVehileModel.getSelectionModel().getSelectedItem();

                SelectVehicleDTO selectVehicleDTO = new SelectVehicleDTO(fromDate, toDate, selectedModel);

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/SelectVehicleForm.fxml"));
                Parent root = (Parent) fxmlLoader.load();

                SelectVehicleFormController selectVehicleFormController = fxmlLoader.getController();
                selectVehicleFormController.searchAvailableVehicles(selectVehicleDTO, this);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

    }

    public void selectVehicleBtnEnter(MouseEvent mouseEvent) {
        btnSelectVehicle.setStyle("-fx-background-color: " + "#45aaf2");
    }

    public void selectVehicleBtnExit(MouseEvent mouseEvent) {
        btnSelectVehicle.setStyle("-fx-background-color: " + "#2d98da");
    }


    //Remove Button Actions
    public void removeBtnClickAction(ActionEvent actionEvent) {
        SelectVehicleDTO selectedItem = (SelectVehicleDTO) tblSelectedVehicle.getSelectionModel().getSelectedItem();
        tblSelectedVehicle.getItems().remove(selectedItem);
    }

    public void removeBtnEnter(MouseEvent mouseEvent) {
        btnRemove.setStyle("-fx-background-color: " + "#45aaf2");
    }

    public void removeBtnExit(MouseEvent mouseEvent) {
        btnRemove.setStyle("-fx-background-color: " + "#2d98da");
    }


    //Select Customer Button Actions
    public void selectCusBtnClickAction(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/CustomerForm.fxml"));

            CustomerFormController cs = fxmlLoader.getController();
            cs.getDoneButton(true);
            cs.getParentDetails(this);

            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void selectCusBtnEnter(MouseEvent mouseEvent) {
        btnSelectCustomer.setStyle("-fx-background-color: " + "#45aaf2");
    }

    public void selectCusBtnExit(MouseEvent mouseEvent) {
        btnSelectCustomer.setStyle("-fx-background-color: " + "#2d98da");
    }


    //View Button Actions
    public void viewBtnClickAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ViewAllBookingsForm.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        ViewAllBookingsFormController controller = fxmlLoader.getController();
        controller.getParent(this);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void viewBtnEnter(MouseEvent mouseEvent) {
        btnViewAllBooking.setStyle("-fx-background-color: " + "#45aaf2");
    }

    public void viewBtnExit(MouseEvent mouseEvent) {
        btnViewAllBooking.setStyle("-fx-background-color: " + "#2d98da");
    }


    //Book Button Actions
    public void bookBtnClickAction(ActionEvent actionEvent) throws Exception {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure ? Do you want add new booking ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {

            if(lblCustId.getText().length()==0){
                new Alert(Alert.AlertType.WARNING, "Please select Customer !").show();
                return;
            }
            if(selectedVehicle.isEmpty()){
                new Alert(Alert.AlertType.WARNING, "Please select Vehicle !").show();
                return;
            }

            //Reservation Table
            int custId = Integer.parseInt(lblCustId.getText());
            UserDTO user = EmployeeFormController.userDTO;
            int userId = user.getUserId();
            String transactionDate = lblDate.getText();
            String resStatus = "Booked";

            ReservationDTO reservationDTO = new ReservationDTO(custId, userId, transactionDate, resStatus);

            //Reservation Detail Table
            int resId = 0;
            int vehicleId = selectedVehicle.get(0).getVehicleId();
            String fromDate = selectedVehicle.get(0).getFromDate();
            String toDate = selectedVehicle.get(0).getToDate();
            double defaultRate = selectedVehicle.get(0).getDefaultRate();
            int km = selectedVehicle.get(0).getKm();
            double addingRate = selectedVehicle.get(0).getAddingRate();

            ReservationDetailDTO reservationDetailDTO = new ReservationDetailDTO(resId, vehicleId, fromDate, toDate, defaultRate, km, addingRate);

            //Payment Table
            int paymentId = 0;
            String paymentDate = lblDate.getText();
            String paymentType = "Advance";
            double amount = Double.parseDouble(txtAmount.getText());

            PaymentDTO paymentDTO = new PaymentDTO(paymentId, paymentDate, paymentType, amount);

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

            PaymentDetailDTO paymentDetailDTO = new PaymentDetailDTO(paymentId, amount, paymentType2, cardNumber);

            BookingDTO bookingDTO = new BookingDTO();
            bookingDTO.setReservationDTO(reservationDTO);
            bookingDTO.setReservationDetailDTO(reservationDetailDTO);
            bookingDTO.setPaymentDTO(paymentDTO);
            bookingDTO.setPaymentDetailDTO(paymentDetailDTO);

            resId= bookingBO.addBooking(bookingDTO);
            if(resId>0){
                new Alert(Alert.AlertType.INFORMATION, "Booked !").show();
                clearAll();
                Report report = new Report();
                report.getReport(resId);
            }else{
                new Alert(Alert.AlertType.INFORMATION, "Failed !").show();
            }
        }


    }

    public void bookBtnEnter(MouseEvent mouseEvent) {
        btnBook.setStyle("-fx-background-color: " + "#45aaf2");
    }

    public void bookBtnExit(MouseEvent mouseEvent) {
        btnBook.setStyle("-fx-background-color: " + "#2d98da");
    }


    //Cancel Button Actions
    public void cancelBtnClickAction(ActionEvent actionEvent) {
        clearAll();
    }

    public void cancelBtnEnter(MouseEvent mouseEvent) {
        btnCancel.setStyle("-fx-background-color: " + "#45aaf2");
    }

    public void cancelBtnExit(MouseEvent mouseEvent) {
        btnCancel.setStyle("-fx-background-color: " + "#2d98da");
    }


    //Radio Buttons Actions
    public void cashRadioBtnAction(ActionEvent actionEvent) {
        if(rdoCash.isSelected()){
            lblCardType.setVisible(false);
            cmbCardTypes.setVisible(false);
            txtCardNumber.setVisible(false);
            lblCardNo.setVisible(false);
        }
    }

    public void cardRadioBtnAction(ActionEvent actionEvent) {
        if(rdoCard.isSelected()){
            lblCardType.setVisible(true);
            cmbCardTypes.setVisible(true);
            txtCardNumber.setVisible(true);
            lblCardNo.setVisible(true);
        }
    }

    public void clearAll(){
        dateFrom.getEditor().clear();
        dateTo.getEditor().clear();
        cmbSelectVehileModel.getSelectionModel().clearSelection();
        tblSelectedVehicle.getItems().clear();

        lblCustId.setText("");
        lblCustomerName.setText("");
        lblCustomerNIC.setText("");
        lblCustomerAddress.setText("");
        lblCustomerMobileNo.setText("");

        txtAmount.clear();
        rdoCash.setSelected(true);
        lblCardType.setVisible(false);
        cmbCardTypes.getSelectionModel().clearSelection();
        cmbCardTypes.setVisible(false);
        lblCardNo.setVisible(false);
        txtCardNumber.clear();
        txtCardNumber.setVisible(false);

        btnBook.setText("Book");
    }

    public void setUpdateBooking(BookingDTO s){
        dateFrom.setValue(localDate(s.getFromDate()));
        dateTo.setValue(localDate(s.getToDate()));

        cmbSelectVehileModel.setValue(s.getModelName());
        ArrayList<BookingDTO> table = new ArrayList<>();
        table.add(s);
        tblSelectedVehicle.setItems(FXCollections.observableArrayList(table));
        clmModel.setCellValueFactory(new PropertyValueFactory<>("modelName"));
        clmVehicleNo.setCellValueFactory(new PropertyValueFactory<>("vehicleNo"));
        clmFromDate.setCellValueFactory(new PropertyValueFactory<>("fromDate"));
        clmToDate.setCellValueFactory(new PropertyValueFactory<>("toDate"));
        clmDefaultRate.setCellValueFactory(new PropertyValueFactory<>("defaultRate"));
        clmKm.setCellValueFactory(new PropertyValueFactory<>("km"));
        clmAddingRate.setCellValueFactory(new PropertyValueFactory<>("addingRate"));

        lblCustId.setText(s.getCustId()+"");
        lblCustomerName.setText(s.getCustName());
        lblCustomerNIC.setText(s.getCustNic());
        lblCustomerAddress.setText(s.getCustAddress());
        lblCustomerMobileNo.setText(s.getMobileNo());

        txtAmount.setText(s.getAmount()+"");
        String type = s.getPayment_type();
        if(type.equals("Cash")){
            rdoCash.setSelected(true);
        }else{
            rdoCard.setSelected(true);
            lblCardType.setVisible(true);
            cmbCardTypes.setVisible(true);
            lblCardNo.setVisible(true);
            txtCardNumber.setVisible(true);
            txtCardNumber.setText(s.getCardNumber()+"");
        }
    }
    public static final LocalDate localDate (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
}
