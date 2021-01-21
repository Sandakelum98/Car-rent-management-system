package controller;

import bo.BOFactory;
import bo.custom.BookingBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.BookingDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.ArrayList;

public class ViewAllBookingsFormController {
    public TableView tblAllBookings;
    public TableColumn clmResId;
    public TableColumn clmCustName;
    public TableColumn clmMobileNo;
    public TableColumn clmToDate;
    public TableColumn clmFromDate;
    public TableColumn clmModel;
    public TableColumn clmPayment;
    public TableColumn clmBookingDate;
    public JFXButton btnUpdate;
    public JFXTextField txtSearch;

    BookingFormController bookingFormController;

    BookingBO bookingBO = (BookingBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOKING);

    public void initialize() throws Exception {
        loadAllBookings();
    }

    public void getParent(BookingFormController bookingFormController){
        this.bookingFormController = bookingFormController;
    }

    private void loadAllBookings() throws Exception {
        ArrayList<BookingDTO> allBookings = bookingBO.getAllBookinds();
        tblAllBookings.setItems(FXCollections.observableArrayList(allBookings));

        clmResId.setCellValueFactory(new PropertyValueFactory<>("resId"));
        clmCustName.setCellValueFactory(new PropertyValueFactory<>("custName"));
        clmMobileNo.setCellValueFactory(new PropertyValueFactory<>("mobileNo"));
        clmFromDate.setCellValueFactory(new PropertyValueFactory<>("fromDate"));
        clmToDate.setCellValueFactory(new PropertyValueFactory<>("toDate"));
        clmModel.setCellValueFactory(new PropertyValueFactory<>("modelName"));
        clmPayment.setCellValueFactory(new PropertyValueFactory<>("amount"));
        clmBookingDate.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));
    }

    //Search Text Action
    public void searchTxtKeyReleaseAction(KeyEvent keyEvent) throws Exception {
        String value = txtSearch.getText();
        ArrayList<BookingDTO> searchBooking = bookingBO.searchBooking(value);

        tblAllBookings.setItems(FXCollections.observableArrayList(searchBooking));

        clmResId.setCellValueFactory(new PropertyValueFactory<>("resId"));
        clmCustName.setCellValueFactory(new PropertyValueFactory<>("custName"));
        clmMobileNo.setCellValueFactory(new PropertyValueFactory<>("mobileNo"));
        clmFromDate.setCellValueFactory(new PropertyValueFactory<>("fromDate"));
        clmToDate.setCellValueFactory(new PropertyValueFactory<>("toDate"));
        clmModel.setCellValueFactory(new PropertyValueFactory<>("modelName"));
        clmPayment.setCellValueFactory(new PropertyValueFactory<>("amount"));
        clmBookingDate.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));
    }

    //Update Button Actions
    public void updateBtnClickAction(ActionEvent actionEvent) {
        BookingDTO selectedItem = (BookingDTO) tblAllBookings.getSelectionModel().getSelectedItem();

        bookingFormController.setUpdateBooking(selectedItem);
        bookingFormController.btnBook.setText("Update");

        Stage stage = (Stage) btnUpdate.getScene().getWindow();
        stage.close();
    }

    public void updateBtnEnter(MouseEvent mouseEvent) {
        btnUpdate.setStyle("-fx-background-color: "+"#45aaf2");
    }

    public void updateBtnExit(MouseEvent mouseEvent) {
        btnUpdate.setStyle("-fx-background-color: "+"#2d98da");
    }

    
}
