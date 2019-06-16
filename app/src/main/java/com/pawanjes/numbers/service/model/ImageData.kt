package com.pawanjes.numbers.service.model


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class ImageData() : Parcelable {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("sortKey")
    @Expose
    var sortKey: Int? = null
    @SerializedName("active")
    @Expose
    var active: Boolean? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("bannerType")
    @Expose
    var bannerType: String? = null
    @SerializedName("creationDate")
    @Expose
    var creationDate: Long? = null
    @SerializedName("modificationDate")
    @Expose
    var modificationDate: Long? = null
    @SerializedName("errorMessage")
    @Expose
    var errorMessage: String? = null
    @SerializedName("eventTrackingName")
    @Expose
    var eventTrackingName: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        sortKey = parcel.readValue(Int::class.java.classLoader) as? Int
        active = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        url = parcel.readString()
        bannerType = parcel.readString()
        creationDate = parcel.readValue(Long::class.java.classLoader) as? Long
        modificationDate = parcel.readValue(Long::class.java.classLoader) as? Long
        errorMessage = parcel.readString()
        eventTrackingName = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeValue(sortKey)
        parcel.writeValue(active)
        parcel.writeString(url)
        parcel.writeString(bannerType)
        parcel.writeValue(creationDate)
        parcel.writeValue(modificationDate)
        parcel.writeString(errorMessage)
        parcel.writeString(eventTrackingName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ImageData> {
        override fun createFromParcel(parcel: Parcel): ImageData {
            return ImageData(parcel)
        }

        override fun newArray(size: Int): Array<ImageData?> {
            return arrayOfNulls(size)
        }
    }
}
