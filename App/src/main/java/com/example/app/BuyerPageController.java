package com.example.app;

import com.example.app.customDesign.BuyerData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class BuyerPageController {
    @FXML
    private Text name;

    @FXML
    private Text email;

    @FXML
    private Button signOutButton;

    @FXML
    void gotoAllItemScreen(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/app/all_item_show_screen.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setHeight(768);
        stage.setWidth(1024);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/styles.css")).toExternalForm());
        stage.show();
    }
    @FXML
    void signOutBuyer(ActionEvent event) throws IOException {
        Parent root = loadFXML();
        Stage stage = (Stage) signOutButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    private Parent loadFXML() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("welcome_screen.fxml"));
        return loader.load();
    }
    public void setBuyerData(BuyerData buyerData) {
        name.setText("Name:"+buyerData.name);
        email.setText("Email:"+buyerData.email);
    }

}
