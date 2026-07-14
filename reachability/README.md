# Reachability

Practice module for the **R8 Code Shrinking** topic on Android Interview Prep.

You're building what R8 actually is under the hood: mark-and-sweep
reachability over a class/member graph. You'll implement the basic mark and
sweep passes, then the sharp edge in `-keepclassmembers` wildcard rules
(they protect members but never save an otherwise-unreferenced class), the
reflection problem (a class only reached via reflection looks dead and gets
wrongly stripped unless you add a keep rule), a deterministic obfuscation
mapping table, and retracing an obfuscated stack trace back through it, plus
why retracing with a mapping.txt from the wrong build quietly lies to you.
Each task is a small function that's currently broken or unwritten, with a
matching test that's **red**. Make each test **green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :reachability:test                                    # run everything
./gradlew :reachability:test --tests "*T1ReachableClassesTest"  # one task
```

## The tasks

All the work is in `src/main/kotlin/reachability/Tasks.kt`.

1. **`reachableClasses`** (`T1ReachableClassesTest`) - mark phase: the transitive closure of the reference graph from a set of roots.
2. **`strippedClasses`** (`T2StrippedClassesTest`) - sweep phase: every declared class that never came back reachable.
3. **`reachableWithKeepRules`** (`T3ReachableWithKeepRulesTest`) - member-level reachability with `-keep` and `-keepclassmembers` wildcard rules, including the gotcha where keepclassmembers alone can't save an unreferenced class.
4. **`reachableWithReflectionRoots`** (`T4ReachableWithReflectionRootsTest`) - the reflection problem: a class only loaded via reflection has no static edge, so it needs an explicit keep root.
5. **`obfuscationMapping`** (`T5ObfuscationMappingTest`) - build a deterministic original-to-short-name mapping table.
6. **`retraceClassName`** (`T6RetraceClassNameTest`) - reverse the mapping to retrace an obfuscated name, and see why a mapping from the wrong build gives a confidently wrong answer instead of an error.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
