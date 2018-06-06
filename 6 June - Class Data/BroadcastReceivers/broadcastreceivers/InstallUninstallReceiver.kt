package broadcastreceivers.bmpl.com.broadcastreceivers


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class InstallUninstallReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val packageName = intent.data!!.schemeSpecificPart
        //app installed

        if (intent.action == Intent.ACTION_POWER_CONNECTED) {
            Toast.makeText(context.applicationContext, "Power connected Called from Broadcast Receiver", Toast.LENGTH_LONG).show()
        }

        if (intent.action == Intent.ACTION_PACKAGE_ADDED) {
            Toast.makeText(context, "Installed $packageName Called from Broadcast Receiver", Toast.LENGTH_LONG).show()
        }

        if (intent.action == Intent.ACTION_PACKAGE_REMOVED) {
            Toast.makeText(context.applicationContext, "Uninstalled $packageName Called from Broadcast Receiver", Toast.LENGTH_LONG).show()
        }

        if (intent.action == Intent.ACTION_PACKAGE_CHANGED) {
            Toast.makeText(context.applicationContext, "Updated $packageName Called from Broadcast Receiver", Toast.LENGTH_LONG).show()
        }

    }
}
