package sample;

import java.util.List;

public interface ViewInterface {
    void showTable(List<Planet> list);
    void showError(String message);
    void showInfo(String title, String message);
}
