package decorator;

public class MuseumVisit implements CityPlanner {
    private CityPlanner planner;

    public MuseumVisit(CityPlanner planner) {
        this.planner = planner;
    }

    @Override
    public String getDescription() {
        return planner.getDescription() + ", Visiting Museum";
    }

    @Override
    public double getCost() {
        return planner.getCost() + 50; // TL
    }

    @Override
    public int getTimeRequired() {
        return planner.getTimeRequired() + 2; // saat
    }
}
