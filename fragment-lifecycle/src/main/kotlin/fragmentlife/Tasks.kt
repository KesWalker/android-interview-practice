package fragmentlife

/**
 * Fragment lifecycle practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :fragment-lifecycle:test
 */

/** The callbacks a Fragment (and its view) can receive, in the order Android fires them. */
enum class FragmentEvent {
    ON_ATTACH, ON_CREATE, ON_CREATE_VIEW, ON_VIEW_CREATED,
    ON_START, ON_RESUME, ON_PAUSE, ON_STOP,
    ON_DESTROY_VIEW, ON_DESTROY, ON_DETACH
}

/** The fragment-level lifecycle, which is broader than the view lifecycle. */
enum class FragmentLifecycleState { DETACHED, ATTACHED, CREATED, DESTROYED }

/** Which LifecycleOwner an observer was registered against. */
enum class ObserverOwner { FRAGMENT_LIFECYCLE, VIEW_LIFECYCLE }

// TODO(t1): T1FragmentLifecycleStateTest
// Fold a list of events into the fragment's own lifecycle state, starting
// from DETACHED. ON_ATTACH moves to ATTACHED, ON_CREATE moves to CREATED,
// ON_DESTROY moves to DESTROYED, ON_DETACH moves back to DETACHED. Every
// other event (the view-level callbacks) leaves the state unchanged, because
// the fragment stays CREATED across a view being torn down and rebuilt.
fun fragmentLifecycleState(events: List<FragmentEvent>): FragmentLifecycleState {
    TODO("t1: fold events into ATTACHED/CREATED/DESTROYED/DETACHED, ignoring view events")
}

// TODO(t2): T2IsViewAliveTest
// Track whether the fragment's view currently exists. ON_CREATE_VIEW turns
// it on, ON_DESTROY_VIEW turns it off, and this can flip on and off more
// than once across the same events list (a fragment pushed onto the back
// stack tears its view down and rebuilds it later without being destroyed
// itself).
fun isViewAlive(events: List<FragmentEvent>): Boolean {
    TODO("t2: true between an ON_CREATE_VIEW and the next ON_DESTROY_VIEW")
}

// TODO(t3): T3ViewRecreationCountTest
// Count how many times this fragment's view has been created, i.e. how many
// ON_CREATE_VIEW events appear in the list. A fragment that never got a view
// counts as zero.
fun viewRecreationCount(events: List<FragmentEvent>): Int {
    TODO("t3: count ON_CREATE_VIEW occurrences")
}

// TODO(t4): T4ActiveObserverCountTest
// Simulate registering one LiveData observer every time the view is
// created, and see how many are still active at the end. Every
// ON_CREATE_VIEW adds one active observer. ON_DESTROY always clears every
// observer (the fragment itself is gone). The interesting bit is
// ON_DESTROY_VIEW: when owner is VIEW_LIFECYCLE, it clears every observer
// too, since observe() was called with viewLifecycleOwner and Android
// removes it automatically. When owner is FRAGMENT_LIFECYCLE, ON_DESTROY_VIEW
// does nothing, so an observer registered against the fragment itself
// survives a view recreation and a second one piles up next to it, exactly
// the leak/double-subscribe bug this task exists to make visible.
fun activeObserverCount(events: List<FragmentEvent>, owner: ObserverOwner): Int {
    TODO("t4: count observers, cleared by ON_DESTROY_VIEW only for VIEW_LIFECYCLE, always by ON_DESTROY")
}

// TODO(t5): T5LegalNextEventTest
// Decide whether `event` is a legal next callback given the fragment's
// current coarse state and whether its view is currently alive. Rules:
//  - From DETACHED (view never alive): only ON_ATTACH is legal.
//  - From ATTACHED (view never alive): only ON_CREATE is legal.
//  - From CREATED with no view alive: ON_CREATE_VIEW is legal, and so is
//    ON_DESTROY (a fragment may never acquire a view before being torn
//    down). Nothing else is legal, in particular none of
//    ON_START/ON_RESUME/ON_PAUSE/ON_STOP/ON_VIEW_CREATED/ON_DESTROY_VIEW,
//    since there is no view for them to apply to.
//  - From CREATED with the view alive: ON_VIEW_CREATED, ON_START,
//    ON_RESUME, ON_PAUSE, ON_STOP and ON_DESTROY_VIEW are all legal.
//    ON_CREATE_VIEW is not (the view already exists) and neither is
//    ON_DESTROY (the view must be torn down first).
//  - From DESTROYED (view never alive): only ON_DETACH is legal.
//  - Any other combination (including DESTROYED with a view alive, which
//    should never happen) is illegal.
fun legalNextEvent(current: FragmentLifecycleState, viewAlive: Boolean, event: FragmentEvent): Boolean {
    TODO("t5: check event against the allowed transitions for (current, viewAlive)")
}

// TODO(t6): T6ValidateSequenceTest
// Walk a full events list from the start (DETACHED, view not alive),
// checking each event with legalNextEvent before applying it. Apply an
// event by updating the running state the same way fragmentLifecycleState
// and isViewAlive do (ON_ATTACH/ON_CREATE/ON_DESTROY/ON_DETACH update the
// coarse state, ON_CREATE_VIEW/ON_DESTROY_VIEW flip viewAlive). Return the
// index of the first event that legalNextEvent rejects, or -1 if the whole
// sequence is legal.
fun validateSequence(events: List<FragmentEvent>): Int {
    TODO("t6: return the index of the first illegal event, or -1")
}
