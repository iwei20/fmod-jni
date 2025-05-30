jextract \
    --include-dir fmodstudioapi/c/ \
    --output lib/src/generated/java/ \
    --target-package com.iwei20.fmod.gen.fmodstudio \
    fmodstudioapi/c/fmod_studio.h
jextract \
    --include-dir fmodstudioapi/c/ \
    --output lib/src/generated/java/ \
    --target-package com.iwei20.fmod.gen.fmod \
    fmodstudioapi/c/fmod.h

