
## Simple Factory

The Simple Factory isn’t actually a Design Pattern; it’s more of a programming idiom. But it is commonly used.
**Factory** is an ambiguous term that stands for a function, method or class that supposed to be producing something. Most commonly, factories produce objects. But they may also produce files, records in databases, etc.
Here is an example of how the Simple Factory puts things up together:![[Pasted image 20261011141204.png]]

## Factory Method Pattern

>**The Factory Method Pattern:** defines an interface for creating an object, but lets subclasses decide which class to instantiate. Factory Method lets a class defer instantiation to subclasses. 

The general class diagram for the factory method pattern:![[Pasted image 20261011143053.png]]

All factory patterns encapsulate object creation. The Factory Method Pattern encapsulates object creation by letting subclasses decide what objects to create.
Here is an example of how the Factory Method Pattern puts thing up together:![[Pasted image 20261011141619.png]]

### Creators and Products

For every concrete Creator, there’s typically a whole set of products that it creates. And here is an example:![[Pasted image 20261011142041.png]]

## 6th Design Principle - Dependency Inversion Principle

> **The Dependency Inversion Principle** depend upon abstractions. Do not depend upon concrete classes.

### Some guidelines to follow the principle

1. No variable should hold a reference to a concrete class
2. No class should derive from concrete class
3. No method should override an implemented method of any of its base classes
These guidelines are impossible to follow all the time but, we have to **strive** for them.

## Abstract Factory Pattern

> **The Abstract Factory Pattern** provides an interface for creating families of related or dependent objects without specifying their concrete classes.

![[Pasted image 20261121131600.png]]

The class diagram in the terms of pizzas: ![[Pasted image 20261121131754.png]]

Most of the methods inside the abstract factory pattern are factory methods. The job of an Abstract Factory is to define an interface for creating a set of products. Each method in that interface is responsible for creating a concrete product, and we implement a subclass of the Abstract Factory to supply those implementations.

## Factory Method and Abstract Factory compared

![[Pasted image 20261121133823.png]]
![[Pasted image 20261121133833.png]]


[**Very good example on the Factory method pattern in OCR feature in JabRef**](https://github.com/JabRef/jabref/pull/16927)

**Reference:** [Factory Pattern — Refactoring Guru](https://refactoring.guru/design-patterns/factory-method), [Abstract Factory Pattern](https://refactoring.guru/design-patterns/abstract-factory) (very useful, cross-reference alongside the book), [Comparison between factory and abstract factory pattern](https://refactoring.guru/design-patterns/factory-comparison)
