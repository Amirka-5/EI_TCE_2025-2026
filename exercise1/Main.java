// Main class to demonstrate all patterns
public class Main {
    public static void main(String[] args) {
        System.out.println("=== EXERCISE 1: DESIGN PATTERNS DEMONSTRATION ===\n");
        
        // Behavioral Patterns
        behavioral.ObserverDemo.demonstrate();
        behavioral.StrategyDemo.demonstrate();
        
        // Creational Patterns
        creational.SingletonDemo.demonstrate();
        creational.FactoryDemo.demonstrate();
        
        // Structural Patterns
        structural.AdapterDemo.demonstrate();
        structural.DecoratorDemo.demonstrate();
        
        System.out.println("\n=== ALL 6 DESIGN PATTERNS DEMONSTRATED SUCCESSFULLY ===");
    }
}