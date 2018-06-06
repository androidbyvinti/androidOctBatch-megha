package broadcastreceivers.bmpl.com.broadcastreceivers

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var installUninstallReceiver: InstallUninstallReceiver? = null
    private var customBroadcastReceiver: CustomBroadcastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val registerLocalButton = findViewById(R.id.button) as Button
        val unregisterLocalButton = findViewById(R.id.button2) as Button
        val registerSystemButton = findViewById(R.id.button3) as Button
        val unregisterSystemButton = findViewById(R.id.button4) as Button
        val sendLocalBroadcastButton = findViewById(R.id.button5) as Button

        registerLocalButton.setOnClickListener(this)
        unregisterLocalButton.setOnClickListener(this)
        registerSystemButton.setOnClickListener(this)
        unregisterSystemButton.setOnClickListener(this)
        sendLocalBroadcastButton.setOnClickListener(this)

    }

    override fun onClick(view: View) {

        when (view.id) {

            R.id.button -> registerLocalBroadcast()

            R.id.button2 -> unregisterLocalBroadcast()

            R.id.button3 -> registerSystemBroadcast()

            R.id.button4 -> unregisterSystemBroadcast()

            R.id.button5 -> sendLocalBroadcast()
        }
    }

    private fun registerLocalBroadcast() {
        customBroadcastReceiver = CustomBroadcastReceiver()
        val intentFilter = IntentFilter()
        intentFilter.addAction("custom.broadcast")
        LocalBroadcastManager.getInstance(this).registerReceiver(customBroadcastReceiver, intentFilter)
    }

    private fun unregisterLocalBroadcast() {
        LocalBroadcastManager.getInstance(this@MainActivity).unregisterReceiver(customBroadcastReceiver)
        if (customBroadcastReceiver != null) {
            LocalBroadcastManager.getInstance(this@MainActivity).unregisterReceiver(customBroadcastReceiver)
            customBroadcastReceiver = null
        }
    }

    private fun sendLocalBroadcast() {
        val intent = Intent()
        intent.action = "custom.broadcast"
        intent.putExtra("message", "This is Local Broadcast")
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }

    private fun registerSystemBroadcast() {

        installUninstallReceiver = InstallUninstallReceiver()

        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED)
        intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED)
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED)
        intentFilter.addAction(Intent.ACTION_PACKAGE_CHANGED)  // app update
        registerReceiver(installUninstallReceiver, intentFilter)
    }

    private fun unregisterSystemBroadcast() {
        if (installUninstallReceiver != null) {
            unregisterReceiver(installUninstallReceiver)
            installUninstallReceiver = null
        }
    }
}