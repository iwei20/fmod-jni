// Generated by jextract

package com.iwei20.fmod.gen.fmodstudio;

import java.lang.invoke.*;
import java.lang.foreign.*;
import java.nio.ByteOrder;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static java.lang.foreign.ValueLayout.*;
import static java.lang.foreign.MemoryLayout.PathElement.*;

/**
 * {@snippet lang=c :
 * struct FMOD_DSP_BUFFER_ARRAY {
 *     int numbuffers;
 *     int *buffernumchannels;
 *     FMOD_CHANNELMASK *bufferchannelmask;
 *     float **buffers;
 *     FMOD_SPEAKERMODE speakermode;
 * }
 * }
 */
public class FMOD_DSP_BUFFER_ARRAY {

    FMOD_DSP_BUFFER_ARRAY() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        fmod_studio_h.C_INT.withName("numbuffers"),
        MemoryLayout.paddingLayout(4),
        fmod_studio_h.C_POINTER.withName("buffernumchannels"),
        fmod_studio_h.C_POINTER.withName("bufferchannelmask"),
        fmod_studio_h.C_POINTER.withName("buffers"),
        fmod_studio_h.C_INT.withName("speakermode"),
        MemoryLayout.paddingLayout(4)
    ).withName("FMOD_DSP_BUFFER_ARRAY");

    /**
     * The layout of this struct
     */
    public static final GroupLayout layout() {
        return $LAYOUT;
    }

    private static final OfInt numbuffers$LAYOUT = (OfInt)$LAYOUT.select(groupElement("numbuffers"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int numbuffers
     * }
     */
    public static final OfInt numbuffers$layout() {
        return numbuffers$LAYOUT;
    }

    private static final long numbuffers$OFFSET = $LAYOUT.byteOffset(groupElement("numbuffers"));

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int numbuffers
     * }
     */
    public static final long numbuffers$offset() {
        return numbuffers$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int numbuffers
     * }
     */
    public static int numbuffers(MemorySegment struct) {
        return struct.get(numbuffers$LAYOUT, numbuffers$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int numbuffers
     * }
     */
    public static void numbuffers(MemorySegment struct, int fieldValue) {
        struct.set(numbuffers$LAYOUT, numbuffers$OFFSET, fieldValue);
    }

    private static final AddressLayout buffernumchannels$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("buffernumchannels"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int *buffernumchannels
     * }
     */
    public static final AddressLayout buffernumchannels$layout() {
        return buffernumchannels$LAYOUT;
    }

    private static final long buffernumchannels$OFFSET = $LAYOUT.byteOffset(groupElement("buffernumchannels"));

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int *buffernumchannels
     * }
     */
    public static final long buffernumchannels$offset() {
        return buffernumchannels$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int *buffernumchannels
     * }
     */
    public static MemorySegment buffernumchannels(MemorySegment struct) {
        return struct.get(buffernumchannels$LAYOUT, buffernumchannels$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int *buffernumchannels
     * }
     */
    public static void buffernumchannels(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(buffernumchannels$LAYOUT, buffernumchannels$OFFSET, fieldValue);
    }

    private static final AddressLayout bufferchannelmask$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("bufferchannelmask"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * FMOD_CHANNELMASK *bufferchannelmask
     * }
     */
    public static final AddressLayout bufferchannelmask$layout() {
        return bufferchannelmask$LAYOUT;
    }

    private static final long bufferchannelmask$OFFSET = $LAYOUT.byteOffset(groupElement("bufferchannelmask"));

    /**
     * Offset for field:
     * {@snippet lang=c :
     * FMOD_CHANNELMASK *bufferchannelmask
     * }
     */
    public static final long bufferchannelmask$offset() {
        return bufferchannelmask$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * FMOD_CHANNELMASK *bufferchannelmask
     * }
     */
    public static MemorySegment bufferchannelmask(MemorySegment struct) {
        return struct.get(bufferchannelmask$LAYOUT, bufferchannelmask$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * FMOD_CHANNELMASK *bufferchannelmask
     * }
     */
    public static void bufferchannelmask(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(bufferchannelmask$LAYOUT, bufferchannelmask$OFFSET, fieldValue);
    }

    private static final AddressLayout buffers$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("buffers"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * float **buffers
     * }
     */
    public static final AddressLayout buffers$layout() {
        return buffers$LAYOUT;
    }

    private static final long buffers$OFFSET = $LAYOUT.byteOffset(groupElement("buffers"));

    /**
     * Offset for field:
     * {@snippet lang=c :
     * float **buffers
     * }
     */
    public static final long buffers$offset() {
        return buffers$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * float **buffers
     * }
     */
    public static MemorySegment buffers(MemorySegment struct) {
        return struct.get(buffers$LAYOUT, buffers$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * float **buffers
     * }
     */
    public static void buffers(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(buffers$LAYOUT, buffers$OFFSET, fieldValue);
    }

    private static final OfInt speakermode$LAYOUT = (OfInt)$LAYOUT.select(groupElement("speakermode"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * FMOD_SPEAKERMODE speakermode
     * }
     */
    public static final OfInt speakermode$layout() {
        return speakermode$LAYOUT;
    }

    private static final long speakermode$OFFSET = $LAYOUT.byteOffset(groupElement("speakermode"));

    /**
     * Offset for field:
     * {@snippet lang=c :
     * FMOD_SPEAKERMODE speakermode
     * }
     */
    public static final long speakermode$offset() {
        return speakermode$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * FMOD_SPEAKERMODE speakermode
     * }
     */
    public static int speakermode(MemorySegment struct) {
        return struct.get(speakermode$LAYOUT, speakermode$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * FMOD_SPEAKERMODE speakermode
     * }
     */
    public static void speakermode(MemorySegment struct, int fieldValue) {
        struct.set(speakermode$LAYOUT, speakermode$OFFSET, fieldValue);
    }

    /**
     * Obtains a slice of {@code arrayParam} which selects the array element at {@code index}.
     * The returned segment has address {@code arrayParam.address() + index * layout().byteSize()}
     */
    public static MemorySegment asSlice(MemorySegment array, long index) {
        return array.asSlice(layout().byteSize() * index);
    }

    /**
     * The size (in bytes) of this struct
     */
    public static long sizeof() { return layout().byteSize(); }

    /**
     * Allocate a segment of size {@code layout().byteSize()} using {@code allocator}
     */
    public static MemorySegment allocate(SegmentAllocator allocator) {
        return allocator.allocate(layout());
    }

    /**
     * Allocate an array of size {@code elementCount} using {@code allocator}.
     * The returned segment has size {@code elementCount * layout().byteSize()}.
     */
    public static MemorySegment allocateArray(long elementCount, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout()));
    }

    /**
     * Reinterprets {@code addr} using target {@code arena} and {@code cleanupAction} (if any).
     * The returned segment has size {@code layout().byteSize()}
     */
    public static MemorySegment reinterpret(MemorySegment addr, Arena arena, Consumer<MemorySegment> cleanup) {
        return reinterpret(addr, 1, arena, cleanup);
    }

    /**
     * Reinterprets {@code addr} using target {@code arena} and {@code cleanupAction} (if any).
     * The returned segment has size {@code elementCount * layout().byteSize()}
     */
    public static MemorySegment reinterpret(MemorySegment addr, long elementCount, Arena arena, Consumer<MemorySegment> cleanup) {
        return addr.reinterpret(layout().byteSize() * elementCount, arena, cleanup);
    }
}

