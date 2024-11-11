import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(
    tableName = "items",
    foreignKeys = [ForeignKey(
        entity = Bag::class,
        parentColumns = ["id"],
        childColumns = ["bagId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Item(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val bagId: Long,
    val itemName: String,
    var isChecked: Boolean = false
) {
    fun toggleChecked() {
        isChecked = !isChecked
    }

    fun setIsChecked(value: Boolean) {
        isChecked = value
    }
}
