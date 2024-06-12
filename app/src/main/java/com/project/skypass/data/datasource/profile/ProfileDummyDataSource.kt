package com.project.skypass.data.datasource.profile

import com.project.skypass.data.model.User

class ProfileDummyDataSource : ProfileDataSource {
    override fun getProfile(): List<User> {
        return listOf(
            User(
                id = "1",
                name = "John Doe",
                email = "john.doe@example.com",
                phoneNumber = "123-456-7890",
                role = "User",
                token = "dummyToken12345",
                photoUrl = "https://sites.uci.edu/sbass/files/2022/05/AF7A55DF-ABEE-4850-B93D-846C75426F32-400x400.png",
                password = null,
                userId = null,
                imageId = null,
                createdAt = null,
                updatedAt = null,
                isVerified = true
            )
        )
    }
}
