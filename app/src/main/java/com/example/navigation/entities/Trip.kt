import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trips")
data class Trip(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val startDate: String,
    val endDate: String,
    var allItems: Int = 0,
    var allCheckedItems: Int = 0,
    var tripPercentage: Int = 0
)