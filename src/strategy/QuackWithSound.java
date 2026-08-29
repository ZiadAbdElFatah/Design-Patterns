package strategy;

public class QuackWithSound implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("I'm quacking!");
    }
}
