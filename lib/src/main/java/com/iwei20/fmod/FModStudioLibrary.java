package com.iwei20.fmod;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public interface FModStudioLibrary extends Library {
    FModStudioLibrary INSTANCE = (FModStudioLibrary)Native.load("fmodstudio", FModStudioLibrary.class);

    int FMOD_Studio_System_Create(Pointer system, int headerversion);
}
