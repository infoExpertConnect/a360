package com.pawanjes.numbers.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RetrofitResponse<T> {

    @SerializedName("error")
    @Expose
    var error: Error? = null
    @SerializedName("data")
    @Expose
    var data: T? = null

}
