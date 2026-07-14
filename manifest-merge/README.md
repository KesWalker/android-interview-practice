# ManifestMerge

Practice module for the **Context & the Manifest** topic on Android Interview
Prep.

Two strands of pure decision logic, no Android SDK required. First, you're
picking the right flavor of Context for a handful of real use sites and
catching the classic bug where a long-lived singleton quietly holds onto an
Activity. Second, you're implementing the algorithm the Android Gradle
Plugin actually runs at build time: merging a library manifest into an app
manifest, honoring `tools:node` markers, and detecting the one kind of merge
Gradle genuinely can't resolve on its own. Each task is a small function
that's currently broken or unwritten, with a matching test that's **red**.
Make each test **green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :manifest-merge:test                          # run everything
./gradlew :manifest-merge:test --tests "*T1PickContextTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/manifestmerge/Tasks.kt`.

1. **`pickContext`** (`T1PickContextTest`) - choose APPLICATION or ACTIVITY context for a handful of real use sites.
2. **`wouldLeak`** (`T2WouldLeakTest`) - detect the one holder/context combination that leaks a screen.
3. **`leakingFields`** (`T3LeakingFieldsTest`) - scan a class's fields and report which ones leak.
4. **`mergeAttributes`** (`T4MergeAttributesTest`) - union two attribute maps, app's value wins on shared keys.
5. **`applyNodeMarker`** (`T5ApplyNodeMarkerTest`) - resolve a same-keyed node pair using `tools:node`'s MERGE, REPLACE and REMOVE.
6. **`mergeManifests`** (`T6MergeManifestsTest`) - run the full library-into-app manifest merge.
7. **`findMergeConflicts`** (`T7FindMergeConflictsTest`) - catch the one kind of conflict Gradle can't auto-resolve: two equal-priority libraries disagreeing with no app override to arbitrate.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
