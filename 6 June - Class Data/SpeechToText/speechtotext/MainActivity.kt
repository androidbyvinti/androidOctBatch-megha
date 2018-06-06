package speechtotext.bmpl.com.speechtotext

import android.app.Activity
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {

    private var txtSpeechInput: TextView? = null
    private var btnSpeak: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var dialog = AlertDialog.Builder(this)
        dialog.setTitle("Exit")
        dialog.setMessage("Do you want to Exit?")
        dialog.setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
            finish()
        })    // Postivie --> Yes,Negative --> No,Neutral-->ok/Cancel

        dialog.setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
            onResume()
        })    // Postivie --> Yes,Negative --> No,Neutral-->ok/Cancel
        dialog.show()

        txtSpeechInput = findViewById(R.id.txtSpeechInput) as TextView
        btnSpeak = findViewById(R.id.btnSpeak) as ImageButton

        // hide the action bar
        // getActionBar().hide();

        btnSpeak!!.setOnClickListener { promptSpeechInput() }
    }

    /**
     * Showing google speech input dialog
     */
    private fun promptSpeechInput() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

        //Starts an activity that will prompt the user for speech and send it through a speech recognizer.
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)

        //EXTRA_LANGUAGE_MODEL: Informs the recognizer which speech model to prefer when performing ACTION_RECOGNIZE_SPEECH.
        //LANGUAGE_MODEL_FREE_FORM: Use a language model based on free-form speech recognition.
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())

        //EXTRA_LANGUAGE: Optional IETF language tag (as defined by BCP 47), for example "en-US".
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Say Something")

        //EXTRA_PROMPT: Optional text prompt to show to the user when asking them to speak.
        try {

            //Starting this intent with just startActivity(Intent) is not supported.
            // You must either use startActivityForResult(Intent, int), or provide a PendingIntent, to receive recognition results.
            startActivityForResult(intent, 1001)

        } catch (a: ActivityNotFoundException) {
            Toast.makeText(applicationContext,
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show()
        }

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    /**
     * Receiving speech input
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            1001 -> {
                if (resultCode == Activity.RESULT_OK && null != data) {

                    val result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    txtSpeechInput!!.text = result[0]
                }
            }
        }
    }

    companion object {
        protected val REQ_CODE_SPEECH_INPUT = 1
    }
}