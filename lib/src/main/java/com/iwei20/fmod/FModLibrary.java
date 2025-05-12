package com.iwei20.fmod;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface FModLibrary extends Library {
    FModLibrary INSTANCE = (FModLibrary)Native.load("fmod", FModLibrary.class);
}
