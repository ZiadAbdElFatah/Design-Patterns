package strategy;

public class MallardDuck extends Duck {
    public MallardDuck() {
        flyBehavior = new FlyWithWings();
        quackBehavior = new QuackWithSound();
    }

    public void display() {
        System.out.println("I am a Mallard Duck");
    }
}
