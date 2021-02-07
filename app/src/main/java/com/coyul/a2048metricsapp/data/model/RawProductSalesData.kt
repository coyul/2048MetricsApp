package com.coyul.a2048metricsapp.data.model

import com.google.gson.annotations.SerializedName

data class RawProductSalesData(
    @SerializedName("code") val code: Int,
    @SerializedName("currency") val currency: String,
    @SerializedName("iap_sales_list") val iapSalesList: List<Any>,
    @SerializedName("market") val market: String,
    @SerializedName("next_page") val nextPage: Any,
    @SerializedName("page_index") val pageIndex: Int,
    @SerializedName("page_num") val pageNum: Int,
    @SerializedName("prev_page") val prevPage: Any,
    @SerializedName("sales_list") val salesList: List<Sales>,
    @SerializedName("vertical") val vertical: String
)

data class Sales(
    @SerializedName("country") val country: String,
    @SerializedName("date") val date: String,
    @SerializedName("revenue") val revenue: Revenue,
    @SerializedName("units") val units: Units
)

data class Revenue(
    @SerializedName("ad") val ad: String,
    @SerializedName("iap") val iap: Iap,
    @SerializedName("product") val product: SharedProduct
)

data class Units(
    @SerializedName("iap") val iap: Iap,
    @SerializedName("product") val product: Product
)

data class Iap(
    @SerializedName("promotions") val promotions: String,
    @SerializedName("refunds") val refunds: String,
    @SerializedName("sales") val sales: String
)

data class Product(
    @SerializedName("downloads") val downloads: String,
    @SerializedName("promotions") val promotions: String,
    @SerializedName("refunds") val refunds: String,
    @SerializedName("updates") val updates: String
)