package com.graviwave.purpose.singleTrip;

import com.graviwave.BadInputException;
import com.graviwave.Locations.Sea;
import com.graviwave.Locations.Village;
import com.graviwave.Voucher;

import java.util.Objects;

public class Treatment extends Voucher implements Village, Sea {
    private int procedures;

    public Treatment(String name, int price, String transport, int days, int mealCount, Type type, int procedures)throws BadInputException {
        super(name, price, transport, days, mealCount, Type.SINGLE);
        this.procedures = procedures;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Treatment ||| ").append(super.toString()).append(" | Number of procedures: ").append(procedures).append("\n");
        return sb.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Treatment)) return false;
        if (!super.equals(o)) return false;
        Treatment trip = (Treatment) o;
        return Integer.compare(trip.procedures, procedures) == 0;
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), procedures);
    }
    public int getProcedures() {
        return procedures;
    }
}
