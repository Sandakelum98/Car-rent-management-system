package controller;

import bo.BOFactory;
import bo.custom.VehicleBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.VehicleDetailsDTO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class VehicleFormController {
    public JFXButton btnAddVehicle;
    public JFXTextField txtSearchVehicle;
    public JFXButton btnUpdate;
    public TableView tblAllVehicleDetails;
    public TableColumn clmId;
    public TableColumn clmMake;
    public TableColumn clmModel;
    public TableColumn clmVehicleNo;
    public TableColumn clmYear;
    public TableColumn clmColour;
    public TableColumn clmDefaultRate;
    public TableColumn clmKm;
    public TableColumn clmAddingRate;
    public TableColumn clmStatus;
    public JFXButton btnViewImage;

    VehicleBO vehicleBO = (VehicleBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.VEHICLE);

    public void initialize() {
        btnViewImage.setVisible(false);
        loadAllVehicleDetails();
    }

    public void loadAllVehicleDetails() {
        try {

            ArrayList<VehicleDetailsDTO> vehicleDetailsDTOS = vehicleBO.getAllVehicleDetails();
            tblAllVehicleDetails.setItems(FXCollections.observableArrayList(vehicleDetailsDTOS));

            clmId.setCellValueFactory(new PropertyValueFactory<>("vehicleId"));
            clmMake.setCellValueFactory(new PropertyValueFactory<>("makeName"));
            clmModel.setCellValueFactory(new PropertyValueFactory<>("modelName"));
            clmVehicleNo.setCellValueFactory(new PropertyValueFactory<>("vehicleNo"));
            clmYear.setCellValueFactory(new PropertyValueFactory<>("vehicleYear"));
            clmColour.setCellValueFactory(new PropertyValueFactory<>("colour"));
            clmDefaultRate.setCellValueFactory(new PropertyValueFactory<>("defaultRate"));
            clmKm.setCellValueFactory(new PropertyValueFactory<>("km"));
            clmAddingRate.setCellValueFactory(new PropertyValueFactory<>("addingRate"));
            clmStatus.setCellValueFactory(new PropertyValueFactory<>("isActiveName"));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Search Action
    public void searchTxtKeyReleaseAction(KeyEvent keyEvent) {
        String value = txtSearchVehicle.getText();
        System.out.println(value);
        try {
            ArrayList<VehicleDetailsDTO> searchVehicleDetails = vehicleBO.searchVehicleDetails(value);
            tblAllVehicleDetails.setItems(FXCollections.observableArrayList(searchVehicleDetails));

            clmId.setCellValueFactory(new PropertyValueFactory<>("vehicleId"));
            clmMake.setCellValueFactory(new PropertyValueFactory<>("makeName"));
            clmModel.setCellValueFactory(new PropertyValueFactory<>("modelName"));
            clmVehicleNo.setCellValueFactory(new PropertyValueFactory<>("vehicleNo"));
            clmYear.setCellValueFactory(new PropertyValueFactory<>("vehicleYear"));
            clmColour.setCellValueFactory(new PropertyValueFactory<>("colour"));
            clmDefaultRate.setCellValueFactory(new PropertyValueFactory<>("defaultRate"));
            clmKm.setCellValueFactory(new PropertyValueFactory<>("km"));
            clmAddingRate.setCellValueFactory(new PropertyValueFactory<>("addingRate"));
            clmStatus.setCellValueFactory(new PropertyValueFactory<>("isActiveName"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Add Vehicle Button Actions
    public void addVehicleAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AddVehicleForm.fxml"));
            Parent root = (Parent) fxmlLoader.load();

            AddVehicleFormController controller = fxmlLoader.getController();
            controller.getParentUi(this);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addButtonEnter(MouseEvent mouseEvent) {
        btnAddVehicle.setStyle("-fx-background-color: "+"#45aaf2");
    }

    public void addButtonExit(MouseEvent mouseEvent) {
        btnAddVehicle.setStyle("-fx-background-color: "+"#2d98da");
    }


    //Update Button Actions
    public void updateBtnClickAction(ActionEvent actionEvent) throws Exception {

        if(tblAllVehicleDetails.getSelectionModel().getSelectedItem()==null){
            new Alert(Alert.AlertType.WARNING,"Please select Vehicle !").show();
            return;
        }

        VehicleDetailsDTO selectedVehicle = (VehicleDetailsDTO) tblAllVehicleDetails.getSelectionModel().getSelectedItem();
        VehicleDetailsDTO vehicleDetailsDTO = new VehicleDetailsDTO(
                selectedVehicle.getMakeId(),
                selectedVehicle.getMakeName(),
                selectedVehicle.getModelId(),
                selectedVehicle.getModelName(),
                selectedVehicle.getVehicleId(),
                selectedVehicle.getVehicleNo(),
                selectedVehicle.getVehicleYear(),
                selectedVehicle.getColour(),
                selectedVehicle.getIsActive(),
                selectedVehicle.getIsActiveName(),
                selectedVehicle.getPriceCateId(),
                selectedVehicle.getPriceCateName(),
                selectedVehicle.getDefaultRate(),
                selectedVehicle.getKm(),
                selectedVehicle.getAddingRate()
        );

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/VehicleUpdateForm.fxml"));
        Parent root = fxmlLoader.load();

        VehicleUpdateFormController controller = fxmlLoader.getController();
        controller.getUpdateVehicle(vehicleDetailsDTO);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void updateBtnEnter(MouseEvent mouseEvent) {
        btnUpdate.setStyle("-fx-background-color: "+"#45aaf2");
    }

    public void updateBtnExit(MouseEvent mouseEvent) {
        btnUpdate.setStyle("-fx-background-color: "+"#2d98da");
    }

    
    //Image View Button Actions
    public void imageBtnAction(ActionEvent actionEvent) {
    }

    public void imageBtnEnter(MouseEvent mouseEvent) {
        btnViewImage.setStyle("-fx-background-color: "+"#45aaf2");
    }

    public void imageBtnExit(MouseEvent mouseEvent) {
        btnViewImage.setStyle("-fx-background-color: "+"#2d98da");
    }


}
