package com.ehopperproject_ad340

data class CameraDetails(
    val Id: String,
    val Description: String,
    val ImageUrl: String,
    val Type: String

) {

    fun getFullImageUrl(): String {
        return if (Type == "sdot") {
            "https://www.seattle.gov/trafficcams/images/$ImageUrl"
        } else {
            "https://images.wsdot.wa.gov/nw/$ImageUrl"
        }
    }
}

data class CallResponse(
    val Features: List<Features>
)

data class Features(
    val Cameras: List<CameraDetails>
)






