package iterator;

import model.City;
import model.WeatherState;

import java.util.List;

public class WeatherFilteredIterator implements CityIterator {
    private List<City> cities;
    private WeatherState filter;
    private int position = 0;

    public WeatherFilteredIterator(List<City> cities, WeatherState filter) {
        this.cities = cities;
        this.filter = filter;
    }

    @Override
    public boolean hasNext() {
        while (position < cities.size()) {
            if (cities.get(position).getCurrentWeatherState() == filter) {
                return true;
            }
            position++;
        }
        return false;
    }

    @Override
    public City next() {
        return cities.get(position++);
    }
}
