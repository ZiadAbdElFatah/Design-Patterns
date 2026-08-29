package strategy;

public class RubberDuck extends Duck {
    public RubberDuck() {
        flyBehavior = new FlyNoWhere();
        quackBehavior = new QuackWithNoSound();
    }

    public void display() {
        System.out.println("I am a Rubber Duck");
    }
}
