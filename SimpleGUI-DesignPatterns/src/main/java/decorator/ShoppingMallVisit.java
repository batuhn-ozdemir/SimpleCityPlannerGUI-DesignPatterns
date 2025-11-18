package decorator;

public class ShoppingMallVisit implements CityPlanner {
    private CityPlanner planner;

    public ShoppingMallVisit(CityPlanner planner) {
        this.planner = planner;
    }

    @Override
    public String getDescription() {
        return planner.getDescription() + ", Visiting Shopping Mall";
    }

    @Override
    public double getCost() {
        return planner.getCost() + 100;
    }

    @Override
    public int getTimeRequired() {
        return planner.getTimeRequired() + 3;
    }
}
