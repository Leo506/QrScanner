package com.example.qrscanner

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import models.EncodingRequest
import java.text.SimpleDateFormat

class EncodingRequestsAdapter(private val context: Activity, private val requests: List<EncodingRequest>)
    : ArrayAdapter<EncodingRequest>(context, R.layout.simple_list_item, requests) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.simple_list_item, null, true)

        val request = rowView.findViewById<TextView>(R.id.request)
        request.text = requests[position].requestText

        val requestDate = rowView.findViewById<TextView>(R.id.request_date)
        requestDate.text = SimpleDateFormat("HH:mm dd-MM-yyyy").format(requests[position].requestDate)

        return rowView
    }

}