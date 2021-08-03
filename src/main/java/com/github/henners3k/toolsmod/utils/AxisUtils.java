package com.github.henners3k.toolsmod.utils;

import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Vector3d;

public final class AxisUtils {
    public static Direction.Axis maxAxis(Vector3d v) {
        double max = Math.max(Math.max(v.x, v.y), v.z);

        if (max == v.x)
            return Direction.Axis.X;
        else if (max == v.y)
            return Direction.Axis.Y;
        else if (max == v.z)
            return Direction.Axis.Z;

        throw new Error("AxisUtils.max() failed");
    }

    public static Direction.Axis maxAbsAxis(Vector3d v) {
        return maxAxis(Vector3dUtils.abs(v));
    }

    public static Direction.Axis minAxis(Vector3d v) {
        double min = Math.min(Math.min(v.x, v.y), v.z);

        if (min == v.x)
            return Direction.Axis.X;
        else if (min == v.y)
            return Direction.Axis.Y;
        else if (min == v.z)
            return Direction.Axis.Z;

        throw new Error("AxisUtils.min() failed");
    }

    public static Direction.Axis minAbsAxis(Vector3d v) {
        return minAxis(Vector3dUtils.abs(v));
    }

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
