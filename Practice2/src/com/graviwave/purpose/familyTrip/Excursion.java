package com.graviwave.purpose.familyTrip;

import com.graviwave.BadInputException;
import com.graviwave.Locations.BigCity;
import com.graviwave.Locations.Tropics;
import com.graviwave.Locations.Village;
import com.graviwave.Voucher;

import java.util.Objects;

public class Excursion extends Voucher implements BigCity, Village, Tropics {
    private int attractions;
    public Excursion(String name, int price, String transport, int days, int mealCount, Type type, int attractions)throws BadInputException {
        super(name, price, transport, days, mealCount, Type.FAMILY);
        this.attractions = attractions;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Excursion ||| ").append(super.toString()).append(" | Number of attractions: ").append(attractions).append("\n");
        return sb.toString() ;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Excursion)) return false;
        if (!super.equals(o)) return false;
        Excursion trip = (Excursion) o;
        return Integer.compare(trip.attractions, attractions) == 0;
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), attractions);
    }
    public int getAttractions() {
        return attractions;
    }

}
