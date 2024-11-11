import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Delete

@Dao
interface TripDao {

    @Insert
    suspend fun insertTrip(trip: Trip)

    @Insert
    suspend fun insertBag(bag: Bag)

    @Insert
    suspend fun insertItem(item: Item)

    @Transaction
    @Query("SELECT * FROM trips WHERE id = :tripId")
    suspend fun getTripWithBags(tripId: Long): TripWithBags

    @Transaction
    @Query("SELECT * FROM bags WHERE id = :bagId")
    suspend fun getBagWithItems(bagId: Long): BagWithItems

    @Query("SELECT * FROM trips")
    suspend fun getAllTrips(): List<Trip>

    @Query("SELECT * FROM bags WHERE tripId = :tripId")
    suspend fun getBagsForTrip(tripId: Long): List<Bag>

    @Query("SELECT * FROM items WHERE bagId = :bagId")
    suspend fun getItemsForBag(bagId: Long): List<Item>

    @Delete
    suspend fun deleteTrip(trip: Trip)

    @Delete
    suspend fun deleteBag(bag: Bag)

    @Delete
    suspend fun deleteItem(item: Item)
}
