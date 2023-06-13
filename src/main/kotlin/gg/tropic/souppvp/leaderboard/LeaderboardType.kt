package gg.tropic.souppvp.leaderboard

import org.bson.Document

/**
 * @author GrowlyX
 * @since 6/13/2023
 */
fun aggregateFieldSortedDescending(field: String) =
    listOf(
        Document(
            "\$sort",
            Document(field, -1L)
        ),
        Document("\$limit", 10L),
        Document(
            "\$project",
            Document("_id", "\$_id")
                .append(
                    "value",
                    "\$$field"
                )
        )
    )

enum class LeaderboardType(
    val field: String,
    val aggregate: () -> List<Document>
)
{
    Kills("kills") {
        aggregateFieldSortedDescending(field)
    },
    Deaths("deaths") {
        aggregateFieldSortedDescending(field)
    },
    Balances("coins") {
        aggregateFieldSortedDescending(field)
    },
    Consumed_Soup("soupsConsumed") {
        aggregateFieldSortedDescending(field)
    };

    /**
     * This is sorta ghetto but too lazy right now
     */
    val display: String = name
        .replace("_", " ")
}
