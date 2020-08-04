package me.nullicorn.minekartz.kart;

import lombok.Getter;

/**
 * Combinations of keyboard controls that can be pressed while riding a kart
 *
 * @author Nullicorn
 */
@Getter
enum PressedControls {
    NONE(false, false, false, false), // No keys pressed

    W(true, false, false, false), // Forward
    A(false, false, true, false), // Left
    S(false, true, false, false), // Back
    D(false, false, false, true), // Right

    WD(true, false, false, true), // Forward & Right
    WA(true, false, true, false), // Forward & Left

    SD(false, true, false, true), // Back & Right
    SA(false, true, true, false); // Back & Left

    private final boolean isForward;
    private final boolean isBackward;
    private final boolean isLeft;
    private final boolean isRight;

    PressedControls(boolean isForward, boolean isBackward, boolean isLeft, boolean isRight) {
        this.isForward = isForward;
        this.isBackward = isBackward;
        this.isLeft = isLeft;
        this.isRight = isRight;
    }

    /**
     * Get the pressed controls from a STEER_VEHICLE packet's "forward" and "sideways" values
     *
     * <ul>
     *     <li>If "forward" is positive, the W key is pressed (move forward)</li>
     *     <li>If "forward" is negative, the S key is pressed (move backward)</li>
     *     <li>If "forward" is zero, neither or both are pressed (not moving)</li>
     *     -------------------------------
     *     <li>If "sideways" is positive, the A key is pressed (turn left)</li>
     *     <li>If "sideways" is negative, the D key is pressed (turn right)</li>
     *     <li>If "sideways" is zero, neither or both are pressed (not steering)</li>
     * </ul>
     */
    public static PressedControls from(float forward, float sideways) {
        if (forward > 0) {
            // Driving forwards
            if (sideways < 0) {
                return WD;
            } else if (sideways > 0) {
                return WA;
            }
            return W;

        } else if (forward == 0) {
            // Steering in place
            if (sideways < 0) {
                return D;
            } else if (sideways > 0) {
                return A;
            }
            return NONE;

        } else if (forward < 0) {
            // Driving backwards
            if (sideways < 0) {
                return SD;
            } else if (sideways > 0) {
                return SA;
            }
            return S;
        }

        // Shouldn't be reached
        return NONE;
    }
}
