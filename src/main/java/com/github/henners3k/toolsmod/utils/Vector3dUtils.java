package com.github.henners3k.toolsmod.utils;

import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Vector3d;

public final class Vector3dUtils {
    public static Vector3d abs(Vector3d v) {
        return new Vector3d(Math.abs(v.x), Math.abs(v.y), Math.abs(v.z));
    }
}
