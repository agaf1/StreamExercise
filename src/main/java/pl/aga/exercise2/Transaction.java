package pl.aga.exercise2;

import lombok.Value;

@Value
public class Transaction {

    private final Trader trader;
    private final int year;
    private final int value;

}
