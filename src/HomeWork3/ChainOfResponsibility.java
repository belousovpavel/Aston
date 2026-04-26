package HomeWork3;

abstract class Handler {
    protected Handler next;

    public void setNext(Handler next) { this.next = next; }

    public void handle(String request) {
        if (canHandle(request)) {
            process(request);
        } else if (next != null) {
            next.handle(request);
        } else {
            System.out.println("No handler for: " + request);
        }
    }

    protected abstract boolean canHandle(String request);
    protected abstract void process(String request);
}

class LowLevelHandler extends Handler {
    @Override protected boolean canHandle(String request) { return request.length() <= 5; }
    @Override protected void process(String request) { System.out.println("LowLevelHandler: " + request); }
}

class MidLevelHandler extends Handler {
    @Override protected boolean canHandle(String request) { return request.length() <= 10; }
    @Override protected void process(String request) { System.out.println("MidLevelHandler: " + request); }
}

class HighLevelHandler extends Handler {
    @Override protected boolean canHandle(String request) { return true; }
    @Override protected void process(String request) { System.out.println("HighLevelHandler: " + request); }
}

// Использование
public class ChainOfResponsibility {
    public static void main(String[] args) {
        Handler low = new LowLevelHandler();
        Handler mid = new MidLevelHandler();
        Handler high = new HighLevelHandler();

        low.setNext(mid);
        mid.setNext(high);

        low.handle("Hi");
        low.handle("Hello123");
        low.handle("Long request here");
    }
}
