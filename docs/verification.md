# Verification

Checks performed on 30 October 2026.

## Build

- `:app:assembleDebug`: passed.
- `:app:lintDebug`: passed, with **0 errors and 4 warnings**.
- The remaining warnings are one Gradle update notice and three nested-layout-weight performance advisories. Versions remain pinned to the compatible toolchain; the small, static Views layouts use weights to meet the assignment's proportional-sizing requirement.

## Device checks

The app was installed and exercised on an **Android 17 / API 37 emulator**. Additional screen profiles were tested by changing the same emulator's display size and density, rather than by using separate physical devices.

| Check | Result |
|---|---|
| Four click handlers in portrait and landscape | Passed |
| Explicit Intent navigation and system Back for all topics | Passed |
| All four topic titles, category names and complete text in both orientations | Passed |
| Long content scrolls while staying in DetailActivity | Passed |
| Rotation preserves the selected topic | Passed |
| Portrait 2×2 grid and landscape row of four buttons | Passed |
| Normal button background exactly #1B5E20 | Passed |
| Pressed button background exactly #66BB6A | Passed |
| 360dp-wide phone profile, portrait and landscape | Passed |
| 800dp-wide tablet profile, portrait and landscape | Passed |
| Small landscape profile with font scale 1.3 | Passed |

The emulator's physical display settings and original font scale were restored after the checks. The course data file was checked against `Resources.zip` and remained unchanged apart from its filename.

The minimum SDK is API 24 and the API compatibility lint check passes. An API 24 device was not available for a separate runtime test.
