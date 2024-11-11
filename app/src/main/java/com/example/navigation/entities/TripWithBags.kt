import androidx.room.Embedded
import androidx.room.Relation

data class TripWithBags(
    @Embedded val trip: Trip,
    @Relation(
        parentColumn = "id",
        entityColumn = "tripId"
    )
    val bags: List<Bag>
)
