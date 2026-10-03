package decorator;

public class Whip extends CondimentDecorator {
    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return "Whipped " + beverage.getDescription();
    }

    @Override
    public double cost() {
        return beverage.cost() + .1;
    }
}