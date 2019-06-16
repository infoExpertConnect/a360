package com.pawanjes.numbers.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

    class AboutContactBean {

    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("content")
    @Expose
    var content: String? = null

}