package com.faizul.myquran.presentation.adzan

import android.Manifest
import android.content.pm.PackageManager
import android.icu.text.SimpleDateFormat
import android.location.Geocoder
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.faizul.myquran.core.data.network.adzan.JadwalAdzanRepository
import com.faizul.myquran.core.data.network.adzan.JadwalAdzanViewModel
import com.faizul.myquran.databinding.FragmentAdzanBinding
import com.faizul.myquran.utils.ViewModelFactory
import java.util.Calendar
import java.util.Locale

class AdzanFragment : Fragment() {
    private lateinit var binding: FragmentAdzanBinding
    private lateinit var viewModel: JadwalAdzanViewModel
    private val LOCATION_PERMISSION_REQUEST_CODE = 123
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAdzanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up ViewModel
        val repository = JadwalAdzanRepository()
        val factory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[JadwalAdzanViewModel::class.java]

        val currentDate = getCurrentDate()
        binding.tvDate.text = currentDate

        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true -> {
                    // Permission granted
                    val locationManager = ContextCompat.getSystemService(requireContext(), LocationManager::class.java)
                    if (locationManager != null) {
                        dispatchJadwalShalatCall(locationManager)
                    }
                }
                else -> {
                    // Permission not granted
                    Toast.makeText(
                        requireContext(),
                        "Jadwal shalat will not work unless you provide the permission to access your approximate location", Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Permission already granted
            val locationManager = ContextCompat.getSystemService(requireContext(), LocationManager::class.java)
            if (locationManager != null) {
                dispatchJadwalShalatCall(locationManager)
            }
        } else {
            // Permission not granted, request it
            locationPermissionRequest.launch(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION))
        }

        viewModel.jadwalAdzan.observe(requireActivity()) { jadwalAdzanResponse ->
            if (jadwalAdzanResponse == null) {
                Log.w("JadwalSholatActivity", "Loh kok ilang");
            }
            jadwalAdzanResponse?.let { response ->
                response.data?.firstOrNull()?.let { data ->
                    // Perbarui tampilan dengan data jadwalSholatResponse
                    binding.apply {
                        tvTimeImsak.text = data.timings?.imsak?.substringBefore(" (WIB)")
                        tvTimeSubuh.text = data.timings?.fajr?.substringBefore(" (WIB)")
                        tvTimeDzuhur.text = data.timings?.dhuhr?.substringBefore(" (WIB)")
                        tvTimeAshar.text = data.timings?.asr?.substringBefore(" (WIB)")
                        tvTimeMaghrib.text = data.timings?.maghrib?.substringBefore(" (WIB)")
                        tvTimeIsya.text = data.timings?.isha?.substringBefore(" (WIB)")
                    }

//                    Log.d("jam", data.timings?.asr.toString())
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with location-related operations
            } else {
                // Permission denied, handle accordingly
                Toast.makeText(
                    requireContext(),
                    "Jadwal shalat will not work unless you provide the permission to access your approximate location", Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun getCurrentDate(): String {
        // Using Calendar
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("EEE, dd / MM / yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(calendar.time)

        // Using Date
        // val currentDate = Date()
        // val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        // val formattedDate = dateFormat.format(currentDate)

        return formattedDate
    }

    private fun dispatchJadwalShalatCall(locationManager: LocationManager) {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return;
        }

        val location = when {
            Build.VERSION.SDK_INT >= 31 -> locationManager.getLastKnownLocation(LocationManager.FUSED_PROVIDER)
            else -> locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        }

        if (location == null) {
            Toast.makeText(requireContext(), "takde lokasi lah", Toast.LENGTH_LONG).show();
        } else {
            val currentYear = Calendar.getInstance().get(Calendar.YEAR)
            val currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1
            viewModel.getJadwalAdzan(
                year = currentYear,
                month = currentMonth,
                latitude = location.latitude,
                longitude = location.longitude
            )

            getLocationName(location.latitude, location.longitude)?.let { locationName ->
                binding.tvLocation.text = locationName
            }
        }
    }

    private fun getLocationName(latitude: Double, longitude: Double): String? {
        val geocoder = Geocoder(requireContext(), Locale.getDefault())

        try {
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)

            if (addresses != null && addresses.isNotEmpty()) {
                return addresses[0].getLocality() // You can modify this to get more specific address components if needed
            }
        } catch (e: Exception) {
            Log.e("LocationError", "Error getting location name: ${e.message}")
        }

        return null
    }
}