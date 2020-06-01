package graviwave;

import java.util.List;
import java.util.Map;

public interface ControllerInterface {
    boolean open(String path);
    void showList();
    List<Country> getModel();
    Map<String, String> getCapMap();
}


