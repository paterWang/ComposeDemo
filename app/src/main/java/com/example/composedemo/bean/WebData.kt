package com.example.composedemo.bean

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class WebData(
    var title :String?,
    var url :String?
) :Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WebData> {
        override fun createFromParcel(parcel: Parcel): WebData {
            return WebData(parcel)
        }

        override fun newArray(size: Int): Array<WebData?> {
            return arrayOfNulls(size)
        }
    }
}