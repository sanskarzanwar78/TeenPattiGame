package com.teenpatti.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MoneyUtils {
    private static final int DEFAULT_SCALE = 2;
    
    public static BigDecimal round(BigDecimal amount) {
        return amount.setScale(DEFAULT_SCALE, RoundingMode.HALF_UP);
    }
    
    public static boolean isNegative(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) < 0;
    }
    
    public static boolean isPositive(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) > 0;
    }
    
    public static boolean isZero(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) == 0;
    }
    
    public static BigDecimal max(BigDecimal a, BigDecimal b) {
        return a.compareTo(b) >= 0 ? a : b;
    }
    
    public static BigDecimal min(BigDecimal a, BigDecimal b) {
        return a.compareTo(b) <= 0 ? a : b;
    }
}