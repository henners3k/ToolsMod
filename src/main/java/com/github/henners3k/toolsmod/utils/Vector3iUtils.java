package com.github.henners3k.toolsmod.utils;

import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Vector3i;

public class Vector3iUtils {

    public static class Builder {
        int xVal = 0;
        int yVal = 0;
        int zVal = 0;

        public Builder() {
        }

        public Vector3i build() {
            return new Vector3i(xVal, yVal, zVal);
        }

        public Builder x(int val) {
            xVal = val;
            return this;
        }

        public Builder y(int val) {
            yVal = val;
            return this;
        }

        public Builder z(int val) {
            zVal = val;
            return this;
        }

        public Builder with(Direction.Axis axis, int val) {
            switch (axis) {
                case X:
                    return x(val);
                case Y:
                    return y(val);
                case Z:
                    return z(val);
            }
            return this;
        }

    }


}
