package com.graviwave.purpose.friendsTrip;

import com.graviwave.BadInputException;
import com.graviwave.Locations.BigCity;
import com.graviwave.Voucher;

import java.util.Objects;

public class Shopping extends Voucher implements BigCity {
    private int shops;
    public Shopping(String name, int price, String transport, int days, int mealCount, Type type, int shops)throws BadInputException {
        super(name, price,  transport, days, mealCount, Type.FRIENDS);
        this.shops = shops;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Shopping ||| ").append(super.toString()).append(" | Number of shops: ").append(shops).append("\n");
        return sb.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shopping)) return false;
        if (!super.equals(o)) return false;
        Shopping trip = (Shopping) o;
        return Integer.compare(trip.shops, shops) == 0;
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), shops);
    }
    public int getShops() {
        return shops;
    }
}
