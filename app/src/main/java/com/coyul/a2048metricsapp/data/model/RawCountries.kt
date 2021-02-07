package com.coyul.a2048metricsapp.data.model

import com.google.gson.annotations.SerializedName


data class RawCountries(
    @SerializedName("code") val code: Long,
    @SerializedName("country_list") val countryList: List<Country>,
    @SerializedName("region_list") val regionList: List<Region>
)

data class Country(
    @SerializedName("country_code") val countryCode: String,
    @SerializedName("country_name") val countryName: String
)

data class Region(
    @SerializedName("region_code") val regionCode: String,
    @SerializedName("region_name") val regionName: String
)