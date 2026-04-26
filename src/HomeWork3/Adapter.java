package HomeWork3;

class LegacyPrinter {
    public void printDocument(String text) {
        System.out.println("Legacy Printer: " + text);
    }
}

interface ModernPrinter {
    void print(String text);
}

class PrinterAdapter implements ModernPrinter {
    private LegacyPrinter legacyPrinter;

    public PrinterAdapter(LegacyPrinter legacyPrinter) {
        this.legacyPrinter = legacyPrinter;
    }

    @Override
    public void print(String text) {
        legacyPrinter.printDocument(text);
    }
}

class Client {
    void usePrinter(ModernPrinter printer) {
        printer.print("Hello, world!");
    }
}

// Использование
public class Adapter {
    public static void main(String[] args) {
        LegacyPrinter legacy = new LegacyPrinter();
        ModernPrinter adapter = new PrinterAdapter(legacy);

        Client client = new Client();
        client.usePrinter(adapter); // Legacy Printer: Hello, world!
    }
}
