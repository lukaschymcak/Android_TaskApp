import androidx.room.Embedded
import androidx.room.Relation

data class BagWithItems(
    @Embedded val bag: Bag,
    @Relation(
        parentColumn = "id",
        entityColumn = "bagId"
    )
    val items: List<Item>
)
