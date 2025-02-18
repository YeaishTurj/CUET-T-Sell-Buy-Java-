// completed

package com.example.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class SellerPageController {

    private static final String WELCOME_SCREEN_FXML = "welcome_screen.fxml";
    private static final String PRODUCT_MANAGEMENT_FMXL = "product_management.fxml";
    private static final String PRODUCT_UPLOAD_FXML = "product_upload.fxml";
    private static final String CSS_PATH = "/css/styles.css";
    private static final double SCREEN_WIDTH = 1024;
    private static final double SCREEN_HEIGHT = 768;

    private Connection connection = null;
    private String sellerEmail;

    @FXML
    private Text sellerEmailField, companyName, phoneNum, waNum, fbLink;
    @FXML
    private TextField newCompanyName, newPhoneNum, newWANum, newFBLink;
    @FXML
    private Button manageProductsButton, uploadProductButton, signOutButton;
    @FXML
    private Button editCompanyButton, saveCompanyButton;
    @FXML
    private Button editPhoneButton, savePhoneButton;
    @FXML
    private Button editWAButton, saveWAButton;
    @FXML
    private Button editFBButton, saveFBButton;

    @FXML
    public void initialize() throws SQLException {
        sellerEmail= SessionData.getSellerEmail();
        connectToDatabase();
        showSellerInfo();
    }

    @FXML
    private void connectToDatabase() {
        String url = "jdbc:mysql://localhost:3306/CUET_T_SELL_DB";
        String user = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not loaded!");
        }

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Database connection failed!");
        }
    }

    @FXML
    private void showSellerInfo() throws SQLException {
        if (connection != null) {
            String query = "SELECT * FROM seller WHERE email = ?";

            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setString(1, sellerEmail);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    String name = rs.getString("name");
                    String contact = rs.getString("contact");
                    String wApp = rs.getString("w_app");
                    String f_bLink = rs.getString("facebook_link");

                    companyName.setText(name);
                    sellerEmailField.setText(sellerEmail);
                    phoneNum.setText(contact);
                    waNum.setText(wApp);
                    fbLink.setText(f_bLink);
                }
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Database connection not established. Seller info not found.");
        }
    }

    @FXML
    public void handleManageProducts() throws IOException {
        Parent root = loadFXML(PRODUCT_MANAGEMENT_FMXL);
        Stage stage = (Stage) manageProductsButton.getScene().getWindow();
        setScene(stage, root);
    }

    @FXML
    public void handleUploadProduct() throws IOException {
        Parent root = loadFXML(PRODUCT_UPLOAD_FXML);
        Stage stage = (Stage) uploadProductButton.getScene().getWindow();
        setScene(stage, root);
    }

    @FXML
    public void handleSignOut() throws IOException {
        Parent root = loadFXML(WELCOME_SCREEN_FXML);
        Stage stage = (Stage) signOutButton.getScene().getWindow();
        setScene(stage, root);
    }

    @FXML
    public void handleEditCompany(ActionEvent actionEvent) {
        editCompanyButton.setVisible(false);
        saveCompanyButton.setVisible(true);
        companyName.setVisible(false);
        newCompanyName.setVisible(true);
        newCompanyName.setText(companyName.getText());
    }

    @FXML
    public void handleSaveCompany(ActionEvent actionEvent) {
        if(newCompanyName.getText().isEmpty()){
            showErrorPopup("Company name cannot be empty!");
        }else{
            editCompanyButton.setVisible(true);
            saveCompanyButton.setVisible(false);
            companyName.setVisible(true);
            newCompanyName.setVisible(false);
            companyName.setText(newCompanyName.getText());
            editSellerInfo(newCompanyName, "name");
        }

    }

    private void showErrorPopup(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Sign In Error");
        alert.setHeaderText(null);

        Label label = new Label(message);
        label.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");

        alert.getDialogPane().setContent(label);

        alert.showAndWait();
    }

    @FXML
    public void handleEditPhone(ActionEvent actionEvent) {
        editPhoneButton.setVisible(false);
        savePhoneButton.setVisible(true);
        phoneNum.setVisible(false);
        newPhoneNum.setVisible(true);
        newPhoneNum.setText(phoneNum.getText());
    }

    @FXML
    public void handleSavePhone(ActionEvent actionEvent) {
        if(newPhoneNum.getText().isEmpty()){
            showErrorPopup("Phone number cannot be empty!");
        }else {
            editPhoneButton.setVisible(true);
            savePhoneButton.setVisible(false);
            phoneNum.setVisible(true);
            newPhoneNum.setVisible(false);
            phoneNum.setText(newPhoneNum.getText());
            editSellerInfo(newPhoneNum, "contact");
        };
    }

    @FXML
    public void handleEditWA(ActionEvent actionEvent) {
        editWAButton.setVisible(false);
        saveWAButton.setVisible(true);
        waNum.setVisible(false);
        newWANum.setVisible(true);
        newWANum.setText(waNum.getText());
    }

    @FXML
    public void handleSaveWA(ActionEvent actionEvent) {
        if(newWANum.getText().isEmpty()){
            showErrorPopup("WhatsApp number cannot be empty!");
        }else {
            editWAButton.setVisible(true);
            saveWAButton.setVisible(false);
            waNum.setVisible(true);
            newWANum.setVisible(false);
            waNum.setText(newWANum.getText());
            editSellerInfo(newWANum, "w_app");
        };
    }

    @FXML
    public void handleEditFB(ActionEvent actionEvent) {
        editFBButton.setVisible(false);
        saveFBButton.setVisible(true);
        fbLink.setVisible(false);
        newFBLink.setVisible(true);
        newFBLink.setText(fbLink.getText());
    }

    @FXML
    public void handleSaveFB(ActionEvent actionEvent) {
        if(newFBLink.getText().isEmpty()){
            showErrorPopup("Facebook link cannot be empty!");
        }else {
            editFBButton.setVisible(true);
            saveFBButton.setVisible(false);
            fbLink.setVisible(true);
            newFBLink.setVisible(false);
            fbLink.setText(newFBLink.getText());
            editSellerInfo(newFBLink, "facebook_link");
        };
    }

    private Parent loadFXML(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        return loader.load();
    }

    private void setScene(Stage stage, Parent root) {
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(CSS_PATH)).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void editSellerInfo(TextField newInfo, String infoType) {
        if (connection != null) {
            String query = "UPDATE seller SET " + infoType + " = ? WHERE email = ?";

            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setString(1, newInfo.getText());
                pstmt.setString(2, sellerEmail);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Database connection not established. Seller info not updated.");
        }
    }
}
