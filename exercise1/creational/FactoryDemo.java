package creational;

// Factory Pattern - Document Creator
interface Document {
    void open();
    void save();
    void close();
}

class WordDocument implements Document {
    private String name;
    
    public WordDocument(String name) {
        this.name = name;
    }
    
    @Override
    public void open() {
        System.out.println("Opening Word document: " + name + ".docx");
    }
    
    @Override
    public void save() {
        System.out.println("Saving Word document: " + name + ".docx");
    }
    
    @Override
    public void close() {
        System.out.println("Closing Word document: " + name + ".docx");
    }
}

class PdfDocument implements Document {
    private String name;
    
    public PdfDocument(String name) {
        this.name = name;
    }
    
    @Override
    public void open() {
        System.out.println("Opening PDF document: " + name + ".pdf");
    }
    
    @Override
    public void save() {
        System.out.println("Saving PDF document: " + name + ".pdf");
    }
    
    @Override
    public void close() {
        System.out.println("Closing PDF document: " + name + ".pdf");
    }
}

class SpreadsheetDocument implements Document {
    private String name;
    
    public SpreadsheetDocument(String name) {
        this.name = name;
    }
    
    @Override
    public void open() {
        System.out.println("Opening Spreadsheet: " + name + ".xlsx");
    }
    
    @Override
    public void save() {
        System.out.println("Saving Spreadsheet: " + name + ".xlsx");
    }
    
    @Override
    public void close() {
        System.out.println("Closing Spreadsheet: " + name + ".xlsx");
    }
}

class DocumentFactory {
    public static Document createDocument(String type, String name) {
        switch (type.toLowerCase()) {
            case "word": return new WordDocument(name);
            case "pdf": return new PdfDocument(name);
            case "spreadsheet": return new SpreadsheetDocument(name);
            default: throw new IllegalArgumentException("Unknown document type: " + type);
        }
    }
}

public class FactoryDemo {
    public static void demonstrate() {
        System.out.println("\n=== FACTORY PATTERN DEMO ===");
        
        Document wordDoc = DocumentFactory.createDocument("word", "Report");
        Document pdfDoc = DocumentFactory.createDocument("pdf", "Manual");
        Document sheetDoc = DocumentFactory.createDocument("spreadsheet", "Budget");
        
        wordDoc.open();
        wordDoc.save();
        wordDoc.close();
        
        pdfDoc.open();
        sheetDoc.open();
    }
}