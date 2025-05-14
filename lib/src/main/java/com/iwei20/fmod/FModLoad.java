package com.iwei20.fmod;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;

public class FModLoad {
    // Operating systems
    private static final String WINDOWS = "windows";
    private static final String MAC = "mac";
    private static final String LINUX = "linux";

    // Architecture folder names
    private static final String X86_64 = "x86_64";
    private static final String X86 = "x86";
    private static final String ARM64 = "arm64";
    private static final String ARM = "arm";

    // Matched against osArch
    private static final String[] X86_64_ALIASES = {"amd64", "x86-64", "x86_64", "x64" };
    private static final String[] X86_ALIASES = {"ia32", "i386", "i486", "i586", "i686", "x86"};
    private static final String[] ARM64_ALIASES = {"arm64", "aarch64", "arm64-v8a"};
    private static final String[] ARM_ALIASES = {"arm", "armeabi-v7a"};

    private static String withSlashes(String finalOsName, String finalOsArch) {
        return "/" + finalOsName + "/" + finalOsArch + "/";
    }

    /**
     * Android, iOS, and HTML5 are not planned to be supported,
     * because the usecase doesn't exist.
     *
     * <h4>Possible resource paths:</h4>
     * <ul>
     * <li>/windows/x86_64/
     * <li>/windows/x86/
     * <li>/windows/arm64/
     * <li>/mac/
     * <li>/linux/x86_64/
     * <li>/linux/x86/
     * <li>/linux/arm64/
     * <li>/linux/arm/
     * </ul>
     *
     * @return the resource path corresponding to the operating system
     * of the current JVM and architecture, holding the dynamic
     * library files. The format is "/{osName}/{osArch}/".
     */
    private static String resourcePath() {
        String osName = System.getProperty("os.name");
        String osArch = System.getProperty("os.arch");
        String processedName = osName.toLowerCase().trim();

        if (processedName.startsWith(WINDOWS)) {
            if (Arrays.asList(X86_64_ALIASES).contains(osArch)) return withSlashes(WINDOWS, X86_64);
            else if (Arrays.asList(X86_ALIASES).contains(osArch)) return withSlashes(WINDOWS, X86);
            else if (Arrays.asList(ARM64_ALIASES).contains(osArch)) return withSlashes(WINDOWS, ARM64);
            else throw new UnsupportedOperationException("Unsupported architecture: " + osArch);
        } else if (processedName.startsWith(MAC)) {
            if (Arrays.asList(X86_64_ALIASES).contains(osArch)
                    || Arrays.asList(ARM64_ALIASES).contains(osArch)) {
                return "/" + MAC + "/";
            } else throw new UnsupportedOperationException("Unsupported architecture: " + osArch);
        } else if (processedName.startsWith(LINUX)) {
            if (Arrays.asList(X86_64_ALIASES).contains(osArch)) return withSlashes(LINUX, X86_64);
            else if (Arrays.asList(X86_ALIASES).contains(osArch)) return withSlashes(LINUX, X86);
            else if (Arrays.asList(ARM64_ALIASES).contains(osArch)) return withSlashes(LINUX, ARM64);
            else if (Arrays.asList(ARM_ALIASES).contains(osArch)) return withSlashes(LINUX, ARM);
            else throw new UnsupportedOperationException("Unsupported architecture: " + osArch);
        } else {
            throw new UnsupportedOperationException("Unsupported operating system: " + osName);
        }
    }

    private static String suffix(String mappedName) {
        return mappedName.split(".")[1];
    }

    private static String prefix(String mappedName) {
        return mappedName.split(".")[0];
    }

    private static boolean log = false;
    private static boolean loaded = false;

    /**
     * Causes loadLibraries() to load the "L" version of dynamic libraries
     * distributed by FMOD instead, which prints logging information to
     * stdout.
     */
    public static void enableLogging() {
        log = true;
    }

    private static final String FMOD = "fmod";
    private static final String STUDIO = "fmodstudio";
    private static final String FMODL = "fmodL";
    private static final String STUDIOL = "fmodstudioL";
    
    public static void loadFModLibraries() throws IOException {
        if (loaded) {
            return;
        }

        String fmod = log ? FMODL : FMOD;
        String studio = log ? STUDIOL : STUDIO;

        String mappedFMOD = System.mapLibraryName(fmod);
        String mappedStudio = System.mapLibraryName(studio);

        // Read dynamic libraries from resources folder
        InputStream fmodIn   = FModLoad.class.getResourceAsStream(resourcePath() + mappedFMOD);
        InputStream studioIn = FModLoad.class.getResourceAsStream(resourcePath() + mappedStudio);

        // Copy fmod to tempfile and load it
        File tempFMOD = File.createTempFile(prefix(mappedFMOD), suffix(mappedFMOD));
        tempFMOD.deleteOnExit();
        try (OutputStream out = new FileOutputStream(tempFMOD)) {
            IOUtils.copy(fmodIn, out);
        }
        System.load(tempFMOD.getAbsolutePath());

        // Copy fmodstudio to tempfile and load it
        File tempStudio = File.createTempFile(prefix(mappedStudio), suffix(mappedStudio));
        tempStudio.deleteOnExit();
        try (OutputStream out = new FileOutputStream(tempStudio)) {
            IOUtils.copy(studioIn, out);
        }
        System.load(tempStudio.getAbsolutePath());

        loaded = true;
    }
}
