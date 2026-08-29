package strategy;

public class FlyNoWhere implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I can't fly!");
    }
}
