package com.example.pertemuan7

import android.provider.BaseColumns

object UserContract {
    object UserEntry : BaseColumns{
        const val Table_nama = "user"
        const val Coloumn_Id = "id"
        const val Coloumn_Email = "email"
        const val Coloumn_Password = "password"


    }
}