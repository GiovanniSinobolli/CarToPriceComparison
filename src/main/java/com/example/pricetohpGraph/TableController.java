package com.example.pricetohpGraph;
import com.example.pricetohpGraph.Car;
import com.example.pricetohpGraph.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

// fetches all car rows and displays them in the table

public class TableController {

    @FXML private TableView<Car> tableView;
    @FXML private TableColumn<Car,Integer> colId;
    @FXML private TableColumn<Car,String> colBrand;
    @FXML private TableColumn<Car,Double> colPrice;
    @FXML private TableColumn<Car,Integer> colHP;
    @FXML private Button closeButton;

    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colHP.setCellValueFactory(new PropertyValueFactory<>("horsepower"));

        tableView.setItems(fetchAllCars());
    }

    private ObservableList<Car> fetchAllCars() {
        ObservableList<Car> list = FXCollections.observableArrayList();
        String sql = "SELECT id, brand, price, horsepower FROM Car";
        try (Connection conn = DBUtil.getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultset = statement.executeQuery(sql)) {

            while (resultset.next()) {
                list.add(new Car(
                        resultset.getInt("id"),
                        resultset.getString("brand"),
                        resultset.getDouble("price"),
                        resultset.getInt("horsepower")
                ));
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    //closes the scene, gonna use to close the "tables" scene to emulate going back to the chart window
    @FXML
    private void onCloseClicked() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
