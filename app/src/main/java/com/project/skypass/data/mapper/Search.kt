package com.project.skypass.data.mapper

import com.project.skypass.data.model.Search
import com.project.skypass.data.source.network.model.search.SearchItemResponse

fun SearchItemResponse?.toSearch() =
    Search(
        airportId = this?.airport_id,
        airportName = this?.airport_name,
        city = this?.city,
        cityCode = this?.city_code,
        continent = this?.continent,
        iataCode = this?.iata_code,
        country = this?.country
    )

fun Collection<SearchItemResponse>?.toSearchDestination() = this?.map { it.toSearch() }  ?: listOf()