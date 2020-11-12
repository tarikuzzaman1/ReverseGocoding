package bd.rmis.tarik.reverse_gocoding

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        CoroutineScope(Dispatchers.IO).launch {
            println("Kotlin Coroutines World!")
            println("Hello")
            updateLocation(23.776380, 90.371497)
        }

    }

    private suspend fun updateLocation(latitude: Double, longitude: Double): MutableList<Address> {
        val geocoder = Geocoder(this@MainActivity, Locale.getDefault())
        val addresses = geocoder.getFromLocation(latitude, longitude, 1)
        val sb = StringBuilder()
        addresses.forEach { address ->
            Log.e("Reverse Address: ", "$address")
            sb.append(address.getAddressLine(0)).append("\n")
            sb.append(address.locality).append("\n")
            sb.append(address.postalCode).append("\n")
            sb.append(address.countryName)
        }
        Log.e("Reverse Address: ", "$sb")

        //val address: String = addresses[0].getAddressLine(0) // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

        val city: String = addresses[0].locality
        val state: String = addresses[0].adminArea
        val country: String = addresses[0].countryName
        val knownName: String = addresses[0].featureName // Only if available else return NULL
        Log.d("getAddress", "getAddress:  address - $addresses");
        Log.d("getAddress", "getAddress:  city - $city");
        Log.d("getAddress", "getAddress:  state - $state");
        Log.d("getAddress", "getAddress:  country - $state");
        Log.d("getAddress", "getAddress:  knownName - $knownName");

        Log.e("Reverse Address: ", "$sb")

        return addresses

    }
}