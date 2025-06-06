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
 * struct FMOD_STUDIO_BUFFER_INFO {
 *     int currentusage;
 *     int peakusage;
 *     int capacity;
 *     int stallcount;
 *     float stalltime;
 * }
 * }
 */
public class FMOD_STUDIO_BUFFER_INFO {

    FMOD_STUDIO_BUFFER_INFO() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        fmod_studio_h.C_INT.withName("currentusage"),
        fmod_studio_h.C_INT.withName("peakusage"),
        fmod_studio_h.C_INT.withName("capacity"),
        fmod_studio_h.C_INT.withName("stallcount"),
        fmod_studio_h.C_FLOAT.withName("stalltime")
    ).withName("FMOD_STUDIO_BUFFER_INFO");

    /**
     * The layout of this struct
     */
    public static final GroupLayout layout() {
        return $LAYOUT;
    }

    private static final OfInt currentusage$LAYOUT = (OfInt)$LAYOUT.select(groupElement("currentusage"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int currentusage
     * }
     */
    public static final OfInt currentusage$layout() {
        return currentusage$LAYOUT;
    }

    private static final long currentusage$OFFSET = $LAYOUT.byteOffset(groupElement("currentusage"));

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int currentusage
     * }
     */
    public static final long currentusage$offset() {
        return currentusage$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int currentusage
     * }
     */
    public static int currentusage(MemorySegment struct) {
        return struct.get(currentusage$LAYOUT, currentusage$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int currentusage
     * }
     */
    public static void currentusage(MemorySegment struct, int fieldValue) {
        struct.set(currentusage$LAYOUT, currentusage$OFFSET, fieldValue);
    }

    private static final OfInt peakusage$LAYOUT = (OfInt)$LAYOUT.select(groupElement("peakusage"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int peakusage
     * }
     */
    public static final OfInt peakusage$layout() {
        return peakusage$LAYOUT;
    }

    private static final long peakusage$OFFSET = $LAYOUT.byteOffset(groupElement("peakusage"));

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int peakusage
     * }
     */
    public static final long peakusage$offset() {
        return peakusage$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int peakusage
     * }
     */
    public static int peakusage(MemorySegment struct) {
        return struct.get(peakusage$LAYOUT, peakusage$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int peakusage
     * }
     */
    public static void peakusage(MemorySegment struct, int fieldValue) {
        struct.set(peakusage$LAYOUT, peakusage$OFFSET, fieldValue);
    }

    private static final OfInt capacity$LAYOUT = (OfInt)$LAYOUT.select(groupElement("capacity"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int capacity
     * }
     */
    public static final OfInt capacity$layout() {
        return capacity$LAYOUT;
    }

    private static final long capacity$OFFSET = $LAYOUT.byteOffset(groupElement("capacity"));

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int capacity
     * }
     */
    public static final long capacity$offset() {
        return capacity$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int capacity
     * }
     */
    public static int capacity(MemorySegment struct) {
        return struct.get(capacity$LAYOUT, capacity$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int capacity
     * }
     */
    public static void capacity(MemorySegment struct, int fieldValue) {
        struct.set(capacity$LAYOUT, capacity$OFFSET, fieldValue);
    }

    private static final OfInt stallcount$LAYOUT = (OfInt)$LAYOUT.select(groupElement("stallcount"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int stallcount
     * }
     */
    public static final OfInt stallcount$layout() {
        return stallcount$LAYOUT;
    }

    private static final long stallcount$OFFSET = $LAYOUT.byteOffset(groupElement("stallcount"));

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int stallcount
     * }
     */
    public static final long stallcount$offset() {
        return stallcount$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int stallcount
     * }
     */
    public static int stallcount(MemorySegment struct) {
        return struct.get(stallcount$LAYOUT, stallcount$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int stallcount
     * }
     */
    public static void stallcount(MemorySegment struct, int fieldValue) {
        struct.set(stallcount$LAYOUT, stallcount$OFFSET, fieldValue);
    }

    private static final OfFloat stalltime$LAYOUT = (OfFloat)$LAYOUT.select(groupElement("stalltime"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * float stalltime
     * }
     */
    public static final OfFloat stalltime$layout() {
        return stalltime$LAYOUT;
    }

    private static final long stalltime$OFFSET = $LAYOUT.byteOffset(groupElement("stalltime"));

    /**
     * Offset for field:
     * {@snippet lang=c :
     * float stalltime
     * }
     */
    public static final long stalltime$offset() {
        return stalltime$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * float stalltime
     * }
     */
    public static float stalltime(MemorySegment struct) {
        return struct.get(stalltime$LAYOUT, stalltime$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * float stalltime
     * }
     */
    public static void stalltime(MemorySegment struct, float fieldValue) {
        struct.set(stalltime$LAYOUT, stalltime$OFFSET, fieldValue);
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

