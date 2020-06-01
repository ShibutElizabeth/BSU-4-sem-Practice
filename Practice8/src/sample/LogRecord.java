package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LogRecord implements EventListener{
    private final List<String> list;
    private ObservableList<String> observable;
    private ListView<String> listView;

    LogRecord(){
        this.list  = new ArrayList<>();
        this.observable = FXCollections.observableArrayList(list);
        this.listView = new ListView<>(observable);
    }

    ListView<String> getListView() { return listView; }

    @Override
    public void update(String keyName){
        observable.clear();
        list.add(keyName);
        observable.addAll(list);
        listView.setItems(observable);
    }
}
