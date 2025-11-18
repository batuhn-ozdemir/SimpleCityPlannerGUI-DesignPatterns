package ui;

import data.CityRepository;
import decorator.*;
import iterator.*;
import model.City;
import model.WeatherState;
import observer.WeatherObserver;
import observer.WeatherProvider;
import strategy.*;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;
import java.util.stream.Collectors;

public class MainWindow2 extends Application implements WeatherObserver {

    private ListView<City> cityListView = new ListView<>();
    private ComboBox<String> sortComboBox = new ComboBox<>();
    private ComboBox<String> weatherComboBox = new ComboBox<>();
    private CheckBox museumBox = new CheckBox("Visit Museum");
    private CheckBox parkBox = new CheckBox("Walk in the Park");
    private CheckBox mallBox = new CheckBox("Visit Shopping Mall");
    private CheckBox centerBox = new CheckBox("Visit City Center");

    private BarChart<String, Number> tempChart;
    private PieChart weatherPie;

    private WeatherProvider weatherProvider;

    @Override
    public void start(Stage stage) {
        // Sort ComboBox
        sortComboBox.getItems().addAll("Name", "Population", "Area");
        sortComboBox.setValue("Name");

        // Weather ComboBox
        weatherComboBox.getItems().addAll("ALL", "SUNNY", "CLOUDY", "RAINY", "SNOWY");
        weatherComboBox.setValue("ALL");

        // Event Listeners
        sortComboBox.setOnAction(e -> updateCityList());
        weatherComboBox.setOnAction(e -> updateCityList());
        cityListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updatePlanner());

        // Temperature Bar Chart
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        tempChart = new BarChart<>(xAxis, yAxis);
        tempChart.setTitle("City Temperatures");

        // Weather Pie Chart
        weatherPie = new PieChart();
        weatherPie.setTitle("Weather States");

        // "All Cities" Section
        VBox cityBox = new VBox(10,
                new Label("Sort By:"), sortComboBox,
                new Label("Weather Filter:"), weatherComboBox,
                new Label("All Cities:"), cityListView
        );
        cityBox.setPadding(new Insets(10));
        TitledPane cityPane = new TitledPane("Cities & Weather Filter", cityBox);
        cityPane.setCollapsible(false);

        // "Planner" Section
        VBox plannerBox = new VBox(10, museumBox, parkBox, mallBox, centerBox);
        plannerBox.setPadding(new Insets(10));
        TitledPane plannerPane = new TitledPane("Activity Planner", plannerBox);
        plannerPane.setCollapsible(false);

        VBox leftPane = new VBox(15, cityPane, plannerPane);
        leftPane.setPrefWidth(300);
        leftPane.setPadding(new Insets(10));

        VBox rightPane = new VBox(10, tempChart, weatherPie);
        rightPane.setPadding(new Insets(10));
        rightPane.setPrefWidth(600);

        HBox root = new HBox(10, leftPane, rightPane);
        Scene scene = new Scene(root, 950, 600);
        stage.setTitle("City Planner");
        stage.setScene(scene);
        stage.show();

        // Initial data setup
        updateCityList();
        updateCharts();

        // Weather thread
        weatherProvider = new WeatherProvider();
        weatherProvider.addObserver(this);
        weatherProvider.start();
    }

    private void updateCityList() {
        List<City> cities = CityRepository.getInstance().getCityList();

        String weatherFilter = weatherComboBox.getValue();
        if (!weatherFilter.equals("ALL")) {
            CityIterator iterator = WeatherIteratorFactory.getIterator(cities, WeatherState.valueOf(weatherFilter));
            List<City> filtered = new ArrayList<>();
            while (iterator.hasNext()) {
                filtered.add(iterator.next());
            }
            cities = filtered;
        }

        SortStrategy strategy = switch (sortComboBox.getValue()) {
            case "Population" -> new SortByPopulation();
            case "Area" -> new SortByArea();
            default -> new SortByName();
        };
        strategy.sort(cities);

        //cityListView.setItems(FXCollections.observableArrayList(cities));
        cityListView.getItems().setAll(cities);
    }

    private void updatePlanner() {
        City selected = cityListView.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        CityPlanner planner = new BasePlanner(selected);
        if (museumBox.isSelected()) planner = new MuseumVisit(planner);
        if (parkBox.isSelected()) planner = new ParkVisit(planner);
        if (mallBox.isSelected()) planner = new ShoppingMallVisit(planner);
        if (centerBox.isSelected()) planner = new CityCenterVisit(planner);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Plan Info");
        alert.setHeaderText("Selected Plan:");
        alert.setContentText(planner.getDescription() +
                "\nCost: " + planner.getCost() + " TL" +
                "\nTime: " + planner.getTimeRequired() + " hours");
        alert.show();
    }

    private void updateCharts() {
        List<City> cities = CityRepository.getInstance().getCityList();

        XYChart.Series<String, Number> tempSeries = new XYChart.Series<>();
        tempSeries.setName("Temperature");
        for (City city : cities) {
            tempSeries.getData().add(new XYChart.Data<>(city.getName(), city.getCurrentTemperature()));
        }
        tempChart.getData().setAll(tempSeries);

        Map<WeatherState, Long> stateCount = cities.stream()
                .collect(Collectors.groupingBy(City::getCurrentWeatherState, Collectors.counting()));

        List<PieChart.Data> pieData = stateCount.entrySet().stream()
                .map(entry -> new PieChart.Data(entry.getKey().name(), entry.getValue()))
                .collect(Collectors.toList());

        weatherPie.setData(FXCollections.observableArrayList(pieData));
    }

    @Override
    public void onWeatherUpdate() {
        Platform.runLater(() -> {
            updateCityList();
            updateCharts();
        });
    }

    public static void main(String[] args) {
        launch();
    }
}

