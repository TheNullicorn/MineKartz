package me.nullicorn.minekartz.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Nullicorn
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MathUtil {

    /**
     * @return The original float value clamped between the maximum and minimum
     */
    public static float clampF(float value, float min, float max) {
        return Math.min(max, Math.max(min, value));
    }

    /**
     * Shift a value closer to zero by the specified amount
     *
     * @return The shifted value, or zero if the value was already zero
     */
    public static float moveTowardsZero(float value, float amount) {
        if ((value > 0 && value < amount) || (value < 0 && value > -amount)) {
            // If the value is already closer to zero than amount, set value right to zero
            return 0;

        } else if (value != 0) {
            // Increase/decrease the value by amount so that value is closer to zero
            return value + (value > 0 ? -amount : amount);

        } else {
            // Value is already zero; ignore
            return 0;
        }
    }
}
