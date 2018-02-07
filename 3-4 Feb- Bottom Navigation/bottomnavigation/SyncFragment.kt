package com.bmpl.android.bottomnavigation

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class SyncFragment : Fragment() {


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.i("SyncFragment", "inside onAttach()")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("SyncFragment", "inside onCreate()")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
        Log.i("SyncFragment", "inside onCreateView()")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.i("SyncFragment", "inside onActivityCreated()")
    }

    override fun onStart() {
        super.onStart()
        Log.i("SyncFragment", "inside onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.i("SyncFragment", "inside onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.i("SyncFragment", "inside onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.i("SyncFragment", "inside onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("SyncFragment", "inside onDestroy()")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("SyncFragment", "inside onDestroyView()")
    }

    override fun onDetach() {
        super.onDetach()
        Log.i("SyncFragment", "inside onDetach()")
    }
}