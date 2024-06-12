package com.project.skypass.data.datasource.notification

import com.project.skypass.data.model.Notification

interface DataSourceNotification {
    fun getNotificationItem(): List<Notification>
}

class DataSourceNotificationImpl: DataSourceNotification {
    override fun getNotificationItem(): List<Notification> {
        return mutableListOf(
            Notification(
                title = "E-Ticket Ready: Your Adventure Awaits!",
                body = "Your e-ticket for flight XYZ123 to Bali is now available in your account. Download it for easy access and check important details like baggage allowance, check-in times, and gate information. Get ready for an unforgettable trip!",
                category = "Information",
                date = "2023-12-19",
                status = false
            ),
            Notification(
                title = "Exclusive Deals on E-Flight Tickets!",
                body = "Don't miss out on our limited-time promotion for e-flight tickets to your dream destinations. Book now and enjoy incredible savings on your next adventure. Explore exotic locales, discover hidden gems, and create memories that will last a lifetime.",
                category = "Promotion",
                date = "2023-12-18",
                status = false
            ),
            Notification(
                title = "Manage Your E-Flight Ticket with Ease",
                body = "Did you know you can easily manage your e-flight ticket details through our app? Update passenger information, select preferred seats, and even add extra baggage with just a few taps. Experience a seamless travel journey from booking to boarding.",
                category = "Information",
                date = "2023-12-17",
                status = false
            ),
        )
    }
}