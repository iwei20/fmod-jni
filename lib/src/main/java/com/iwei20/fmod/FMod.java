package com.iwei20.fmod;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface FMod extends Library {
    FMod INSTANCE = (FMod)Native.load("fmodL", FMod.class);
}
