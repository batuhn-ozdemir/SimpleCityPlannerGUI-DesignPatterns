package strategy;

import model.City;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortByName implements SortStrategy {
    @Override
    public void sort(List<City> cities) {
        Collections.sort(cities, Comparator.comparing(City::getName));
    }
}
