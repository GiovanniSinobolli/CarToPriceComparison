package com.example.pricetohpGraph;
import com.example.pricetohpGraph.DBUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

//Loading chart data
public class ChartController {

    @FXML private LineChart<String, Number> chart;
    @FXML private CategoryAxis xAxis;
    @FXML private NumberAxis yAxis;
    @FXML private RadioButton listPriceButton;
    @FXML private RadioButton listHorsepowerButton;

    public void initialize() {
        // Wire radio buttons
        listPriceButton.setOnAction(e -> loadChartData("price"));
        listHorsepowerButton.setOnAction(e -> loadChartData("horsepower"));
        // Default view
        loadChartData("price");
    }

//Queries the car table, sorts and populates the linechart

    private void loadChartData(String sortBy) {
        chart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(sortBy);

        String sql = "SELECT brand," + sortBy + " FROM Car ORDER BY " + sortBy + " ASC";
        try (Connection conn = DBUtil.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                String brand = rs.getString("brand");
                Number value = sortBy.equals("price")
                        ? rs.getDouble("price")
                        : rs.getInt("horsepower");
                series.getData().add(new XYChart.Data<>(brand, value));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        xAxis.setLabel("Brand");
        yAxis.setLabel(sortBy.substring(0,1).toUpperCase() + sortBy.substring(1));
        chart.getData().add(series);
    }

//    swithcing scenes
    @FXML
    private void onTableClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/pricetohpGraph/cartable-view.fxml")
            );
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Car Table");
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
