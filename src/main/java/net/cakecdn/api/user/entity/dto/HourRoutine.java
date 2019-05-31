package net.cakecdn.api.user.entity.dto;

import lombok.Getter;

@Getter
public enum HourRoutine {

    ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6),
    SEVEN(7), EIGHT(8), NINE(9), TEN(10), ELEVEN(11), TWELVE(12),
    THIRTEEN(13), FOURTEEN(14), FIFTEEN(15), SIXTEEN(16), SEVENTEEN(17), EIGHTEEN(18),
    NINETEEN(19), TWENTY(20), TWENTY_ONE(21), TWENTY_TWO(22), TWENTY_THREE(23);

    private int hour;

    HourRoutine(int hour) {
        this.hour = hour;
    }

    private int increase() {
        int hour = this.hour;
        if (hour == 23) {
            return 0;
        } else return hour + 1;
    }

    private int increase(int hours) {
        for (int i = 0; i < hours; i++) {
            hour = this.increase();
        }
        return hour;
    }

    private int decrease() {
        int hour = this.hour;
        if (hour == 0) {
            return 23;
        } else return hour - 1;
    }

    private int decrease(int hours) {
        for (int i = 0; i > hours; i--) {
            hour = this.decrease();
        }
        return hour;
    }

    public HourRoutine hourIncrease() {
        return hour(this.increase());
    }

    public HourRoutine hourIncrease(int hours) {
        return hour(this.increase(hours));
    }

    public HourRoutine hourDecrease() {
        return hour(this.decrease());
    }

    public HourRoutine hourDecrease(int hours) {
        return hour(this.decrease(hours));
    }

    public HourRoutine hourStep(int hours) {
        if (hours > 0) {
            return this.hourIncrease(hours);
        } else {
            return this.hourDecrease(hours);
        }
    }

    public static HourRoutine hour(int hour) {
        switch (hour) {
            case 1:
                return ONE;
            case 2:
                return TWO;
            case 3:
                return THREE;
            case 4:
                return FOUR;
            case 5:
                return FIVE;
            case 6:
                return SIX;
            case 7:
                return SEVEN;
            case 8:
                return EIGHT;
            case 9:
                return NINE;
            case 10:
                return TEN;
            case 11:
                return ELEVEN;
            case 12:
                return TWELVE;
            case 13:
                return THIRTEEN;
            case 14:
                return FOURTEEN;
            case 15:
                return FIFTEEN;
            case 16:
                return SIXTEEN;
            case 17:
                return SEVENTEEN;
            case 18:
                return EIGHTEEN;
            case 19:
                return NINETEEN;
            case 20:
                return TWENTY;
            case 21:
                return TWENTY_ONE;
            case 22:
                return TWENTY_TWO;
            case 23:
                return TWENTY_THREE;
            default:
            case 0:
                return ZERO;
        }
    }

    public boolean hourEqual(int hour) {
        return hour == this.hour;
    }
}
