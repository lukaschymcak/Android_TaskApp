import com.example.navigation.models.BagModel
import kotlinx.serialization.Serializable

@Serializable
data class TripModel(
    val name: String,
    val startDate: String,
    val endDate: String,
    val arrayBagModel: List<BagModel> = mutableListOf(),
    var allItems: Int = 0,
    var allCheckedItems: Int = 0,
    var tripPercentage: Int = 0
){

    fun setTripPercentage(){
        tripPercentage = if (allItems == 0) 0 else (allCheckedItems * 100) / allItems
    }
    fun calculatePackingPercentage(trip: TripModel): Int {
        val totalItems = trip.arrayBagModel.sumOf { it.itemModels.size }
        val checkedItems = trip.arrayBagModel.sumOf { bag ->
            bag.itemModels.count { it.isChecked }
        }

        return if (totalItems == 0) 0 else (checkedItems * 100) / totalItems
    }

}
