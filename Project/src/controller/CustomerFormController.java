package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDTO;
import entity.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tm.CustomerTM;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.ArrayList;

public class CustomerFormController {

    public JFXButton btnAddCustomer;
    public TableView tblCustomers;
    public TableColumn clmNic;
    public TableColumn clmName;
    public TableColumn clmAddress;
    public TableColumn clmMobileNo;
    public TableColumn clmId;
    public JFXTextField txtSearchCustomer;
    public TableColumn clmAction;
    public JFXButton btnDone;

    static BookingFormController bookingFormController;
    public static boolean btnGet = false;
    public JFXButton btnUpdate;

    //Property Injections
    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    //Initialize
    public void initialize() throws Exception {
        loadAllCustomers();
        btnDone.setVisible(btnGet);
    }

    public static void getDoneButton(boolean btnVisibility) {
        btnGet = btnVisibility;
    }

    public static void getParentDetails(BookingFormController form){
        bookingFormController = form;
    }

    //load All Customers
    public void loadAllCustomers() throws Exception {

        clmId.setCellValueFactory(new PropertyValueFactory<>("custID"));
        clmNic.setCellValueFactory(new PropertyValueFactory<>("custNIC"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("custName"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<>("custAddress"));
        clmMobileNo.setCellValueFactory(new PropertyValueFactory<>("mobileNo"));

        ArrayList<CustomerDTO> allCustomers = customerBO.getAllCustomers();
        tblCustomers.setItems(FXCollections.observableArrayList(allCustomers));
    }

    //Add Customer Button Actions---------------------------------------------------------------------------------------
    public void addCustomerAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AddCustomerForm.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            AddCustomerFormController controller = fxmlLoader.getController();
            controller.getParentUi(this);

            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addButtonEnter(MouseEvent mouseEvent) {
        String enteredColour = "#45aaf2";
        btnAddCustomer.setStyle("-fx-background-color:" + enteredColour);
    }

    public void addButtonExit(MouseEvent mouseEvent) {
        btnAddCustomer.setStyle("-fx-background-color: " + "#2d98da");
    }
    //------------------------------------------------------------------------------------------------------------------


    //Controller
    public void getAllCustomers() throws Exception {
        ArrayList<CustomerDTO> allCustomers = customerBO.getAllCustomers();
    }

    @FXML
    void searchCustomerTxtActionKeyReleased(KeyEvent event) {
        String value = txtSearchCustomer.getText();
        try {
            ArrayList<CustomerDTO> searchCustomers = customerBO.searchCustomers(value);
           /* ArrayList<CustomerTM> customerTMS = new ArrayList<>();
            for (CustomerDTO c : searchCustomers) {
                Button btn = new Button("Update");
                CustomerTM customerTM = new CustomerTM(
                        c.getCustID(),
                        c.getCustNIC(),
                        c.getCustName(),
                        c.getCustAddress(),
                        c.getMobileNo(),
                        btn
                );
                customerTMS.add(customerTM);
                btn.setOnAction((e) -> {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AddCustomerForm.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root1));
                        stage.show();
                    } catch (IOException x) {
                        x.printStackTrace();
                    }

                });
            }*/
            tblCustomers.setItems(FXCollections.observableArrayList(searchCustomers));

            clmId.setCellValueFactory(new PropertyValueFactory<>("custID"));
            clmNic.setCellValueFactory(new PropertyValueFactory<>("custNIC"));
            clmName.setCellValueFactory(new PropertyValueFactory<>("custName"));
            clmAddress.setCellValueFactory(new PropertyValueFactory<>("custAddress"));
            clmMobileNo.setCellValueFactory(new PropertyValueFactory<>("mobileNo"));
            clmAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
        } catch (Exception x) {
            x.printStackTrace();
        }

    }
    
    
    //Update Button Actions
    public void updateBtnClickAction(ActionEvent actionEvent) throws IOException {
        if(tblCustomers.getSelectionModel().getSelectedItem()==null){
            new Alert(Alert.AlertType.WARNING,"Please select Customer !").show();
            return;
        }
        CustomerDTO selectedItem = (CustomerDTO) tblCustomers.getSelectionModel().getSelectedItem();
        CustomerDTO customerDTO = new CustomerDTO(
                selectedItem.getCustID(),
                selectedItem.getCustNIC(),
                selectedItem.getCustName(),
                selectedItem.getCustAddress(),
                selectedItem.getMobileNo()
        );

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/CustomerUpdateForm.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        CustomerUpdateFormController controller = fxmlLoader.getController();
        controller.updateCustomer(customerDTO,this);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void updateBtnEnter(MouseEvent mouseEvent){
        btnUpdate.setStyle("-fx-background-color:" + "#45aaf2");
    }

    public void updateBtnExit(MouseEvent mouseEvent) {
        btnUpdate.setStyle("-fx-background-color: " + "#2d98da");
    }


    //Done Button Actions
    public void doneBtnClickAction(ActionEvent actionEvent) {
        CustomerDTO selectedCustomer = (CustomerDTO) tblCustomers.getSelectionModel().getSelectedItem();
        if(selectedCustomer==null){
            new Alert(Alert.AlertType.WARNING, "Please select Customer !").show();
            return;
        }
        CustomerDTO selectedCustomerDTO = new CustomerDTO(
                selectedCustomer.getCustID(),
                selectedCustomer.getCustNIC(),
                selectedCustomer.getCustName(),
                selectedCustomer.getCustAddress(),
                selectedCustomer.getMobileNo()
        );
        bookingFormController.getSelectedCustomer(selectedCustomerDTO);

        Stage stage = (Stage) btnDone.getScene().getWindow();
        stage.close();
    }
}
