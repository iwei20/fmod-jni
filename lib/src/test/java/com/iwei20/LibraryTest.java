/*
 * This source file was generated by the Gradle 'init' task
 */
package com.iwei20;

import static com.iwei20.fmod.gen.fmodstudio.fmod_studio_h.*;
import static org.junit.jupiter.api.Assertions.*;

import com.iwei20.fmod.FModLoad;
import com.iwei20.fmod.studio.FMODSystem;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class LibraryTest {

    @Test
    void load() {
        try {
            FModLoad.load();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Test
    void loadDebug() {
        try {
            FModLoad.enableLogging();
            FModLoad.load();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Test
    void loadCreateRelease() {
        try {
            FModLoad.enableLogging();
            FModLoad.load();
        } catch (IOException e) {
            throw new RuntimeException();
        }
        FMODSystem system = new FMODSystem();
        system.close();
    }
}
