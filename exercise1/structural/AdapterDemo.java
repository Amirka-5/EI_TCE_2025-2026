package structural;

// Adapter Pattern - Legacy System Integration

// Legacy component (incompatible interface)
class LegacyPrinter {
    public void printDocument(String text, int copies) {
        for (int i = 0; i < copies; i++) {
            System.out.println("Legacy Printer [Copy " + (i + 1) + "]: " + text);
        }
    }
    
    public void warmUp() {
        System.out.println("Legacy Printer: Warming up...");
    }
}

// Modern interface that clients expect
interface ModernPrinter {
    void print(String content);
    void printMultiple(String content, int copies);
}

// Adapter that makes LegacyPrinter compatible with ModernPrinter
class PrinterAdapter implements ModernPrinter {
    private LegacyPrinter legacyPrinter;
    
    public PrinterAdapter(LegacyPrinter legacyPrinter) {
        this.legacyPrinter = legacyPrinter;
        legacyPrinter.warmUp();
    }
    
    @Override
    public void print(String content) {
        legacyPrinter.printDocument(content, 1);
    }
    
    @Override
    public void printMultiple(String content, int copies) {
        legacyPrinter.printDocument(content, copies);
    }
}

public class AdapterDemo {
    public static void demonstrate() {
        System.out.println("\n=== ADAPTER PATTERN DEMO ===");
        
        LegacyPrinter oldPrinter = new LegacyPrinter();
        ModernPrinter modernPrinter = new PrinterAdapter(oldPrinter);
        
        modernPrinter.print("Hello World with Modern Interface!");
        modernPrinter.printMultiple("Important Document", 3);
    }
}