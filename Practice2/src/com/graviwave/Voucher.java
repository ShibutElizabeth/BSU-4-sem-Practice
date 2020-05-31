package com.graviwave;

import java.util.Objects;

public abstract class Voucher {
    private String name;
    private int price;
    private int days;
    private int mealCount;
    private String transport;
    public enum Type{FAMILY, FRIENDS, COUPLE, SINGLE}
    private Type type;

    public Voucher(String name, int price, String transport, int days, int mealCount, Type type)throws BadInputException {
        if(name.equals(""))
            throw new BadInputException("Name cannot be empty!");
        if(price < 0)
            throw new BadInputException("Price cannot be negative!");
        if(days <= 0)
            throw new BadInputException("Wrong number of days!");
        if(transport == "")
            throw new BadInputException("Name cannot be empty!");
        if(mealCount < 0)
            throw new BadInputException("Price cannot be negative!");

        this.name = name;
        this.price = price;
        this.transport = transport;
        this.days = days;
        this.mealCount = mealCount;
        this.type = type;
    }

    public int getPrice() { return price; }

    public int getDays() { return days; }

    public int getMealCount() { return mealCount; }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public String getTransport() { return transport; }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("| Name of voucher: ").append(name).append(" | Price: ").append(price).
                append(" | Transport: ").append(transport).append(" | Number of days: ").
                append(days).append(" | Number of meals a day: ").append(mealCount);
       return sb.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Voucher)) return false;
        Voucher voucher = (Voucher) o;
        return name.equals(voucher.name) &&
                Integer.compare(voucher.price, price) == 0 &&
                transport.equals(voucher.transport) &&
                Integer.compare(voucher.days, days) == 0 &&
                Integer.compare(voucher.mealCount, mealCount) == 0 &&
                type.equals(voucher.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, days, mealCount, type);
    }


}
