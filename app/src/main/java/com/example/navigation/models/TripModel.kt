import com.example.navigation.models.BagModel
import kotlinx.serialization.Serializable

@Serializable
data class TripModel(
    val name: String,
    val startDate: String,
    val endDate: String,
    val arrayBagModel: MutableList<BagModel> = mutableListOf(),
    var allItems: Int = 0,
    var allCheckedItems: Int = 0,
    var tripPercentage: Int = 0
){



}
