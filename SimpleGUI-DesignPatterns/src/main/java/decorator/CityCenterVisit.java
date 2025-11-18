package decorator;

public class CityCenterVisit implements CityPlanner {
    private CityPlanner planner;

    public CityCenterVisit(CityPlanner planner) {
        this.planner = planner;
    }

    @Override
    public String getDescription() {
        return planner.getDescription() + ", Visiting City Center";
    }

    @Override
    public double getCost() {
        return planner.getCost() + 30;
    }

    @Override
    public int getTimeRequired() {
        return planner.getTimeRequired() + 2;
    }
}
