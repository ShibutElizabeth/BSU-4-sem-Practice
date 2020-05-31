package com.graviwave;

import com.graviwave.purpose.familyTrip.Excursion;
import com.graviwave.purpose.familyTrip.Relaxation;
import com.graviwave.purpose.friendsTrip.Extreme;
import com.graviwave.purpose.friendsTrip.Shopping;
import com.graviwave.purpose.honeymoon.Cruise;
import com.graviwave.purpose.singleTrip.Treatment;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;


public class Controller {
    final private Map<Integer, Map.Entry<Integer, Integer>> priceMap;
    private List<Voucher> model;

    public Controller() {
        model = new ArrayList<>();
        priceMap = new HashMap<>();
    }


    public boolean open(String path) {
        try {
            model.clear();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(path));
            NodeList voucherElements = document.getDocumentElement().getElementsByTagName("voucher");
            for (int i = 0; i < voucherElements.getLength(); i++) {
                Node voucher = voucherElements.item(i);

                // Получение атрибутов каждого элемента
                NamedNodeMap attributes = voucher.getAttributes();

                // Добавление. Атрибут - тоже Node, потому нам нужно получить значение атрибута с помощью метода getNodeValue()
                String pr = attributes.getNamedItem("price").getNodeValue();
                String d = attributes.getNamedItem("days").getNodeValue();
                String meal = attributes.getNamedItem("mealCounter").getNodeValue();
                String pl = attributes.getNamedItem("place").getNodeValue();
                int price = Integer.parseInt(pr);
                int days = Integer.parseInt(d);
                int mealCount = Integer.parseInt(meal);
                int place = Integer.parseInt(pl);
                String type = attributes.getNamedItem("type").getNodeValue();
                switch(type){
                    case "treatment":
                        model.add(new Treatment(attributes.getNamedItem("name").getNodeValue(), price,
                                attributes.getNamedItem("transport").getNodeValue(), days, mealCount, Voucher.Type.SINGLE, place));
                        break;
                    case "cruise":
                        model.add(new Cruise(attributes.getNamedItem("name").getNodeValue(), price,
                                attributes.getNamedItem("transport").getNodeValue(), days, mealCount, Voucher.Type.COUPLE, place));
                        break;
                    case "shopping":
                        model.add(new Shopping(attributes.getNamedItem("name").getNodeValue(), price,
                                attributes.getNamedItem("transport").getNodeValue(), days, mealCount, Voucher.Type.FRIENDS, place));
                        break;
                    case "extreme":
                        model.add(new Extreme(attributes.getNamedItem("name").getNodeValue(), price,
                                attributes.getNamedItem("transport").getNodeValue(), days, mealCount, Voucher.Type.FRIENDS, place));
                        break;
                    case "relaxation":
                        model.add(new Relaxation(attributes.getNamedItem("name").getNodeValue(), price,
                                attributes.getNamedItem("transport").getNodeValue(), days, mealCount, Voucher.Type.FAMILY, place));
                        break;
                    case "excursion":
                        model.add(new Excursion(attributes.getNamedItem("name").getNodeValue(), price,
                                attributes.getNamedItem("transport").getNodeValue(), days, mealCount, Voucher.Type.FAMILY, place));
                        break;
                }
            }
            priceMap.clear();
            for (Voucher voucher : model) {
                if (priceMap.containsKey(voucher.getMealCount())) {
                    Map.Entry<Integer, Integer> item = priceMap.get(voucher.getMealCount());
                    priceMap.replace(voucher.getMealCount(), Map.entry(Math.min(voucher.getPrice(), item.getKey()), Math.max(voucher.getPrice(),
                            item.getValue())));
                } else {
                    priceMap.put(voucher.getMealCount(), Map.entry(voucher.getPrice(), voucher.getPrice()));
                }
            }
            //showAll(model);

            return true;
        } catch (Exception e) {
            showError(e);
            return false;
        }
    }

    public void showError(Exception e) {
        System.out.println("ERROR!");
    }
    public void showAll(List<Voucher> sorted){
        for(int i = 0; i < sorted.size(); i++){
            System.out.println(sorted.get(i).toString());
        }

    }
    public void showValuable(List<Voucher> unsorted) {
        for(int i = 0; i < unsorted.size(); i++){
            System.out.println(unsorted.get(i).toString());
        }
    }
    public void showT(List<Voucher> tr) {
        for (Voucher voucher : tr) {
            System.out.println(voucher.toString());
        }
    }

    public void showList(String org, String org2) {
        List<Voucher> sorted = new ArrayList<>(model);
        Collections.copy(sorted, model);
        int leftBound = Integer.parseInt(org);
        int rightBound = Integer.parseInt(org2);
        sorted = sorted.stream().filter(a -> a.getPrice() >= leftBound && a.getPrice() <= rightBound).collect(Collectors.toList());
        sorted.sort(new ByPriceComparator());
        showValuable(sorted);

    }

    public void Transport(String org){
        List<Voucher> list = new ArrayList<>(model);
        Collections.copy(list, model);
        list = list.stream().filter(a -> a.getTransport().equals(org)).collect(Collectors.toList());
        Collections.sort(list, new ByPriceComparator());
        showT(list);
    }
    public void Meals(String org){
        List<Voucher> list = new ArrayList<>(model);
        Collections.copy(list, model);
        list = list.stream().filter(a -> a.getMealCount() == (Integer.parseInt(org))).collect(Collectors.toList());
        Collections.sort(list, new ByPriceComparator());
        showT(list);
    }
    public void Days(String org){
        List<Voucher> list = new ArrayList<>(model);
        Collections.copy(list, model);
        list = list.stream().filter(a -> a.getDays() == (Integer.parseInt(org))).collect(Collectors.toList());
        Collections.sort(list, new ByPriceComparator());
        showT(list);
    }
    public void showFilter() throws BadInputException{
        try {
            System.out.println("******  FILTER  BY  ******");
            System.out.println("1. price");
            System.out.println("2. transport");
            System.out.println("3. meals");
            System.out.println("4. number of days");
            System.out.println("Your choice: ");
            Scanner choice = new Scanner(System.in);
            int key = choice.nextInt();
            if (key < 1 || key > 4) {
                throw new BadInputException("Wrong key!");
            }
            switch (key) {
                case 1:
                    System.out.println("Enter price limits");
                    System.out.println("From: ");
                    Scanner l = new Scanner(System.in);
                    String from = l.next();
                    System.out.println("to: ");
                    Scanner r = new Scanner(System.in);
                    String to = r.next();
                    showList(from, to);
                    break;//price
                case 2:
                    System.out.println("Enter type of transport");
                    Scanner tr = new Scanner(System.in);
                    String trans = tr.next();
                    Transport(trans);
                    break;//Transport
                case 3:
                    System.out.println("Enter the number of meals");
                    Scanner m = new Scanner(System.in);
                    String meal = m.next();
                    Meals(meal);
                    break;//meals
                case 4:
                    System.out.println("Enter the number of days");
                    Scanner d = new Scanner(System.in);
                    String day = d.next();
                    Days(day);
                    break;//days
            }
        }
        catch(NumberFormatException e){
            showError(e);
            System.out.println("Incorrect input!");
            menu();
        }
    }

    public List<Voucher> getList() {
        List<Voucher> list = new ArrayList<>(model);
        return list;
    }

    public void menu(){
        System.out.println("*****   MENU    *****");
        System.out.println("1. View all suggestions.");
        System.out.println("2. Filter vouchers.");
        System.out.println("3. Exit.");
        Scanner in = new Scanner(System.in);
        int key = in.nextInt();
        switch (key){
            case 1:
                System.out.println("******  ALL SUGGESTIONS ******");
                showAll(model);
                menu();
                break;
            case 2:
                showFilter();
                menu();
                break;
            case 3:
                break;

        }
    }

}