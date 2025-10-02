package behavioral;

// Strategy Pattern - Payment Processing
interface PaymentStrategy {
    void pay(double amount);
}

class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    
    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using Credit Card (" + cardNumber.substring(cardNumber.length() - 4) + ")");
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;
    
    public PayPalPayment(String email) {
        this.email = email;
    }
    
    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using PayPal (" + email + ")");
    }
}

class PaymentProcessor {
    private PaymentStrategy strategy;
    
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }
    
    public void processPayment(double amount) {
        strategy.pay(amount);
    }
}

public class StrategyDemo {
    public static void demonstrate() {
        System.out.println("\n=== STRATEGY PATTERN DEMO ===");
        PaymentProcessor processor = new PaymentProcessor();
        
        processor.setPaymentStrategy(new CreditCardPayment("1234-5678-9012-3456"));
        processor.processPayment(100.00);
        
        processor.setPaymentStrategy(new PayPalPayment("user@example.com"));
        processor.processPayment(50.00);
    }
}