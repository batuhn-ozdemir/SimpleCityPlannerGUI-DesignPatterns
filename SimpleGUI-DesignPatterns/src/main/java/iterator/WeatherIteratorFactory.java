package iterator;

import model.City;
import model.WeatherState;

import java.util.List;

public class WeatherIteratorFactory {
    public static CityIterator getIterator(List<City> cities, WeatherState weather) {
        return new WeatherFilteredIterator(cities, weather);
    }
}
