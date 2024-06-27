package com.project.skypass.data.mapper

import com.project.skypass.data.model.Search
import com.project.skypass.data.model.SearchHistoryHome
import com.project.skypass.data.source.network.model.search.SearchItemResponse
import com.project.skypass.data.source.network.model.search.gethistory.GetHistoryItemResponse

fun SearchItemResponse?.toSearch() =
    Search(
        airportId = this?.airport_id.orEmpty(),
        airportName = this?.airport_name.orEmpty(),
        city = this?.city.orEmpty(),
        cityCode = this?.city_code.orEmpty(),
        continent = this?.continent.orEmpty(),
        iataCode = this?.iata_code.orEmpty(),
        country = this?.country.orEmpty(),
    )

fun GetHistoryItemResponse?.toSearchHomeHistory() =
    SearchHistoryHome(
        idHistory = this?.id ?: 0,
        userId = this?.userId.orEmpty(),
        history = this?.history.orEmpty(),
        createdAt = this?.createdAt.orEmpty(),
        updatedAt = this?.updatedAt.orEmpty(),
    )

fun Collection<SearchItemResponse>?.toSearchDestination() = this?.map { it.toSearch() } ?: listOf()
fun Collection<GetHistoryItemResponse>?.toSearchHomeListHistory() =
    this?.map { it.toSearchHomeHistory() } ?: listOf()