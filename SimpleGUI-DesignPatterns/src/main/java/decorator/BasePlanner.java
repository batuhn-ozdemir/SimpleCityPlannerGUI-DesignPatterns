package decorator;

import model.City;

public class BasePlanner implements CityPlanner {
    private City city;

    public BasePlanner(City city) {
        this.city = city;
    }

    @Override
    public String getDescription() {
        return "Plan for " + city.getName();
    }

    @Override
    public double getCost() {
        return 0;
    }

    @Override
    public int getTimeRequired() {
        return 0;
    }
}
