package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.regex.Pattern;

public class AddCustomerFormController {
    public JFXTextField txtNIC;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtMobileNo;
    public JFXButton btnSave;
    public JFXButton btnCancel;


    CustomerFormController customerFormController;

    //property Injection
    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    //Focus
    public void nicTextAction(ActionEvent actionEvent) {
        txtName.requestFocus();
    }
    public void nameTextAction(ActionEvent actionEvent) {
        txtAddress.requestFocus();
    }
    public void addressTextAction(ActionEvent actionEvent) {
        txtMobileNo.requestFocus();
    }
    public void mobileNoTextAction(ActionEvent actionEvent) throws Exception {
        saveButtonAction(actionEvent);
    }
    
    
    //Save Button Actions
    public void saveButtonAction(ActionEvent actionEvent) throws Exception {

        if(txtNIC.getText().length()==0){
            new Alert(Alert.AlertType.WARNING,"Please enter NIC !").show();
            txtNIC.setFocusColor(Paint.valueOf("red"));
            txtNIC.requestFocus();
            return;
        }
        if (txtName.getText().length()==0){
            new Alert(Alert.AlertType.WARNING,"Please enter Name !").show();
            txtName.setFocusColor(Paint.valueOf("red"));
            txtName.requestFocus();
            return;
        }
        if(txtMobileNo.getText().length()==0){
            new Alert(Alert.AlertType.WARNING,"Please enter Mobile No !").show();
            txtMobileNo.setFocusColor(Paint.valueOf("red"));
            txtMobileNo.requestFocus();
            return;
        }
        if(!Pattern.compile("[0-9]{9}(V){1}").matcher(txtNIC.getText()).matches()){
            new Alert(Alert.AlertType.WARNING,"Invalid NIC number !").show();
            txtNIC.requestFocus();
            return;
        }
        if(!Pattern.compile("[A-Z]{1}[a-z]{1,} [A-Z]{1}[a-z]{1,}").matcher(txtName.getText()).matches()){
            new Alert(Alert.AlertType.WARNING,"Invalid name type !").show();
            txtName.requestFocus();
            return;
        }
        if(!Pattern.compile("(0){1}[0-9]{9}").matcher(txtMobileNo.getText()).matches()){
            new Alert(Alert.AlertType.WARNING,"Invalid Mobile Number !").show();
            txtMobileNo.requestFocus();
            return;
        }

        String custNIC = txtNIC.getText();
        String custName = txtName.getText();
        String custAddress = txtAddress.getText();
        String mobileNo = txtMobileNo.getText();


        CustomerDTO customerDTO = new CustomerDTO(custNIC,custName,custAddress,mobileNo);

        boolean isAdded = customerBO.addCustomer(customerDTO);
        if(isAdded){
            new Alert(Alert.AlertType.WARNING,"Saved").show();
            customerFormController.loadAllCustomers();
            Stage stage = (Stage) btnSave.getScene().getWindow();
            stage.close();
        }else{
            new Alert(Alert.AlertType.WARNING,"Not Saved").show();
        }
    }

    public void saveBtnEnter(MouseEvent mouseEvent) {
    }

    public void saveBtnExit(MouseEvent mouseEvent) {
    }
    
    //Cancel Button Actions
    public void cancelButtonAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();

    }

    public void cancelBtnEnter(MouseEvent mouseEvent) {
    }

    public void cancelBtnExit(MouseEvent mouseEvent) {
    }


    //Methods
    public void getParentUi(CustomerFormController customerFormController) {
        this.customerFormController = customerFormController;
    }
}
