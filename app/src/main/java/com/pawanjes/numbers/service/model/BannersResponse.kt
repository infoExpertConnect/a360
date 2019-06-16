package com.pawanjes.numbers.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

    class BannersResponse {

    @SerializedName("banners")
    @Expose
    var banners: List<String>? = null

}