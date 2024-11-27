import com.example.navigation.models.BagModel
import kotlinx.serialization.Serializable

@Serializable
data class TripModel(
    //val id : String,
    val name: String,
    val startDate: String,
    val endDate: String,
    val arrayBagModel: MutableList<BagModel> = mutableListOf(),
    var allItems: Int = 0,
    var allCheckedItems: Int = 0,
    var tripPercentage: Int = 0
){

    fun updateBagList(newBags: List<BagModel>) {
        arrayBagModel.clear()
        arrayBagModel.addAll(newBags)
    }
    fun deleteBag(bagModel: BagModel) {
        arrayBagModel.remove(bagModel)
    }
    fun findTripByName(tripName: String): TripModel? {
        return if (name == tripName) {
            this
        } else {
            null
        }
    }



}
