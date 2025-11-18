package decorator;

public class ParkVisit implements CityPlanner {
    private CityPlanner planner;

    public ParkVisit(CityPlanner planner) {
        this.planner = planner;
    }

    @Override
    public String getDescription() {
        return planner.getDescription() + ", Walking in the Park";
    }

    @Override
    public double getCost() {
        return planner.getCost() + 20;
    }

    @Override
    public int getTimeRequired() {
        return planner.getTimeRequired() + 1;
    }
}
