package com.iwei20.fmod.studio;

import static com.iwei20.fmod.gen.fmodstudio.fmod_studio_h.*;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

public class FMODSystem implements AutoCloseable {

    private final Arena arena;
    private final MemorySegment systemPointer;

    /**
     * Calls Studio::System::create.
     *
     * {@link FMODSystem#FMODSystem} and {@link FMODSystem#close} are not thread-safe. Calling
     * either of these functions concurrently with any Studio API function (including
     * these two functions) may cause undefined behavior. External synchronization
     * must be used if calls to {@link FMODSystem#FMODSystem} or {@link FMODSystem#close} could
     * overlap other Studio API calls. All other Studio API functions are thread safe
     * and may be called freely from any thread unless otherwise documented.
     */
    public FMODSystem() throws FMODException {
        arena = Arena.ofAuto();

        MemorySegment systemPointerPointer = arena.allocate(C_POINTER);
        int result = FMOD_Studio_System_Create(systemPointerPointer, FMOD_VERSION());
        FMODException.errCheck(result);

        systemPointer = systemPointerPointer.get(C_POINTER, 0);
    }

    /**
     * This function will free the memory used by the Studio System object and everything
     * created under it.
     *
     * {@link FMODSystem#FMODSystem} and {@link FMODSystem#close} are not thread-safe. Calling
     * either of these functions concurrently with any Studio API function (including
     * these two functions) may cause undefined behavior. External synchronization
     * must be used if calls to {@link FMODSystem#FMODSystem} or {@link FMODSystem#close} could
     * overlap other Studio API calls. All other Studio API functions are thread safe
     * and may be called freely from any thread unless otherwise documented.
     */
    @Override
    public void close() throws FMODException {
        int result = FMOD_Studio_System_Release(systemPointer);
        FMODException.errCheck(result);
    }

    public static record AdvancedSettings(
            int commandQueueSize,
            int handleInitialSize,
            int studioUpdatePeriod,
            int idleSampleDatapoolSize,
            int streamingScheduleDelay,
            String encryptionKey) {}

    /**
     * <ul>
     * <li>InitNormal - Use defaults for all initialization options.
     * <li>LiveUpdate - Enable live update.
     * <li>AllowMissingPlugins - Load banks even if they reference plug-ins
     * that have not been loaded.
     * <li>SynchronousUpdate - Disable asynchronous processing and perform
     * all processing on the calling thread instead.
     * <li>DeferredCallbacks - Defer timeline callbacks until the main update.
     * See EventInstance#setCallback for more information.
     * <li>LoadFromUpdate - No additional threads are created for bank and
     * resource loading. Loading is driven from System#update.
     * <li>MemoryTracking - Enables detailed memory usage statistics.
     * Increases memory footprint and impacts performance. See {@link Bus#getMemoryUsage}
     * and {@link EventInstance#getMemoryUsage} for more information. Implies
     * InitFlags.MemoryTracking.
     * </ul>
     *
     * @see FMODSystem#initialize
     */
    public static enum InitFlags {
        Normal(0x00000000),
        LiveUpdate(0x00000001),
        AllowMissingPlugins(0x00000002),
        SynchronousUpdate(0x00000004),
        DeferredCallbacks(0x00000008),
        LoadFromUpdate(0x00000010),
        MemoryTracking(0x00000020);

        private final int constant;

        private InitFlags(int constant) {
            this.constant = constant;
        }

        public int getConstant() {
            return constant;
        }
    }

    public void initialize() {}
}
