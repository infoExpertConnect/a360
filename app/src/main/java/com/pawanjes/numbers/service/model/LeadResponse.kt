package com.pawanjes.numbers.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

    class LeadResponse {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null

}