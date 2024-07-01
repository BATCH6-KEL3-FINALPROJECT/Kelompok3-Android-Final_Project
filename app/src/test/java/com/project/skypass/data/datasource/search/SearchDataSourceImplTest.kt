package com.project.skypass.data.datasource.search

import com.project.skypass.data.datasource.home.search.SearchDataSource
import com.project.skypass.data.datasource.home.search.SearchDataSourceImpl
import com.project.skypass.data.source.network.model.search.Pagination
import com.project.skypass.data.source.network.model.search.SearchDataItemResponse
import com.project.skypass.data.source.network.model.search.SearchItemResponse
import com.project.skypass.data.source.network.model.search.SearchResponse
import com.project.skypass.data.source.network.service.ApiService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SearchDataSourceImplTest {
    @MockK
    lateinit var service: ApiService
    private lateinit var ds: SearchDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        ds = SearchDataSourceImpl(service)
    }

    @Test
    fun getSearchResults() =
        runBlocking {
            val query = "test query"

            val searchItems =
                listOf(
                    SearchItemResponse(
                        airport_id = "1",
                        airport_name = "Airport A",
                        city = "City A",
                        city_code = "CYA",
                        continent = "Continent A",
                        country = "Country A",
                        iata_code = "AIA",
                    ),
                )

            val pagination = Pagination(10, 10, 100, 100)
            val data = SearchDataItemResponse(airport = searchItems, pagination = pagination)
            val expectedResponse =
                SearchResponse(
                    code = 200,
                    data = data,
                    is_success = true,
                    message = "Success",
                )

            coEvery { service.searchDestination(query) } returns expectedResponse

            val result = ds.getSearchResults(query)

            assertEquals(expectedResponse, result)
            coVerify { service.searchDestination(query) }
        }
}
