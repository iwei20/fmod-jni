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
 * struct FMOD_STUDIO_BUFFER_USAGE {
 *     FMOD_STUDIO_BUFFER_INFO studiocommandqueue;
 *     FMOD_STUDIO_BUFFER_INFO studiohandle;
 * }
 * }
 */
public class FMOD_STUDIO_BUFFER_USAGE {

    FMOD_STUDIO_BUFFER_USAGE() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        FMOD_STUDIO_BUFFER_INFO.layout().withName("studiocommandqueue"),
        FMOD_STUDIO_BUFFER_INFO.layout().withName("studiohandle")
    ).withName("FMOD_STUDIO_BUFFER_USAGE");

    /**
     * The layout of this struct
     */
    public static final GroupLayout layout() {
        return $LAYOUT;
    }

    private static final GroupLayout studiocommandqueue$LAYOUT = (GroupLayout)$LAYOUT.select(groupElement("studiocommandqueue"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * FMOD_STUDIO_BUFFER_INFO studiocommandqueue
     * }
     */
    public static final GroupLayout studiocommandqueue$layout() {
        return studiocommandqueue$LAYOUT;
    }

    private static final long studiocommandqueue$OFFSET = $LAYOUT.byteOffset(groupElement("studiocommandqueue"));

    /**
     * Offset for field:
     * {@snippet lang=c :
     * FMOD_STUDIO_BUFFER_INFO studiocommandqueue
     * }
     */
    public static final long studiocommandqueue$offset() {
        return studiocommandqueue$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * FMOD_STUDIO_BUFFER_INFO studiocommandqueue
     * }
     */
    public static MemorySegment studiocommandqueue(MemorySegment struct) {
        return struct.asSlice(studiocommandqueue$OFFSET, studiocommandqueue$LAYOUT.byteSize());
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * FMOD_STUDIO_BUFFER_INFO studiocommandqueue
     * }
     */
    public static void studiocommandqueue(MemorySegment struct, MemorySegment fieldValue) {
        MemorySegment.copy(fieldValue, 0L, struct, studiocommandqueue$OFFSET, studiocommandqueue$LAYOUT.byteSize());
    }

    private static final GroupLayout studiohandle$LAYOUT = (GroupLayout)$LAYOUT.select(groupElement("studiohandle"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * FMOD_STUDIO_BUFFER_INFO studiohandle
     * }
     */
    public static final GroupLayout studiohandle$layout() {
        return studiohandle$LAYOUT;
    }

    private static final long studiohandle$OFFSET = $LAYOUT.byteOffset(groupElement("studiohandle"));

    /**
     * Offset for field:
     * {@snippet lang=c :
     * FMOD_STUDIO_BUFFER_INFO studiohandle
     * }
     */
    public static final long studiohandle$offset() {
        return studiohandle$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * FMOD_STUDIO_BUFFER_INFO studiohandle
     * }
     */
    public static MemorySegment studiohandle(MemorySegment struct) {
        return struct.asSlice(studiohandle$OFFSET, studiohandle$LAYOUT.byteSize());
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * FMOD_STUDIO_BUFFER_INFO studiohandle
     * }
     */
    public static void studiohandle(MemorySegment struct, MemorySegment fieldValue) {
        MemorySegment.copy(fieldValue, 0L, struct, studiohandle$OFFSET, studiohandle$LAYOUT.byteSize());
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

