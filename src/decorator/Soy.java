package decorator;

public class Soy extends CondimentDecorator{
    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " with Soy";
    }

    @Override
    public double cost() {
        return beverage.cost() + .15;
    }
}