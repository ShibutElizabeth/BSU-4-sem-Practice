package com.graviwave.purpose.honeymoon;

import com.graviwave.BadInputException;
import com.graviwave.Locations.Sea;
import com.graviwave.Voucher;

import java.util.Objects;

public class Cruise extends Voucher implements Sea {
    private int romanticPlaces;
    public Cruise(String name, int price, String transport, int days, int mealCount, Type type, int romanticPlaces)throws BadInputException {
        super(name, price, transport, days, mealCount, Type.COUPLE);
        this.romanticPlaces = romanticPlaces;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Cruise ||| ").append(super.toString()).append(" | Number of romantic places: ").append(romanticPlaces).append("\n");
        return sb.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cruise)) return false;
        if (!super.equals(o)) return false;
        Cruise trip = (Cruise) o;
        return Integer.compare(trip.romanticPlaces, romanticPlaces) == 0;
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), romanticPlaces);
    }
    public int getRomanticPlaces() {
        return romanticPlaces;
    }
}
