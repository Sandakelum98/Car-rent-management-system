package controller;

import bo.BOFactory;
import bo.custom.PriceCategoryBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.PriceCategoryDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddPriceCategoryFormController {
    public JFXTextField txtCategoryName;
    public JFXTextField txtDefaultRate;
    public JFXTextField txtKm;
    public JFXTextField txtAddingRate;
    public JFXButton btnSave;
    public JFXButton btnCancel;

    ChoosePriceCategoryController choosePriceCategoryController;

    PriceCategoryBO priceCategoryBO = (PriceCategoryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PRICECATEGORY);


    //Focus
    public void categoryNameTxtAction(ActionEvent actionEvent) {
        txtDefaultRate.requestFocus();
    }

    public void defaultRateTxtAction(ActionEvent actionEvent) {
        txtKm.requestFocus();
    }

    public void kmTxtAction(ActionEvent actionEvent) {
        txtAddingRate.requestFocus();
    }

    public void addingRateTxtAction(ActionEvent actionEvent) throws Exception {
        saveBtnClickAction(actionEvent);
    }

    //Method
    public void getParent(ChoosePriceCategoryController choosePriceCategoryController){
        this.choosePriceCategoryController = choosePriceCategoryController;
    }


    //Save Button Actions
    public void saveBtnClickAction(ActionEvent actionEvent) throws Exception {
        String cateName = txtCategoryName.getText();
        double defaultRate = Double.parseDouble(txtDefaultRate.getText());
        int km = Integer.parseInt(txtKm.getText());
        double addingRate = Double.parseDouble(txtAddingRate.getText());

        PriceCategoryDTO priceCategoryDTO = new PriceCategoryDTO(cateName, defaultRate, km, addingRate);
        boolean isAdded = priceCategoryBO.addPriceCategory(priceCategoryDTO);
        if(isAdded){
            new Alert(Alert.AlertType.INFORMATION,"Saved!").show();
            Stage stage = (Stage) btnSave.getScene().getWindow();
            stage.close();
            choosePriceCategoryController.loadAllPriceCategory();
        }else{
            new Alert(Alert.AlertType.INFORMATION,"Failed!").show();
        }

    }

    public void saveBtnEnter(MouseEvent mouseEvent) {
        btnSave.setStyle("-fx-background-color: "+"#45aaf2");
    }

    public void saveBtnExit(MouseEvent mouseEvent) {
        btnSave.setStyle("-fx-background-color: "+"#2d98da");
    }


    //Cancel Button Actions
    public void cancelBtnClickAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void cancelBtnEnter(MouseEvent mouseEvent) {
        btnCancel.setStyle("-fx-background-color: "+"#45aaf2");
    }

    public void cancelBtnExit(MouseEvent mouseEvent) {
        btnCancel.setStyle("-fx-background-color: "+"#2d98da");
    }
}
