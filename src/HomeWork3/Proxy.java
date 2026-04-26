package HomeWork3;

interface Image {
    void display();
}

class RealImage implements Image {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("Loading " + filename);
    }

    @Override
    public void display() {
        System.out.println("Displaying " + filename);
    }
}

class ProxyImage implements Image {
    private String filename;
    private RealImage realImage;

    public ProxyImage(String filename) { this.filename = filename; }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        realImage.display();
    }
}

public class Proxy {
    public static void main(String[] args) {
        Image image1 = new ProxyImage("photo.jpg");
        image1.display();
        image1.display();

        Image image2 = new ProxyImage("avatar.png");
        image2.display();
    }
}