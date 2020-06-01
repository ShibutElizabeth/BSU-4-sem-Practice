package sample;

public interface ControllerInterface {
    void open(String path);
    void write(String path);
    void SAXCounting(String fileName, String find);
    void openDAT(String path);
    void writeDAT(String path);
    void updateList(Planet planet, String action);
}
