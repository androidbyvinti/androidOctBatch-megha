package com.bmpl.tablayout


import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_contacts.*

// Context

class ContactsFragment : Fragment() {

    lateinit var contactsName : ArrayList<String>
    lateinit var contactsPhone : ArrayList<String>

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // View is created now
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        contactsName = ArrayList()

        contactsPhone = ArrayList()

        getContacts()

        listView.adapter = ContactsAdapter()
    }

    fun getContacts(){
        var contentResolver = context.contentResolver
        var cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)

        if(cursor!=null){
            if(cursor.count> 0){
                while (cursor.moveToNext()){
                    var nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)

                    var name = cursor.getString(nameIndex)

                    var phnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)

                    var phn = cursor.getString(phnIndex)
                    contactsName.add(name)
                    contactsPhone.add(phn)

                    Log.i("Name", contactsName.toString())
                    Log.i("Phone", contactsPhone.toString())
                }
            }
        }
        if(cursor!=null){
            cursor.close()
        }
    }

}






