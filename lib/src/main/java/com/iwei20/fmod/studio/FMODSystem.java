package com.iwei20.fmod.studio;

import static com.iwei20.fmod.gen.fmodstudio.fmod_studio_h.C_INT;
import static com.iwei20.fmod.gen.fmodstudio.fmod_studio_h.C_POINTER;
import static com.iwei20.fmod.gen.fmodstudio.fmod_studio_h.FMOD_Studio_System_Create;
import static com.iwei20.fmod.gen.fmodstudio.fmod_studio_h.FMOD_Studio_System_Release;
import static com.iwei20.fmod.gen.fmodstudio.fmod_studio_h.FMOD_VERSION;

import com.iwei20.fmod.gen.fmodstudio.FMOD_FILE_CLOSE_CALLBACK;
import com.iwei20.fmod.gen.fmodstudio.FMOD_FILE_OPEN_CALLBACK;
import com.iwei20.fmod.gen.fmodstudio.FMOD_FILE_READ_CALLBACK;
import com.iwei20.fmod.gen.fmodstudio.FMOD_FILE_SEEK_CALLBACK;
import com.iwei20.fmod.gen.fmodstudio.FMOD_STUDIO_ADVANCEDSETTINGS;
import com.iwei20.fmod.gen.fmodstudio.FMOD_STUDIO_BANK_INFO;
import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.util.Optional;

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
     *
     * @throws FMODException if the call the Studio::System::create fails.
     * @see https://www.fmod.com/docs/2.03/api/studio-guide.html#creating-the-studio-system
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
     *
     * @throws FMODException if the call to Studio::System::release fails.
     * @see https://www.fmod.com/docs/2.03/api/studio-guide.html#creating-the-studio-system
     */
    @Override
    public void close() throws FMODException {
        int result = FMOD_Studio_System_Release(systemPointer);
        FMODException.errCheck(result);
    }

    /**
     * When calling {@link FMODSystem#setAdvancedSettings}, any int member may
     * be set to zero, and any Optional member may be set empty, to use the default value
     * for that setting.
     *
     * @param commandQueueSize Command queue size for studio async processing. Units: Bytes. Default: 32768.
     * @param handleInitialSize Initial size to allocate for handles. Memory for handles will grow as needed in pages. Units: Bytes. Default: 8192 * sizeof(void*).
     * @param studioUpdatePeriod Update period of Studio when in async mode, in milliseconds. Will be quantized to the nearest multiple of mixer duration. Units: Milliseconds Default: 20.
     * @param idleSampleDatapoolSize Size in bytes of sample data to retain in memory when no longer used, to avoid repeated disk I/O. Use -1 to disable. Units: Bytes. Default: 262144.
     * @param streamingScheduleDelay Specify the schedule delay for streams, in samples. Lower values can reduce latency when scheduling events containing streams but may cause scheduling issues if too small. Units: Samples. Default: 8192.
     * @param encryptionKey Specify the key for loading sounds from encrypted banks. (UTF-8 string)
     */
    public static record AdvancedSettings(
            int commandQueueSize,
            int handleInitialSize,
            int studioUpdatePeriod,
            int idleSampleDatapoolSize,
            int streamingScheduleDelay,
            Optional<String> encryptionKey) {

        /**
         * Allocates a FMOD_STUDIO_ADVANCEDSETTINGS native struct corresponding
         * to the advanced settings set in this record.
         *
         * @param allocator The allocator used to allocate the struct
         * @return A memory segment containing the allocated struct
         */
        public MemorySegment allocate(SegmentAllocator allocator) {
            MemorySegment advancedSettingsStruct = FMOD_STUDIO_ADVANCEDSETTINGS.allocate(allocator);

            // Since there is no clear return type for sizeof anyway, this cast is OK.
            FMOD_STUDIO_ADVANCEDSETTINGS.cbsize(advancedSettingsStruct, (int) FMOD_STUDIO_ADVANCEDSETTINGS.sizeof());
            FMOD_STUDIO_ADVANCEDSETTINGS.commandqueuesize(advancedSettingsStruct, commandQueueSize);
            FMOD_STUDIO_ADVANCEDSETTINGS.handleinitialsize(advancedSettingsStruct, handleInitialSize);
            FMOD_STUDIO_ADVANCEDSETTINGS.studioupdateperiod(advancedSettingsStruct, studioUpdatePeriod);
            FMOD_STUDIO_ADVANCEDSETTINGS.idlesampledatapoolsize(advancedSettingsStruct, idleSampleDatapoolSize);
            FMOD_STUDIO_ADVANCEDSETTINGS.streamingscheduledelay(advancedSettingsStruct, streamingScheduleDelay);

            MemorySegment encryptionKeyPointer = encryptionKey
                    .map((String key) -> allocator.allocateFrom(key))
                    .orElse(allocator.allocate(C_POINTER).fill((byte) 0));
            FMOD_STUDIO_ADVANCEDSETTINGS.encryptionkey(advancedSettingsStruct, encryptionKeyPointer);

            return advancedSettingsStruct;
        }
    }

    /**
     * Callback for opening a file.
     *
     * Return the appropriate error code such as {@link FMODResult#FMOD_ERR_FILE_NOTFOUND}
     * if the file fails to open. If the callback is from {@link FMODSystem#attachFileSystem},
     * then the return value is ignored.
     *
     * @see FMODSystem#setFileSystem
     * @see FileCloseCallback
     * @see FileReadCallback
     * @see FileSeekCallback
     * @see FileAsyncReadCallback
     * @see FileAsyncCancelCallback
     *
     * <b>Experimental.</b> This API is subject to change if
     * I find a better way to represent file handles.
     */
    public static interface FileOpenCallback extends FMOD_FILE_OPEN_CALLBACK.Function {
        /**
         * Outputs of the callback.
         *
         * @param fileSize Size of the file. Units: Bytes.
         * @param handle File handle to identify this file in future file callbacks.
         * @param errCode Result of the callback.
         */
        public static record Out(int fileSize, MemorySegment handle, FMODResult errCode) {}

        @Override
        default int apply(MemorySegment name, MemorySegment filesize, MemorySegment handle, MemorySegment userdata) {
            String strName = name.getString(0);
            Out result = call(strName, userdata);
            filesize.set(C_INT, 0, result.fileSize());
            handle.set(C_POINTER, 0, result.handle()); // Double pointer, as opposed to the result's single pointer
            return result.errCode().code();
        }

        /**
         * Callback for opening a file.
         *
         * <b>Experimental.</b> This API is subject to change if
         * I find a better way to represent file handles.
         *
         * @param name File name or identifier. (UTF-8 string)
         * @param userData User value set by {@link CreateSoundEXInfo#fileUserData} or {@link BankInfo#userData}.
         * @return See the {@link Out} record's documentation for return information.
         */
        public Out call(String name, MemorySegment userData);
    }

    /**
     * Callback for closing a file.
     *
     * Close any user created file handle and perform any cleanup necessary for the file here.
     * If the callback is from {@link FMODSystem#attachFileSystem}, then the return value is ignored.
     *
     * @see FMODSystem#setFileSystem
     * @see FileOpenCallback
     * @see FileReadCallback
     * @see FileSeekCallback
     * @see FileAsyncReadCallback
     * @see FileAsyncCancelCallback
     *
     * <b>Experimental.</b> This API is subject to change if
     * I find a better way to represent file handles.
     */
    public static interface FileCloseCallback extends FMOD_FILE_CLOSE_CALLBACK.Function {
        @Override
        default int apply(MemorySegment handle, MemorySegment userdata) {
            FMODResult result = call(handle, userdata);
            return result.code();
        }

        /**
         * Callback for closing a file.
         *
         * <b>Experimental.</b> This API is subject to change if
         * I find a better way to represent file handles.
         *
         * @param handle File handle that was returned in {@link FileOpenCallback#call}.
         * @param userData User value set by {@link CreateSoundEXInfo#fileUserData} or {@link BankInfo#userData}.
         * @return Result of the callback.
         */
        public FMODResult call(MemorySegment handle, MemorySegment userData);
    }

    /**
     * Callback for reading from a file.
     *
     * If the callback is from {@link FMODSystem#attachFileSystem}, then the return value is ignored.
     *
     * If there is not enough data to read the requested number of bytes, return fewer bytes in the
     * bytesread parameter and and return {@link FMODResult#FMOD_ERR_FILE_EOF}.
     *
     * @see FMODSystem#setFileSystem
     * @see FileOpenCallback
     * @see FileCloseCallback
     * @see FileSeekCallback
     * @see FileAsyncReadCallback
     * @see FileAsyncCancelCallback
     *
     * <b>Experimental.</b> This API is subject to change if
     * I find a better way to represent file handles.
     */
    public static interface FileReadCallback extends FMOD_FILE_READ_CALLBACK.Function {
        /**
         * Outputs of the callback.
         *
         * @param bytesRead Number of bytes read into buffer.
         * @param errCode Result of the callback.
         */
        public static record Out(int bytesRead, FMODResult errCode) {}

        @Override
        default int apply(
                MemorySegment handle,
                MemorySegment buffer,
                int sizebytes,
                MemorySegment bytesread,
                MemorySegment userdata) {
            Out result = call(handle, sizebytes, userdata, buffer);
            bytesread.set(C_INT, 0, result.bytesRead());
            return result.errCode().code();
        }

        /**
         * Callback for reading from a file.
         *
         * <b>Experimental.</b> This API is subject to change if
         * I find a better way to represent file handles.
         *
         * @param handle File handle that was returned in {@link FileOpenCallback#call}.
         * @param sizeBytes Number of bytes to read into buffer. Units: Bytes.
         * @param userData User value set by {@link CreateSoundEXInfo#fileUserData} or {@link BankInfo#userData}.
         * @param buffer Output for you to mutate! Buffer to read data into.
         * @return Mutates buffer. See the {@link Out} record's documentation for return information.
         */
        public Out call(MemorySegment handle, int sizeBytes, MemorySegment userData, MemorySegment buffer);
    }

    /**
     * Callback for seeking within a file.
     *
     * If the callback is from {@link FMODSystem#attachFileSystem}, then the return value is ignored.
     *
     * @see FMODSystem#setFileSystem
     * @see FileOpenCallback
     * @see FileCloseCallback
     * @see FileReadCallback
     * @see FileAsyncReadCallback
     * @see FileAsyncCancelCallback
     *
     * <b>Experimental.</b> This API is subject to change if
     * I find a better way to represent file handles.
     */
    public static interface FileSeekCallback extends FMOD_FILE_SEEK_CALLBACK.Function {
        @Override
        default int apply(MemorySegment handle, int pos, MemorySegment userdata) {
            FMODResult result = call(handle, pos, userdata);
            return result.code();
        }

        /**
         * Callback for seeking within a file.
         *
         * @param handle File handle that returned in {@link FileOpenCallback}
         * @param pos Absolute position to seek to in file. Units: Bytes.
         * @param userData User value set by {@link CreateSoundEXInfo#fileUserData} or {@link BankInfo#userData}.
         * @return Result of the callback.
         */
        public FMODResult call(MemorySegment handle, int pos, MemorySegment userData);
    }

    /**
     * Information for loading a bank using user callbacks.
     *
     * @param userData (optional) data to be passed to the file callbacks.
     * If {@link BankInfo#userDataLength} is zero, this must remain valid until the bank
     * has been unloaded and all calls to {@link BankInfo#openCallback} have been matched by
     * a call to {@link BankInfo#closeCallback}.
     * @param userDataLength Length of user data in bytes. If non-zero the {@link BankInfo#userData}
     * will be copied internally; this copy will be kept until the bank has been unloaded and all
     * calls to {@link BankInfo#openCallback} have been matched by a call to {@link BankInfo#closeCallback}.
     * @param openCallback Callback for opening the bank file.
     * @param closeCallback Callback for closing the bank file.
     * @param readCallback Callback for reading from the bank file.
     * @param seekCallback Callback for seeking within the bank file.
     * @see FMODSystem#loadBankCustom
     */
    public static record BankInfo(
            MemorySegment userData,
            int userDataLength,
            FileOpenCallback openCallback,
            FileCloseCallback closeCallback,
            FileReadCallback readCallback,
            FileSeekCallback seekCallback) {

        /**
         * Allocates a FMOD_STUDIO_ADVANCEDSETTINGS native struct corresponding
         * to the advanced settings set in this record.
         *
         * @param allocator The allocator used to allocate the struct
         * @return A memory segment containing the allocated struct
         */
        public MemorySegment allocate(Arena arena) {
            MemorySegment bankInfoStruct = FMOD_STUDIO_BANK_INFO.allocate(arena);

            FMOD_STUDIO_BANK_INFO.size(bankInfoStruct, (int) FMOD_STUDIO_BANK_INFO.sizeof());
            FMOD_STUDIO_BANK_INFO.userdata(bankInfoStruct, userData);
            FMOD_STUDIO_BANK_INFO.userdatalength(bankInfoStruct, userDataLength);
            FMOD_STUDIO_BANK_INFO.opencallback(bankInfoStruct, FMOD_FILE_OPEN_CALLBACK.allocate(openCallback, arena));
            FMOD_STUDIO_BANK_INFO.closecallback(
                    bankInfoStruct, FMOD_FILE_CLOSE_CALLBACK.allocate(closeCallback, arena));
            FMOD_STUDIO_BANK_INFO.readcallback(bankInfoStruct, FMOD_FILE_READ_CALLBACK.allocate(readCallback, arena));
            FMOD_STUDIO_BANK_INFO.seekcallback(bankInfoStruct, FMOD_FILE_SEEK_CALLBACK.allocate(seekCallback, arena));

            return bankInfoStruct;
        }
    }

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
