package model;

public class City {
    private String name;
    private int population;
    private double area;
    private int currentTemperature;
    private WeatherState currentWeatherState;

    public City(String name, int population, double area, int currentTemperature, WeatherState currentWeatherState) {
        this.name = name;
        this.population = population;
        this.area = area;
        this.currentTemperature = currentTemperature;
        this.currentWeatherState = currentWeatherState;
    }


    public String getName() { return name; }
    public int getPopulation() { return population; }
    public double getArea() { return area; }
    public int getCurrentTemperature() { return currentTemperature; }
    public WeatherState getCurrentWeatherState() { return currentWeatherState; }


    public void setCurrentTemperature(int currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public void setCurrentWeatherState(WeatherState currentWeatherState) {
        this.currentWeatherState = currentWeatherState;
    }

    @Override
    public String toString() {
        return String.format("%s | Pop: %d | Area: %.2f | Temp: %.1f°C",
                name,
                population,
                (double) area,
                (double) currentTemperature);
    }
}
