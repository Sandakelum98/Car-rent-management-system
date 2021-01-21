package controller;

import bo.BOFactory;
import bo.custom.UserBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.custom.impl.UserDAOImpl;
import dto.UserDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import entity.User;
import javafx.event.ActionEvent;
import sun.management.BaseOperatingSystemImpl;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {
    public JFXTextField txtPassword;
    public JFXTextField txtUsername;
    public JFXButton btnLogin;
    public AnchorPane root;
    public ImageView imgView;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public void LoginOnAction(ActionEvent actionEvent) throws Exception {
        String username=txtUsername.getText().trim();
        String password=txtPassword.getText().trim();
        boolean check=true;

        if(username.length()==0 && password.length()==0){
            JOptionPane.showMessageDialog(null, "Please enter Username and Password");
            check=false;
        }else if(username.length()==0 ){
            JOptionPane.showMessageDialog(null, "Please enter Username");
            check=false;
        }else if(password.length()==0 ){
            JOptionPane.showMessageDialog(null, "Please enter Password");
            check=false;
        }

        if(check) {
            UserDTO userDTO = new UserDTO(username, password);

            try {
                userDTO = userBO.login(userDTO);
                if (userDTO != null) {

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/EmployeeForm.fxml"));
                    Parent root = (Parent) fxmlLoader.load();


                    EmployeeFormController controller = fxmlLoader.getController();
                    controller.setUser(userDTO);

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();

                    Stage stage1 = (Stage) btnLogin.getScene().getWindow();
                    stage1.close();

                } else {
                    JOptionPane.showMessageDialog(null, "username or password incorrect");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setFocus(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }

    public void loginEnter(ActionEvent actionEvent) throws Exception {
        LoginOnAction(actionEvent);
    }
}
