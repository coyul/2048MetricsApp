package com.coyul.a2048metricsapp.data.model

import com.google.gson.annotations.SerializedName

class RawSharedProductsData(
    @SerializedName("id") val id: Long,
    @SerializedName("code") val code: Long,
    @SerializedName("next_page") val next_page: String,
    @SerializedName("page_index") val page_index: Long,
    @SerializedName("page_num") val page_num: Long,
    @SerializedName("prev_page") val prev_page: String,
    @SerializedName("sharings") val sharings: List<Sharing>
)

data class Sharing(
    @SerializedName("market") val market: String,
    @SerializedName("owner_account_id") val owner_account_id: Long,
    @SerializedName("owner_name") val owner_name: String,
    @SerializedName("products") val products: List<SharedProduct>,
    @SerializedName("vertical") val vertical: String
)

data class SharedProduct(
    @SerializedName("device_codes") val device_codes: List<String>,
    @SerializedName("devices") val devices: List<String>,
    @SerializedName("first_sales_date") val first_sales_date: String,
    @SerializedName("last_sales_date") val last_sales_date: String,
    @SerializedName("market") val market: String,
    @SerializedName("product_id") val product_id: Long,
    @SerializedName("status") val status: Boolean
)