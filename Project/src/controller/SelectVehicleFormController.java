package controller;

import bo.BOFactory;
import bo.custom.SelectVehicleBO;
import com.jfoenix.controls.JFXButton;
import dto.SelectVehicleDTO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SelectVehicleFormController {

    public TableView tblAvailableVehicle;
    public TableColumn clmId;
    public TableColumn clmMake;
    public TableColumn clmModel;
    public TableColumn clmVehicleNo;
    public TableColumn clmYear;
    public TableColumn clmColour;
    public TableColumn clmDefaultRate;
    public TableColumn clmKm;
    public TableColumn clmAddingRate;
    public JFXButton btnDone;

    SelectVehicleDTO selectVehicleDTO;
    BookingFormController bookingFormController;
    SelectVehicleBO selectVehicleBO = (SelectVehicleBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SELECTVEHICLE);

    public void initialize() throws Exception {
    }

    public void searchAvailableVehicles(SelectVehicleDTO selectVehicleDTO, BookingFormController bookingFormController) throws Exception {
        this.selectVehicleDTO = selectVehicleDTO;
        this.bookingFormController = bookingFormController;
        
        loadAvailableVehicles();
    }

    private void loadAvailableVehicles() throws Exception {
        ArrayList<SelectVehicleDTO> availableVehicles = selectVehicleBO.getAvailableVehicles(selectVehicleDTO);
        tblAvailableVehicle.setItems(FXCollections.observableArrayList(availableVehicles));

        clmMake.setCellValueFactory(new PropertyValueFactory<>("makeName"));
        clmModel.setCellValueFactory(new PropertyValueFactory<>("modelName"));
        clmVehicleNo.setCellValueFactory(new PropertyValueFactory<>("vehicleNo"));
        clmYear.setCellValueFactory(new PropertyValueFactory<>("vehicleYear"));
        clmColour.setCellValueFactory(new PropertyValueFactory<>("vehicleColour"));
        clmDefaultRate.setCellValueFactory(new PropertyValueFactory<>("defaultRate"));
        clmKm.setCellValueFactory(new PropertyValueFactory<>("km"));
        clmAddingRate.setCellValueFactory(new PropertyValueFactory<>("addingRate"));
    }


    //Done Button Actions
    public void doneBtnClickAction(ActionEvent actionEvent) {
        SelectVehicleDTO selectedVehicle = (SelectVehicleDTO) tblAvailableVehicle.getSelectionModel().getSelectedItem();
        if(selectedVehicle==null){
            new Alert(Alert.AlertType.WARNING,"Please select Vehicle !").show();
            return;
        }
        ArrayList<SelectVehicleDTO> selectedVehicleArray = new ArrayList<>();
        selectedVehicleArray.add(new SelectVehicleDTO(
                        selectedVehicle.getVehicleId(),
                        selectedVehicle.getVehicleNo(),
                        selectedVehicle.getVehicleYear(),
                        selectedVehicle.getVehicleColour(),
                        selectedVehicle.getMakeName(),
                        selectedVehicle.getModelName(),
                        selectedVehicle.getDefaultRate(),
                        selectedVehicle.getKm(),
                        selectedVehicle.getAddingRate(),
                        selectedVehicle.getFromDate(),
                        selectedVehicle.getToDate()
                ));

        bookingFormController.getSelectedVehicle(selectedVehicleArray);

        Stage stage = (Stage) btnDone.getScene().getWindow();
        stage.close();
    }

    public void doneBtnEnter(MouseEvent mouseEvent) {
        btnDone.setStyle("-fx-background-color: " + "#45aaf2");
    }

    public void doneBtnExit(MouseEvent mouseEvent) {
        btnDone.setStyle("-fx-background-color: " + "#2d98da");
    }
}
