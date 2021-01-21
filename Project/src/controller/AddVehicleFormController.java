package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import bo.custom.VehicleBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDTO;
import dto.VehicleDTO;
import dto.VehicleMakeDTO;
import dto.VehicleModelDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AddVehicleFormController {
    public JFXButton btnCancel;
    public JFXTextField txtVehicleImage;
    public JFXComboBox cmbVehicleMake;
    public JFXButton btnChooseImage;
    public JFXButton btnChoosePriceCategory;
    public JFXButton btnSave;
    public JFXComboBox cmbVehicleModel;
    public JFXTextField txtVehiclePriceCategory;
    public JFXTextField txtVehicleNo;
    public JFXTextField txtVehicleYear;
    public JFXTextField txtVehicleColour;

    private int priceCategoryId;
    private ArrayList<VehicleMakeDTO> vehicleMakeDTO;
    private ArrayList<VehicleModelDTO> vehicleModelDTOS;
    private String selectedMake;
    private VehicleFormController vehicleFormController;

    VehicleBO vehicleBO= (VehicleBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.VEHICLE);

    //Start
    public void initialize(){
        loadVehicleMake();
    }


    //Methods-----------------------------------------------------------------------------------------------------------
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


    //Image Button Action Events----------------------------------------------------------------------------------------
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
    //------------------------------------------------------------------------------------------------------------------


    //Price category choose button Action Events------------------------------------------------------------------------
    public void categoryBtnClickAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ChoosePriceCategory.fxml"));
            Parent root = (Parent) fxmlLoader.load();

            ChoosePriceCategoryController controller = fxmlLoader.getController();
            controller.setParentFormAdd(this);

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
    //------------------------------------------------------------------------------------------------------------------


    //Save Button Action Events-----------------------------------------------------------------------------------------
    public void saveButtonAction(ActionEvent actionEvent) {

        if(cmbVehicleMake.getSelectionModel().getSelectedItem()==null){
            new Alert(Alert.AlertType.WARNING,"Please select Vehicle Make !").show();
            cmbVehicleMake.setFocusColor(Paint.valueOf("red"));
            cmbVehicleMake.requestFocus();
            return;
        }
        if(cmbVehicleModel.getSelectionModel().getSelectedItem()==null){
            new Alert(Alert.AlertType.WARNING,"Please select Vehicle Model !").show();
            cmbVehicleModel.setFocusColor(Paint.valueOf("red"));
            cmbVehicleModel.requestFocus();
            return;
        }
        if(txtVehicleNo.getText().length()==0){
            new Alert(Alert.AlertType.WARNING,"Please enter Vehicle Number !").show();
            txtVehicleNo.setFocusColor(Paint.valueOf("red"));
            txtVehicleNo.requestFocus();
            return;
        }
        if(txtVehicleYear.getText().length()==0){
            new Alert(Alert.AlertType.WARNING,"Please enter vehicle year !").show();
            txtVehicleYear.setFocusColor(Paint.valueOf("red"));
            txtVehicleYear.requestFocus();
            return;
        }
        if(txtVehicleColour.getText().length()==0){
            new Alert(Alert.AlertType.WARNING,"Please enter Vehicle Colour !").show();
            txtVehicleColour.setFocusColor(Paint.valueOf("red"));
            txtVehicleColour.requestFocus();
            return;
        }
        if(txtVehiclePriceCategory.getText().length()==0){
            new Alert(Alert.AlertType.WARNING,"Please select price category !").show();
            txtVehiclePriceCategory.setFocusColor(Paint.valueOf("red"));
            txtVehiclePriceCategory.requestFocus();
            return;
        }
        if(!Pattern.compile("[0-9]{4}").matcher(txtVehicleYear.getText()).matches()){
            new Alert(Alert.AlertType.WARNING,"Invalid vehicle year !").show();
            txtVehicleYear.requestFocus();
            return;
        }
        if(!Pattern.compile("[A-z]").matcher(txtVehicleColour.getText()).matches()){
            new Alert(Alert.AlertType.WARNING,"Invalid vehicle colour !").show();
            txtVehicleColour.requestFocus();
            return;
        }

        //get make id
        int makeId = 0;
        for (VehicleMakeDTO v : vehicleMakeDTO) {
            String make = v.getMakeName();
            makeId =  v.getMakeId();

            if(selectedMake.equals(make)){
                break;
            }
        }

        //get model id
        String selectedModel = (String) cmbVehicleModel.getSelectionModel().getSelectedItem();
        int modelId = 0;
        for (VehicleModelDTO v: vehicleModelDTOS) {
            String model = v.getModelName();
            modelId = v.getModelId();

            if(selectedModel.equals(model)){
                break;
            }
        }

        String vehicleNo = txtVehicleNo.getText();
        int year = Integer.parseInt(txtVehicleYear.getText());
        String colour = txtVehicleColour.getText();
        String image = txtVehicleImage.getText();

        VehicleDTO vehicleDTO = new VehicleDTO(priceCategoryId, makeId, modelId, vehicleNo, year, colour);

        try {
            boolean isAdded = vehicleBO.addVehicle(vehicleDTO);
            if(isAdded){
                boolean imageAdded = saveImage(image,vehicleNo);
                if(imageAdded) {
                    JOptionPane.showMessageDialog(null, "Saved");
                    vehicleFormController.loadAllVehicleDetails();
                    Stage stage = (Stage) btnSave.getScene().getWindow();
                    stage.close();
                }else{
                    JOptionPane.showMessageDialog(null, "Failed");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void saveBtnEnter(MouseEvent mouseEvent) {
        btnSave.setStyle("-fx-background-color: "+"#45aaf2");
    }

    public void saveBtnExit(MouseEvent mouseEvent) {
        btnSave.setStyle("-fx-background-color: "+"#2d98da");
    }
    //------------------------------------------------------------------------------------------------------------------


    //Cancel Button Action Events---------------------------------------------------------------------------------------
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

    public void makeComboAction(ActionEvent actionEvent) throws Exception {
        this.selectedMake = (String) cmbVehicleMake.getSelectionModel().getSelectedItem();

        this.vehicleModelDTOS = vehicleBO.getModels(selectedMake);
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for (VehicleModelDTO v : vehicleModelDTOS) {
            observableList.add(v.getModelName());
        }
        cmbVehicleModel.setItems(observableList);
    }

    public void addPriceCategory(int priceCategoryID, String name){
        this.priceCategoryId = priceCategoryID;
        this.txtVehiclePriceCategory.setText(name);
    }

    private static boolean saveImage(String image, String vehicleNo) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(image));//change path to where file is located
            int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
            BufferedImage resizedImage = new BufferedImage(400, 400, type);
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(originalImage, 0, 0, 400, 400, null);
            g.dispose();
            boolean isSave = ImageIO.write(resizedImage, "jpg", new File("vehicle images\\" + vehicleNo + ".jpg"));//change path where you want it saved
            return isSave;
        } catch (IOException e) {
            return false;
        }
    }

    public void getParentUi(VehicleFormController vehicleFormController) {
        this.vehicleFormController = vehicleFormController;
    }
}
