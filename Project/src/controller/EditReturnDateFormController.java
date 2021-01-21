package controller;

import bo.BOFactory;
import bo.custom.ReturnBO;
import com.jfoenix.controls.JFXButton;
import dao.DAOFactory;
import dao.custom.ReservationDaysDAO;
import dto.ReturnDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditReturnDateFormController {
    public JFXButton btnDone;
    public JFXButton btnCancel;
    public TextField txtToDate;
    public TextField txtFromDate;
    public DatePicker pickerNewDate;

    int resDetailId;
    String fromDate;
    String toDate;
    ReturnFormController returnFormController;

    ReturnBO returnBO = (ReturnBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RETURN);


    public void getParent(int resDetailId, String fromDate, String toDate, ReturnFormController returnFormController){
        this.resDetailId = resDetailId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.returnFormController = returnFormController;

        txtFromDate.setText(fromDate);
        txtToDate.setText(toDate);
    }


    //Done Button Actions
    public void doneBtnClickAction(ActionEvent actionEvent) throws Exception {
        String newReturnDate = pickerNewDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        ReturnDTO returnDTO = new ReturnDTO(resDetailId, fromDate, newReturnDate);

        boolean isAdded = returnBO.editReturnDate(returnDTO);
        if(isAdded){
            new Alert(Alert.AlertType.WARNING,"Changed!").show();
            returnFormController.searchBtnClickAction(actionEvent);
        }else{
            new Alert(Alert.AlertType.WARNING,"Failed!").show();
        }

        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();

    }

    public void doneBtnEnter(MouseEvent mouseEvent) {
        btnDone.setStyle("-fx-background-color: " + "#45aaf2");
    }

    public void doneBtnExit(MouseEvent mouseEvent) {
        btnDone.setStyle("-fx-background-color: " + "#2d98da");
    }


    //Cancel Button Actions
    public void cancelBtnClickAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void cancelBtnEnter(MouseEvent mouseEvent) {
    }

    public void cancelBtnExit(MouseEvent mouseEvent) {
    }
}
