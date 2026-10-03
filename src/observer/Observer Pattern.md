
## Publishers + Subscribers = Observer Pattern

We call the publisher the **Subject** and the subscribers the **Observers**.

## The Observer Pattern

> The Observer Pattern defines a one-to-many dependency between objects so that when one object changes state, all of its dependents are notified and updated automatically.

Think of it like a newspaper subscription. The subject and observers define the one-to-many relationship: one subject notifies many observers when something in the subject changes. The observers are dependent on the subject - when the subject's state changes, the observers are notified.

## The Interfaces Involved

The pattern is built on two (sometimes three) core interfaces:

- **`Subject`** - typically declares:
    - `registerObserver(Observer o)`
    - `removeObserver(Observer o)`
    - `notifyObservers()`
- **`Observer`** - declares an `update(...)` method (the shape depends on push vs. pull - see below)
- **`DisplayElement`** (in the WeatherData example) - a separate interface for the display-specific concern, e.g. `display()`. This is kept distinct from `Observer` because "being notified of new data" and "how to render/display that data" are different responsibilities.

**This is the concrete fix to the original misguided `WeatherData` implementation:** instead of three named, concretely-typed display fields, `WeatherData` now holds a `List<Observer>` - a collection typed as the _interface_, not concrete classes. This is what finally makes it possible to add/remove displays at runtime and loop over them polymorphically when notifying.

## Push vs. Pull Model

Two ways the subject can get data to its observers:

- **Push model** - the subject sends all relevant state directly as arguments to `update()`, e.g. `update(temp, humidity, pressure)`. Simple, but every observer receives every piece of data whether it needs it or not, and adding a new piece of state to the subject means changing the `update()` signature - which breaks every observer's implementation.
- **Pull model** - the subject just notifies observers that _something_ changed (e.g. `update(Subject subject)`, or no-arg), and each observer calls getters on the subject itself to pull only the specific data it actually needs.

**Pull is generally considered more flexible**: observers aren't forced to handle data they don't care about, and the subject can add new state without breaking every observer's method signature.

## Java's Built-in Observer Support (older editions / historical note)

The original book edition references `java.util.Observable` / `java.util.Observer`. Worth knowing: Oracle **deprecated these in Java 9+**, partly because `Observable` was a concrete **class**, not an interface - meaning a class already extending something else couldn't also extend `Observable` (Java's single-inheritance limitation). This is itself a real-world illustration of why favoring interfaces over concrete classes (Chapter 1's 2nd principle) matters in practice.

## The Power of Loose Coupling

When two objects are loosely coupled, they can interact, but typically have very little knowledge of each other. The Observer Pattern is a strong example:

1. **The subject only knows an observer implements the `Observer` interface** - it doesn't need to know the observer's concrete class, what it does internally, or anything else about it.
2. **New observers can be added at any time.** Since the subject only depends on a list of objects implementing `Observer`, observers can be added, replaced, or removed at runtime, and the subject keeps working without modification.
3. **The subject never needs to be modified to support new observer types.** A new concrete class just needs to implement `Observer` and register itself - the subject doesn't care, it notifies anything that satisfies the interface.
4. **Subjects and observers can be reused independently of each other**, since they aren't tightly coupled.
5. **Changes to either subject or observer don't affect the other**, as long as both continue to fulfill their obligations to the `Subject`/`Observer` interfaces.

### What loose coupling does and doesn't protect against

- **Protects against:** depending on each other's _concrete implementations_. Subjects and observers can vary, multiply, and be swapped freely without rippling into each other's source code.
- **Does NOT protect against:** changes to the **shared interface's contract** itself. If `Observer.update()`'s signature changes (e.g. switching from push to pull), every class implementing `Observer` must be updated to match, and the subject's notification call must change too. Both sides are still - deliberately - coupled to the interface's contract; that's the one dependency they both intentionally share. Loose coupling decouples implementations, not the agreed-upon interface shape.

## 4th Design Principle - Strive for Loosely Coupled Designs

> Strive for loosely coupled designs between objects that interact.

**Reference:** [Observer Pattern - Refactoring Guru](https://refactoring.guru/design-patterns/observer) (very useful, cross-reference alongside the book)