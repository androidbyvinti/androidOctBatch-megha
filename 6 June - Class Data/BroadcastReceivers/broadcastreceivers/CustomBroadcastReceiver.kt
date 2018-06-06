package broadcastreceivers.bmpl.com.broadcastreceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast


// step-> 1 : clas inherit abstract BroadcastReceiver --> onReceive()
class CustomBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        if (intent.action == "custom.broadcast") {
            val message = intent.getStringExtra("message")

            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }
}