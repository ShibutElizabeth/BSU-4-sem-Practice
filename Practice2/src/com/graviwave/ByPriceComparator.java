package com.graviwave;

import com.graviwave.Voucher;

import java.util.Comparator;

public class ByPriceComparator implements Comparator<Voucher> {
    public int compare(Voucher first, Voucher second) {
        return Double.compare(first.getPrice(), second.getPrice());
    }
}
