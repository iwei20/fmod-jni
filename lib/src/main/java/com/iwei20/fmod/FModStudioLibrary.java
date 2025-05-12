package com.iwei20.fmod;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public interface FModStudioLibrary extends Library {
    FModStudioLibrary INSTANCE = (FModStudioLibrary)Native.load("fmodstudioL", FModStudioLibrary.class);

    // Hardcoded for now
    int FMOD_VERSION = 0x00020307;

    int FMOD_Studio_System_Create(Pointer systemPointer, int headerversion);
    int FMOD_Studio_System_Release(Pointer system);
}
