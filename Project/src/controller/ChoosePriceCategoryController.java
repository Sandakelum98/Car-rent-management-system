package controller;

import bo.BOFactory;
import bo.custom.PriceCategoryBO;
import com.jfoenix.controls.JFXButton;
import dto.PriceCategoryDTO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ChoosePriceCategoryController {

    public JFXButton btnAddNewCategory;
    public JFXButton btnSaveNewCategory;
    public JFXButton btnDone;
    public TableView tblPriceCategory;
    public TableColumn clmId;
    public TableColumn clmDefaultRate;
    public TableColumn clmCateName;
    public TableColumn clmKm;
    public TableColumn clmAddingRate;

    private AddVehicleFormController addVehicleFormController;
    private VehicleUpdateFormController vehicleUpdateFormController;

    //Property Injections
    PriceCategoryBO priceCategoryBO = (PriceCategoryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PRICECATEGORY);


    //Initialize
    public void initialize() throws Exception {
        loadAllPriceCategory();
    }

    //Methods-----------------------------------------------------------------------------------------------------------
    public void loadAllPriceCategory() throws Exception {
        clmId.setCellValueFactory(new PropertyValueFactory<>("priceCategoryId"));
        clmCateName.setCellValueFactory(new PropertyValueFactory<>("priceCategoryName"));
        clmDefaultRate.setCellValueFactory(new PropertyValueFactory<>("defaultRate"));
        clmKm.setCellValueFactory(new PropertyValueFactory<>("km"));
        clmAddingRate.setCellValueFactory(new PropertyValueFactory<>("addingRate"));

        ArrayList<PriceCategoryDTO> allPriceCategory = priceCategoryBO.getAllPriceCate();
        tblPriceCategory.setItems(FXCollections.observableArrayList(allPriceCategory));
    }

    public void setParentFormAdd(AddVehicleFormController addVehicleFormController) {
        this.addVehicleFormController=addVehicleFormController;
    }

    public void setParentFormUpdate(VehicleUpdateFormController vehicleUpdateFormController) {
        this.vehicleUpdateFormController=vehicleUpdateFormController;
    }
    //------------------------------------------------------------------------------------------------------------------

    //Add New Category Button Action Events
    public void addCategoryBtnClickAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AddPriceCategoryForm.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        AddPriceCategoryFormController controller = fxmlLoader.getController();
        controller.getParent(this);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void addCategoryBtnEnter(MouseEvent mouseEvent) {
        btnAddNewCategory.setStyle("-fx-background-color: "+"#45aaf2");
    }

    public void addCategoryBtnExit(MouseEvent mouseEvent) {
        btnAddNewCategory.setStyle("-fx-background-color: "+"#2d98da");
    }


    //Done Button Action Events
    public void doneBtnClickAction(ActionEvent actionEvent) {
        PriceCategoryDTO pcd = (PriceCategoryDTO) tblPriceCategory.getSelectionModel().getSelectedItem();

        addVehicleFormController.addPriceCategory(pcd.getPriceCategoryId(),pcd.getPriceCategoryName());
        //vehicleUpdateFormController.addPriceCategory(pcd.getPriceCategoryId(),pcd.getPriceCategoryName());

        Stage stage = (Stage) btnDone.getScene().getWindow();
        stage.close();
    }

    public void doneBtnEnter(MouseEvent mouseEvent) {
        btnDone.setStyle("-fx-background-color: "+"#45aaf2");
    }

    public void doneBtnExit(MouseEvent mouseEvent) {
        btnDone.setStyle("-fx-background-color: "+"#2d98da");
    }

}
