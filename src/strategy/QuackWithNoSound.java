package strategy;

public class QuackWithNoSound implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("I can't quack!");
    }
}
