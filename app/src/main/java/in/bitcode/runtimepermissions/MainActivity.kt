package `in`.bitcode.runtimepermissions

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val smsPermissionStatus =
            ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_SMS
            )

        if(smsPermissionStatus == PackageManager.PERMISSION_DENIED &&
            ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED
        ) {
            //request sms permission
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_SMS, android.Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        }
        else {
            mt("Sms permission already granted!")
            takeSmsAction()

        }

        /*val smsPermissionStatus =
            checkSelfPermission(android.Manifest.permission.READ_SMS)

        if(smsPermissionStatus == PackageManager.PERMISSION_DENIED &&
            checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED
            ) {
            //request sms permission
            requestPermissions(
                arrayOf(android.Manifest.permission.READ_SMS, android.Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        }
        else {
            mt("Sms permission already granted!")
            takeSmsAction()

        }*/
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            mt("Sms permission granted")
            takeSmsAction()
        }
        else {
            mt("Sms permission denied")
        }
        if(grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            mt("Location permission granted...")
            takeLocationAction()
        }
        else {
            mt("Location permission denied...")
        }
    }

    private fun takeLocationAction() {
        mt("Location based action!")
    }

    private fun takeSmsAction() {
        mt("Sms Action");
    }

    private fun mt(text : String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}