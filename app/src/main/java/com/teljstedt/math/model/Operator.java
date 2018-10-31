package com.teljstedt.math.model;

public enum Operator {
    Addition,
    Subtraktion,
    Multiplikation,
    Division;

    @Override
    public String toString() {
        switch(this){
            case Addition:
                return "+";
            case Subtraktion:
                return "-";
            case Multiplikation:
                return "*";
            case Division:
                return "/";
                default:
                    throw new IllegalArgumentException();
        }
    }
}
