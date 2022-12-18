package com.example.course_project;


import javafx.application.Application;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.scene.Scene;
        import javafx.stage.Stage;
        import javafx.scene.chart.*;
        import javafx.scene.Group;

public class PieChartSample extends Application {

    @Override public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Imported Fruits");
        stage.setWidth(500);
        stage.setHeight(500);

        String fin_flow_data = ClientCommonFuctions.clientServerDialog(3, 1, "0");
        fin_flow_data = fin_flow_data.substring(1, fin_flow_data.length() - 1);
        String[] splited_info = fin_flow_data.split(",");

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Собственные фонды", Double.parseDouble(splited_info[1])),
                        new PieChart.Data("Заемные фонды", Double.parseDouble(splited_info[2])),
                        new PieChart.Data("Резервы", Double.parseDouble(splited_info[3])));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Денежные ресурсы");

        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);
        stage.show();
    }
}
