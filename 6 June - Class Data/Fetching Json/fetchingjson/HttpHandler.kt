package fetchingjson.bmpl.com.fetchingjson

import android.util.Log
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.ProtocolException
import java.net.URL

class HttpHandler{

    var TAG = "HttpHandler"

    fun makeServiceCall(reqUrl: String): String? {

        var response: String? = null

        try {
            // Step : 1--> convert the string into proper url form

            val url = URL(reqUrl)//step-1: url passed to URL class

            // step:2 --> open or establish the connection with the url

            val conn : HttpURLConnection = url.openConnection() as HttpURLConnection

            // step:3 --> request data from URL --> GET(fetch) or POST(upload)
            // request = "GET"
            conn.requestMethod = "GET"   // request get

            val bufferedInputStream = BufferedInputStream(conn.inputStream) // StringBuilder or StringBuffer--> modification string
            response = convertStreamToString(bufferedInputStream)

        } catch (e: MalformedURLException) {
            Log.e(TAG, "MalformedURLException: " + e.message)
        } catch (e: ProtocolException) {
            Log.e(TAG, "ProtocolException: " + e.message)
        } catch (e: IOException) {
            Log.e(TAG, "IOException: " + e.message)
        } catch (e: Exception) {
            Log.e(TAG, "Exception: " + e.message)
        }

        return response
    }

    private fun convertStreamToString(inputStream : InputStream): String {
        //var streamReader = InputStreamReader(inputStream)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val sb = StringBuilder() // String --> immutable --> 1111 -->("Ram")  2222-->("Ram" + "Kumar")
        // StringBuilder --> Mutable --> 12222  --> updatation
        try {
            while (reader.readLine() != null) {

                sb.append(reader.readLine()).append("\n")
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                inputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return sb.toString()
    }
}