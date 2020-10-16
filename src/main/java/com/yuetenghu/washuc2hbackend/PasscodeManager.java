package com.yuetenghu.washuc2hbackend;

import java.util.HashSet;
import java.util.Set;

public class PasscodeManager {

    private static Set<String> inUsePasscodes = new HashSet<>();
    private static final String[] wordPool = new String[] {
        "AGE",
        "AIR",
        "AND",
        "ASK",
        "BIG",
        "BOY",
        "BOX",
        "BUS",
        "CAT",
        "CUP",
        "CAR",
        "CAN",
        "DAY",
        "DOG",
        "DRY",
        "EGG",
        "END",
        "FIX",
        "FLY",
        "FUN",
        "GAP",
        "GAS",
        "GET",
        "HEY",
        "HOT",
        "ICE",
        "JOB",
        "JOY",
        "KEY",
        "KID",
        "LAW",
        "LEG",
        "LOW",
        "MAP",
        "MAY",
        "MIX",
        "MUM",
        "NET",
        "NOW",
        "OFF",
        "OIL",
        "OUT",
        "PAY",
        "PRO",
        "RED",
        "SAY",
        "SIT",
        "SKY",
        "TAX",
        "TOP",
        "WAY",
        "WEB",
        "WIN"
    };

    public PasscodeManager() {

    }

    public static String generateNewPasscode() {
        removeOutdatedPasscodes();
        String randomPasscode = "TOP001";
        while (inUsePasscodes.contains(randomPasscode)) {
            String randomWord = wordPool[(int) (Math.random() * wordPool.length)];
            int randomNum = (int) (Math.random() * (1000 - 100)) + 100;
            randomPasscode = randomWord + randomNum;
        }
        inUsePasscodes.add(randomPasscode);
        return randomPasscode;
    }

    private static void removeOutdatedPasscodes() {
        if (inUsePasscodes.size() > wordPool.length * 999 / 3 * 2) {
            inUsePasscodes.clear();
        }
    }

}
