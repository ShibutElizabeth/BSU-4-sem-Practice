package com.graviwave.purpose.familyTrip;

import com.graviwave.BadInputException;
import com.graviwave.Locations.Mountains;
import com.graviwave.Locations.Sea;
import com.graviwave.Locations.Village;
import com.graviwave.Voucher;

import java.util.Objects;

public class Relaxation extends Voucher implements Village, Mountains, Sea {
    private int restPlaces;
    public Relaxation(String name, int price, String transport, int days, int mealCount, Type type, int restPlaces)throws BadInputException {
        super(name, price, transport, days, mealCount, Type.FAMILY);
        this.restPlaces = restPlaces;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Relaxation ||| ").append(super.toString()).append(" | Number of rest places: ").append(restPlaces).append("\n");
        return sb.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Relaxation)) return false;
        if (!super.equals(o)) return false;
        Relaxation trip = (Relaxation) o;
        return Integer.compare(trip.restPlaces, restPlaces) == 0;
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), restPlaces);
    }

    public int getRestPlaces() {
        return restPlaces;
    }
}
