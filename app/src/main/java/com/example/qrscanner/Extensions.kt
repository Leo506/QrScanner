package com.example.qrscanner

import java.text.SimpleDateFormat
import java.util.*

fun Date.toLongDateString(): String =
    SimpleDateFormat("HH:mm dd-MM-yyyy", Locale.getDefault()).format(this)