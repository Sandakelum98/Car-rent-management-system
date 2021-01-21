package controller;

import bo.BOFactory;
import bo.custom.VehicleBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.VehicleDetailsDTO;
import dto.VehicleMakeDTO;
import dto.VehicleModelDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VehicleUpdateFormController {
    public JFXTextField txtVehicleNo;
    public JFXButton btnUpdate;
    public JFXButton btnCancel;
    public JFXButton btnChooseImage;
    public JFXComboBox cmbVehicleMake;
    public JFXComboBox cmbVehicleModel;
    public JFXTextField txtVehicleYear;
    public JFXTextField txtVehicleColour;
    public JFXTextField txtVehicleImage;
    public JFXTextField txtVehiclePriceCategory;
    public JFXButton btnChoosePriceCategory;
    public JFXComboBox cmbActiveStatus;

    private int priceCategoryId;
    ArrayList<VehicleMakeDTO> vehicleMakeDTO;
    ArrayList<VehicleModelDTO> vehicleModelDTOS;
    String selectedMake;


    VehicleBO vehicleBO= (VehicleBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.VEHICLE);
    ObservableList<String> ActiveStatus = FXCollections.observableArrayList("Active", "Inactive");


    //Start
    public void initialize(){
        loadVehicleMake();
        cmbActiveStatus.setItems(ActiveStatus);
    }

    private void loadVehicleMake() {
        try {
            this.vehicleMakeDTO =vehicleBO.getAllMake();
            ObservableList<String> observableList= FXCollections.observableArrayList();
            for (VehicleMakeDTO v:vehicleMakeDTO) {
                observableList.add(v.getMakeName());
            }
            cmbVehicleMake.setItems(observableList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void makeComboAction(ActionEvent actionEvent) throws Exception {
        this.selectedMake = (String) cmbVehicleMake.getSelectionModel().getSelectedItem();
        getModelByMakeName(selectedMake);
    }

    public void getModelByMakeName(String selectedMake) throws Exception {
        this.vehicleModelDTOS = vehicleBO.getModels(selectedMake);
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for (VehicleModelDTO v : vehicleModelDTOS) {
            observableList.add(v.getModelName());
        }
        cmbVehicleModel.setItems(observableList);
    }

    //Image Button Actions
    public void imageBtnClickAction(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("image Files", "*.jpg", "*.PNG"));
        List<File> f = fc.showOpenMultipleDialog(null);
        for (File file : f){
            txtVehicleImage.setText(file.getAbsolutePath());
        }
    }

    public void imageBtnEnter(MouseEvent mouseEvent) {
        btnChooseImage.setStyle("-fx-background-color: "+"#45aaf2");
    }

    public void imageBtnExit(MouseEvent mouseEvent) {
        btnChooseImage.setStyle("-fx-background-color: "+"#2d98da");
    }


    //Price Category Button Actions
    public void categoryBtnClickAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ChoosePriceCategory.fxml"));
            Parent root = (Parent) fxmlLoader.load();

            ChoosePriceCategoryController controller = fxmlLoader.getController();
            controller.setParentFormUpdate(this);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void categoryBtnEnter(MouseEvent mouseEvent) {
        btnChoosePriceCategory.setStyle("-fx-background-color: "+"#45aaf2");
    }

    public void categoryBtnExit(MouseEvent mouseEvent) {
        btnChoosePriceCategory.setStyle("-fx-background-color: "+"#2d98da");
    }


    //Update Button Actions
    public void updateButtonAction(ActionEvent actionEvent) {
    }

    public void updateBtnEnter(MouseEvent mouseEvent) {
        btnUpdate.setStyle("-fx-background-color: "+"#45aaf2");
    }

    public void updateBtnExit(MouseEvent mouseEvent) {
        btnUpdate.setStyle("-fx-background-color: "+"#2d98da");
    }


    //Cancel Button Actions
    public void cancelButtonAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void cancelBtnEnter(MouseEvent mouseEvent) {
        btnCancel.setStyle("-fx-background-color: "+"#45aaf2");
    }

    public void cancelBtnExit(MouseEvent mouseEvent) {
        btnCancel.setStyle("-fx-background-color: "+"#2d98da");
    }

    //Controllers
    public void addPriceCategory(int priceCategoryID, String name){
        this.priceCategoryId = priceCategoryID;
        this.txtVehiclePriceCategory.setText(name);
    }

    public void getUpdateVehicle(VehicleDetailsDTO v) throws Exception {
        cmbVehicleMake.setValue(v.getMakeName());
        getModelByMakeName(v.getMakeName());
        cmbVehicleModel.setValue(v.getModelName());
        txtVehicleNo.setText(v.getVehicleNo());
        txtVehicleYear.setText(String.valueOf(v.getVehicleYear()));
        txtVehicleColour.setText(v.getColour());
        txtVehiclePriceCategory.setText(v.getPriceCateName());
        cmbActiveStatus.setValue(v.getIsActiveName());
    }
}
