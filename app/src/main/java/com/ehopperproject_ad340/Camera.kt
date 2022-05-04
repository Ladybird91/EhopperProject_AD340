package com.ehopperproject_ad340

data class Camera(
    var Id: String,
    var Description: String,
    var ImageUrl: String,
    var Type: String

) {

    fun getFullImageUrl(): String {
        return if (Type == "sdot") {
            "http://www.seattle.gov/trafficcams/images/$ImageUrl"
        } else {
            "http://images.wsdot.wa.gov/nw/$ImageUrl"
        }
    }
}

data class GetList(
    val Features: List<Features>
)

data class Features(
      val Cameras: List<Camera>
)




