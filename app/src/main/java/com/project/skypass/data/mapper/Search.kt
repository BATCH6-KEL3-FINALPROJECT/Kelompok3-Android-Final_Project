package com.project.skypass.data.mapper

import com.project.skypass.data.model.Search
import com.project.skypass.data.source.network.model.search.SearchDataItemResponse
import com.project.skypass.data.source.network.model.search.SearchItemResponse

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

fun Collection<SearchItemResponse>?.toSearchDestination() = this?.map { it.toSearch() }  ?: listOf()