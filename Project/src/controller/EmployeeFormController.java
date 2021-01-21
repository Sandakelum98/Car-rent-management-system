package controller;

import dto.UserDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class EmployeeFormController {

    public AnchorPane context;
    public Pane paneDashboard;
    public Pane paneCustomer;
    public Pane paneVehicles;
    public Pane paneBooking;
    public Pane paneIssue;
    public Pane paneObtain;
    public Label lblUserName;
    public ImageView imgLogout;
    public AnchorPane anchorPaneMain;

    public static UserDTO userDTO;
    public ImageView imgReports;
    public Label lblReports;

    //START-------------------------------------------------------------------------------------------------------------
    public void initialize() throws IOException {
        loadDefault();
        imgReports.setVisible(false);
        lblReports.setVisible(false);
    }

    private void loadDefault() throws IOException {
        setUi("EmployeeDashboard");
    }
    //------------------------------------------------------------------------------------------------------------------

    //Method FOR ALL
    public void setUi(String location) throws IOException {
        context.getChildren().clear();
        context.getChildren().add(FXMLLoader.
                load(this.getClass().
                        getResource("/view/" + location + ".fxml")));
    }


    //DashBoard Pane Action events--------------------------------------------------------------------------------------
    public void dashboardPaneClickAction(MouseEvent mouseEvent) throws IOException {
        setUi("EmployeeDashboard");
    }

    public void dashboardMouseEnter(MouseEvent mouseEvent) {
        String enteredByUser = "#40739e";
        paneDashboard.setStyle("-fx-background-color:"+enteredByUser);
    }

    public void dashboardMouseExit(MouseEvent mouseEvent) {

        String enteredByUser = "#4b6584";
        paneDashboard.setStyle("-fx-background-color:"+enteredByUser);
    }
    //------------------------------------------------------------------------------------------------------------------


    //Customer Pane Action events---------------------------------------------------------------------------------------
    public void customerPaneClickAction(MouseEvent mouseEvent) throws IOException {
        //setUi("CustomerForm");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/CustomerForm.fxml"));
        CustomerFormController cs = fxmlLoader.getController();
        cs.getDoneButton(false);
        context.getChildren().clear();
        context.getChildren().add(fxmlLoader.load(this.getClass().
                getResource("/view/CustomerForm.fxml")));
    }

    public void customerMouseEnter(MouseEvent mouseEvent) {
        String enteredByUser = "#40739e";
        paneCustomer.setStyle("-fx-background-color: "+enteredByUser);
    }

    public void customerMouseExit(MouseEvent mouseEvent) {
        String enteredByUser = "#4b6584";
        paneCustomer.setStyle("-fx-background-color: "+enteredByUser);
    }
    //------------------------------------------------------------------------------------------------------------------


    //Vehicle Pane Action Events----------------------------------------------------------------------------------------
    public void vehiclePaneClickAction(MouseEvent mouseEvent) throws IOException {
        setUi("VehicleForm");
    }

    public void vehicleMouseEnter(MouseEvent mouseEvent) {
        paneVehicles.setStyle("-fx-background-color: "+"#40739e");
    }

    public void vehicleMouseExit(MouseEvent mouseEvent) {
        paneVehicles.setStyle("-fx-background-color: "+" #4b6584");
    }
    //------------------------------------------------------------------------------------------------------------------


    //Booking Pane Action Events----------------------------------------------------------------------------------------
    public void bookingPaneClickAction(MouseEvent mouseEvent) throws IOException {
        setUi("BookingForm");
    }

    public void bookingMouseEnter(MouseEvent mouseEvent) {
        paneBooking.setStyle("-fx-background-color: "+"#40739e");
    }

    public void bookingMouseExit(MouseEvent mouseEvent) {
        paneBooking.setStyle("-fx-background-color: "+" #4b6584");
    }
    //------------------------------------------------------------------------------------------------------------------


    //Issue Pane Action Events------------------------------------------------------------------------------------------
    public void issuePaneClickAction(MouseEvent mouseEvent) throws IOException {
        setUi("IssueForm");
    }

    public void issuePaneEnter(MouseEvent mouseEvent) {
        paneIssue.setStyle("-fx-background-color: "+"#40739e");
    }

    public void issuePaneExit(MouseEvent mouseEvent) {
        paneIssue.setStyle("-fx-background-color: "+" #4b6584");
    }


    //Obtain Pane Action Events-----------------------------------------------------------------------------------------
    public void obtainPaneClickAction(MouseEvent mouseEvent) throws IOException {
        setUi("ReturnForm");
    }

    public void obtainPaneEnter(MouseEvent mouseEvent) {
        paneObtain.setStyle("-fx-background-color: "+"#40739e");
    }

    public void obtainPaneExit(MouseEvent mouseEvent) {
        paneObtain.setStyle("-fx-background-color: "+" #4b6584");
    }


    //Report Pane Actions
    public void reportPaneClickAction(MouseEvent mouseEvent) throws IOException {
        //setUi("AddCustomerForm");
    }

    public void setUser(UserDTO userDTO) {
        this.userDTO = userDTO;
        lblUserName.setText(userDTO.getUsername()+" - "+userDTO.getUserType());
    }


    //Logout
    public void logoutAction(MouseEvent mouseEvent) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "You want to Log Out ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            Stage stage1 = (Stage) imgLogout.getScene().getWindow();
            stage1.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/LoginForm.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(root));
            stage2.show();        }



    }

}
