package com.hera.bangkit.ui.main.post.sos

import android.Manifest
import android.app.Activity.RESULT_OK
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnCompleteListener
import com.hera.bangkit.R
import com.hera.bangkit.databinding.ActivityReportBinding
import com.hera.bangkit.databinding.ActivitySosBinding
import java.io.IOException
import java.util.*

class SosActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySosBinding

    private lateinit var guardianPhoneNumber1: String
    private lateinit var guardianPhoneNumber2: String

    val SMS_SENT_ACTION = "com.hera.bangkit.ui.main.post.sos.SMS_SENT_ACTION"
    val SMS_DELIVERED_ACTION = "com.hera.bangkit.ui.main.post.sos.SMS_DELIVERED_ACTION"

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCancelSos.setOnClickListener {
            finish()
        }

        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(this@SosActivity)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED || checkSelfPermission(
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_DENIED
            ) {
                Log.d("permission", "permission denied to SEND_SMS - requesting it")
                val permissions =
                    arrayOf(Manifest.permission.SEND_SMS, Manifest.permission.ACCESS_FINE_LOCATION)
                requestPermissions(permissions, 1)
            }
        }

        binding.sos.setOnClickListener {
            // coba modif
            val phoneNum = guardianPhoneNumber1 + guardianPhoneNumber2
//            val phoneNum1 = guardianPhoneNumber1
//            val phoneNum2 = guardianPhoneNumber2

            // koko
//            val phoneNum = etPhoneNumber.editText?.text.toString()

            if (phoneNum.isEmpty()) {
                Toast.makeText(applicationContext, "Please Enter a Phone Number", Toast.LENGTH_LONG)
                    .show()
            } else {
                showLocation(phoneNum)
            }
        }


        registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                var message: String? = null
                when (resultCode) {
                    RESULT_OK -> message = "Message Sent Successfully !"
                    SmsManager.RESULT_ERROR_GENERIC_FAILURE -> message = "Error."
                    SmsManager.RESULT_ERROR_NO_SERVICE -> message = "Error: No service."
                    SmsManager.RESULT_ERROR_NULL_PDU -> message = "Error: Null PDU."
                    SmsManager.RESULT_ERROR_RADIO_OFF -> message = "Error: Radio off."
                }
//                    smsStatus.setText(message)
                Toast.makeText(applicationContext, "$message", Toast.LENGTH_SHORT).show()
            }
        }, IntentFilter(SMS_SENT_ACTION))

        registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {

//                etPhoneNumber.editText.setText("")
//                deliveryStatus.setText("SMS Delivered")
                Toast.makeText(applicationContext, "SMS Delivered", Toast.LENGTH_SHORT).show()
            }
        }, IntentFilter(SMS_DELIVERED_ACTION))
    }

    fun sendSMS(phoneNumber: String?, smsMessage: String?) {
        val sms: SmsManager = SmsManager.getDefault()
        val messages: List<String> = sms.divideMessage(smsMessage)
        for (message in messages) {
            sms.sendTextMessage(
                phoneNumber, null, message, PendingIntent.getBroadcast(
                    this, 0, Intent(SMS_SENT_ACTION), 0
                ), PendingIntent.getBroadcast(this, 0, Intent(SMS_DELIVERED_ACTION), 0)
            )
        }
    }

    fun showLocation(phoneNumber: String) {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationProviderClient.lastLocation.addOnCompleteListener(OnCompleteListener<Location> {
            var location: Location = it.result
            if (location != null) {
                var geocoder: Geocoder = Geocoder(this@SosActivity, Locale.getDefault())
                try {
                    var addressList: List<Address> =
                        geocoder.getFromLocation(location.latitude, location.longitude, 1)
                    var smsBody =
                        "[DARURAT] Tolong kirim bantuan di ${
                            addressList.get(0).getAddressLine(0)
                        }"
                    sendSMS(phoneNumber, smsBody)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            } else {
                Toast.makeText(this@SosActivity, "Location null error", Toast.LENGTH_SHORT)
                    .show();
            }
        })
    }
}