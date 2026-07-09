package scopefunctions

/**
 * Scope Functions practice.
 *
 * Each function below is unwritten and has a matching test in src/test that
 * is currently RED. Your job, one task at a time, is to implement it so its
 * test goes GREEN. Run a single test class from the gutter in Android Studio,
 * or run them all with:
 *
 *     ./gradlew :scope-functions:test
 */

data class Report(var title: String = "", var pageCount: Int = 0, var published: Boolean = false)

data class Order(val items: List<String>, val discountPercent: Int)

// TODO(t1): GreetIfPresentTest
// Return "Hello, <name>!" when `name` is non-null and not blank, otherwise null.
fun greetIfPresent(name: String?): String? {
    TODO("t1: greet by name, or null when it's null/blank")
}

// TODO(t2): BuildPublishedReportTest
// Build a Report with the given title and page count, marked as published.
fun buildPublishedReport(title: String, pageCount: Int): Report {
    TODO("t2: build and return a published Report with this title and page count")
}

// TODO(t3): TrackAndDoubleTest
// Add `number` to `log`, then return double the original number.
fun trackAndDouble(number: Int, log: MutableList<Int>): Int {
    TODO("t3: record number in log, then return it doubled")
}

// TODO(t4): SummarizeTest
// Return "<n> items, <discountPercent>% off" built from `order`'s own properties, or null when `order` is null.
fun summarize(order: Order?): String? {
    TODO("t4: summarize the order's items and discount, or null when order is null")
}
