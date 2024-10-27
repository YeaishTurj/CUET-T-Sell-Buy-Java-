package com.example.app.customDesign;

import com.example.app.ItemScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomItemController implements Initializable {
    @FXML
    private ImageView itemImage1;
    @FXML
    private ImageView itemImage2;
    @FXML
    private Text itemPrice1;
    @FXML
    private Text itemPrice2;
    @FXML
    private Text itemQuantity1;
    @FXML
    private Text itemQuantity2;
    @FXML
    private Text itemTitle1;
    @FXML
    private Text itemTitle2;
    @FXML
    private Button more1;
    @FXML
    private Button more2;
    public Owner itemOwner1;
    public Item singleItem1;
    public Owner itemOwner2;
    public Item singleItem2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //more1.setOnMouseClicked(this::seeDetails1);
    }
    //------- here we receive the data of item and owner to show in the custom design ------//
    public void setData1(Item item,Owner owner){
        itemTitle1.setText(item.getTitle());
        itemPrice1.setText(Integer.toString(item.getPrice()));
        itemQuantity1.setText(Integer.toString(item.getQuantity()));
        singleItem1=item;
        itemOwner1=owner;
    }
    public void setData2(Item item,Owner owner){
        itemTitle2.setText(item.getTitle());
        itemPrice2.setText(Integer.toString(item.getPrice()));
        itemQuantity2.setText(Integer.toString(item.getQuantity()));
        singleItem2=item;
        itemOwner2=owner;
    }
    public void seeDetails2(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/app/item_screen.fxml"));
            Parent root = loader.load();
            ItemScreenController itemScreenController = loader.getController();
            itemScreenController.getDetails(itemOwner2,singleItem2);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public void seeDetails1(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/app/item_screen.fxml"));
            Parent root = loader.load();
            ItemScreenController itemScreenController = loader.getController();
            itemScreenController.getDetails(itemOwner1,singleItem1);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}