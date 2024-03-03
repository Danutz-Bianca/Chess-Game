package com.danutz_bia.chessgame.domain;

public enum Color {
    WHITE{
        @Override
        public int directionPices() {
            return -1;
        }
        @Override
        public boolean isBlack() {
            return false;
        }
        @Override
        public boolean isWhite() {
            return true;
        }
    },
    BLACK{
        @Override
        public int directionPices() {
            return 1;
        }
        @Override
        public boolean isWhite() {
            return false;
        }
        @Override
        public boolean isBlack() {
            return true;
        }
    };
    public abstract int directionPices();
    public abstract boolean isBlack();
    public abstract boolean isWhite();
}
