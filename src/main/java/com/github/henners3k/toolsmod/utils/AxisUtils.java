package com.github.henners3k.toolsmod.utils;

import net.minecraft.util.Direction;

public final class AxisUtils {

    public static Direction.Axis other(Direction.Axis a, Direction.Axis b) {
        switch (a) {
            case X:
                return b == Direction.Axis.Y ? Direction.Axis.Z : Direction.Axis.Y;
            case Y:
                return b == Direction.Axis.X ? Direction.Axis.Z : Direction.Axis.X;
            case Z:
                return b == Direction.Axis.X ? Direction.Axis.Y : Direction.Axis.X;
        }

        throw new Error("AxisUtils.other() failed");
    }
}
