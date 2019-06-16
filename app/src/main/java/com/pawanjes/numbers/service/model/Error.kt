package com.pawanjes.numbers.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Error {

    @SerializedName("errorCode")
    @Expose
    var errorCode: Int? = null
    @SerializedName("errorMsg")
    @Expose
    var errorMsg: String? = null

}