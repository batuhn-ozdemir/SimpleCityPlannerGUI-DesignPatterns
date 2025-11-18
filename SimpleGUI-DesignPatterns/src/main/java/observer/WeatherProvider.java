package observer;

import data.CityRepository;
import model.City;
import model.WeatherState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeatherProvider extends Thread {
    private List<WeatherObserver> observers = new ArrayList<>();
    private boolean running = true;

    public void addObserver(WeatherObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(WeatherObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (WeatherObserver observer : observers) {
            observer.onWeatherUpdate();
        }
    }

    private WeatherState randomWeather() {
        WeatherState[] states = WeatherState.values();
        return states[new Random().nextInt(states.length)];
    }

    @Override
    public void run() {
        List<City> cities = CityRepository.getInstance().getCityList();
        Random rand = new Random();

        while (running) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (City city : cities) {
                city.setCurrentWeatherState(randomWeather());
                city.setCurrentTemperature(rand.nextInt(35));
            }

            notifyObservers();
        }
    }

    public void stopUpdates() {
        running = false;
    }
}
