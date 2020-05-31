package com.graviwave.purpose.friendsTrip;

import com.graviwave.BadInputException;
import com.graviwave.Locations.Mountains;
import com.graviwave.Locations.Sea;
import com.graviwave.Locations.Tropics;
import com.graviwave.Voucher;
import com.graviwave.purpose.familyTrip.Relaxation;

import java.util.Objects;

public class Extreme extends Voucher implements Mountains, Sea, Tropics {
    private int quests;
    public Extreme(String name, int price, String transport, int days, int mealCount, Type type, int quests)throws BadInputException {
        super(name, price, transport, days, mealCount, Type.FRIENDS);
        this.quests = quests;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Extreme ||| ").append(super.toString()).append(" | Number of quests: ").append(quests).append("\n");
        return sb.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Extreme)) return false;
        if (!super.equals(o)) return false;
        Extreme trip = (Extreme) o;
        return Integer.compare(trip.quests, quests) == 0;
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), quests);
    }
    public int getQuests() {
        return quests;
    }
}
