package fetchingjson.bmpl.com.fetchingjson

import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity" //Log
    private var listView: ListView? = null // not required
    private var progressDialog: ProgressDialog? = null //loading

    // key-value
    lateinit var userDetailsList: ArrayList<HashMap<String, String>>

    private val url = "http://www.json-generator.com/api/json/get/bOmYAaCyKW?indent=2"

    //var userChoice  = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userDetailsList = ArrayList()

        listView = findViewById(R.id.listView) as ListView

        // GetContacts --> Inherit --> AysncTask --> excute() -> predefined method

        // new GetContacts()

        //userChoice = "women_clothing"

        GetContacts().execute()    //AsyncTask class abstract --> doInBackground() --> Background services
    }

    private inner class GetContacts : AsyncTask<Void, Void, Void>() {

        // first loaded --> user interactivity
        override fun onPreExecute() {
            super.onPreExecute()
            // Showing progress dialog
            progressDialog = ProgressDialog(this@MainActivity)
            progressDialog!!.setMessage("Loading")
            progressDialog!!.setCancelable(false)//true
            progressDialog!!.show() // MainThread --> Backend --> Front-end --> UIThread

        }

        // execute
        override fun doInBackground(vararg arg0: Void): Void? {

            val httpHandler = HttpHandler()    // custom class --> url hit --> url data return

            val jsonString : String? = httpHandler.makeServiceCall(url)//method --> url -->

            Log.i(TAG, "Response from url: " + jsonString!!) // null or data

            if (jsonString != null) {
                try {
                    val jsonObj = JSONObject(jsonString)
                    //val innerobj = jsonObj.getJSONObject("clothing")

                    val contacts = jsonObj.getJSONArray("empdata")
                    // contacts[] =  [ {name:"", email:"", address:{}, } , {},  {} ]
                    for (i in 0..contacts.length()) {
                            val c = contacts.getJSONObject(i) //0

                        //val id = c.getString("id")
                        val name = c.getString("name")
                        val email = c.getString("email")
                        //val gender = c.getString("gender")

                        val phone = c.getJSONObject("phone")
                        val mobile = phone.getString("mobile")
                        val home = phone.getString("home")

                        val contact = HashMap<String, String>()

                        contact.put("nm", name)
                        contact.put("em", email)
                        contact.put("mb", mobile)

                        userDetailsList.add(contact) // 0 --> hashmap obj, 1--> hashmap obj
                    }
                }
                // catch(Exception e)
                catch (e: JSONException) {
                    Log.e(TAG, "Json parsing error: " + e.message)
                    runOnUiThread {
                        Toast.makeText(applicationContext,
                                "Json parsing error: " + e.message,
                                Toast.LENGTH_LONG)
                                .show()
                    }
                }

            } else {
                Log.e(TAG, "Couldn't get json from server.")
                runOnUiThread {
                    Toast.makeText(applicationContext,
                            "Couldn't get json from server.",
                            Toast.LENGTH_LONG)
                            .show()
                }
            }
            return null
        }

        override fun onPostExecute(result: Void) {
            super.onPostExecute(result)

            if (progressDialog!!.isShowing)
                progressDialog!!.dismiss()

//            val random = Random()
//            random.nextInt(10)// --> 0,9

            //CustomAdapter--> extends BaseAdapter
            val adapter = SimpleAdapter(
                    this@MainActivity, userDetailsList,
                    R.layout.list_item, arrayOf("nm", "em", "mb"), intArrayOf(R.id.name, R.id.email, R.id.mobile))
                                            //Array<String>
            listView!!.adapter = adapter
        }
    }

}