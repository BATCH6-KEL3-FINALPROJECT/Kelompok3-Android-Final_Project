package com.project.skypass.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import coil.load
import coil.size.Scale
import com.project.skypass.R
import com.project.skypass.data.model.DateCalendar
import com.project.skypass.data.model.Destination
import com.project.skypass.data.model.OrderUser
import com.project.skypass.data.model.Search
import com.project.skypass.data.model.SeatClass
import com.project.skypass.databinding.FragmentHomeBinding
import com.project.skypass.presentation.customview.DataSelection
import com.project.skypass.presentation.flight.detail.FlightDetailActivity
import com.project.skypass.presentation.home.adapter.FavoriteDestinationAdapter
import com.project.skypass.presentation.home.adapter.OrderHistoryAdapter
import com.project.skypass.presentation.home.adapter.PromotionAdapter
import com.project.skypass.presentation.home.calendar.CalendarFragment
import com.project.skypass.presentation.home.flightclass.FlightClassFragment
import com.project.skypass.presentation.home.passengers.PassengersFragment
import com.project.skypass.presentation.home.search.SearchFragment
import com.project.skypass.presentation.profile.changeprofile.ChangeProfileActivity
import com.project.skypass.presentation.profile.settingaccount.SettingsAccountActivity
import com.project.skypass.utils.convertDateFormat
import com.project.skypass.utils.orderDate
import com.project.skypass.utils.proceedWhen
import io.github.muddz.styleabletoast.StyleableToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), DataSelection {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModel()
    private val favoriteDestinationAdapter: FavoriteDestinationAdapter by lazy {
        FavoriteDestinationAdapter {
        }
    }
    private val orderHistoryAdapter: OrderHistoryAdapter by lazy {
        OrderHistoryAdapter()
    }

    private var formatDateDepartureIntent: String? = null
    private var formatDateReturnIntent: String? = null
    private var passengers: String? = null
    private var adultPassenger: Int = 0
    private var childPassenger: Int = 0
    private var babyPassenger: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        clickListener()
        sendData()
        observeDataOrderHistory()
        clickItemOrderHistory()
        displayFavoriteDestination()
        displayProfileData()
        promotionViewPager()
        setOnClickedListener()
    }

    private fun displayFavoriteDestination() {
        viewModel.getFavoriteDestination().observe(viewLifecycleOwner) {
            it.proceedWhen(
                doOnSuccess = { success ->
                    bindSeatClass(success.payload.orEmpty())
                },
                doOnLoading = {
                },
                doOnError = {
                },
            )
        }
    }

    private fun setOnClickedListener() {
        binding.ivPhotoUser.setOnClickListener {
            if (viewModel.isLogin()) {
                navigationToProfile()
            } else {
                StyleableToast.makeText(
                    requireContext(),
                    getString(R.string.text_not_login),
                    R.style.ToastError,
                ).show()
            }
        }

        binding.ivSetting.setOnClickListener {
            if (viewModel.isLogin()) {
                navigateToSetting()
            } else {
                StyleableToast.makeText(
                    requireContext(),
                    getString(R.string.text_not_login),
                    R.style.ToastError,
                ).show()
            }
        }
    }

    private fun bindSeatClass(favDestination: List<Destination>) {
        binding.rvFavoriteDestination.apply {
            adapter = favoriteDestinationAdapter
        }
        favoriteDestinationAdapter.submitData(favDestination)
    }

    private fun sendData() {
        binding.btnSearchFlight.setOnClickListener {
            if (checkAllForm()) {
                moveToFlight()
            } else {
                StyleableToast.makeText(
                    requireContext(),
                    getString(R.string.text_fill_form),
                    R.style.ToastError,
                ).show()
            }
        }
    }

    private fun displayProfileData() {
        if (viewModel.isLogin()) {
            val userId = viewModel.getUserId()
            viewModel.showDataUser(userId).observe(viewLifecycleOwner) { result ->
                result.proceedWhen(
                    doOnSuccess = {
                        binding.tvNameUser.text = "Hi, " + it.payload?.name
                        binding.ivPhotoUser.load(it.payload?.photoUrl) {
                            fallback(R.drawable.iv_profile)
                            crossfade(true)
                            scale(Scale.FILL)
                        }
                    },
                    doOnLoading = {
                    },
                    doOnError = {
                    },
                )
            }
        }
    }

    private fun promotionViewPager() {
        val vpPromotion = binding.vpBannerHome
        val promotion_adapter = PromotionAdapter()
        val pageIndicator = binding.diPromotionPageIndicator

        promotion_adapter.submitList(viewModel.getBannerHome())
        vpPromotion.apply {
            adapter = promotion_adapter
        }
        pageIndicator.attachTo(vpPromotion)
    }

    private fun navigationToProfile() {
        val intent = Intent(activity, ChangeProfileActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSetting() {
        val intent = Intent(activity, SettingsAccountActivity::class.java)
        startActivity(intent)
    }

    private fun moveToFlight() {
        FlightDetailActivity.startActivity(
            requireContext(),
            OrderUser(
                // HHome Data
                id = (0..5000).random(),
                arrivalCity = binding.etToTrip.text.toString(),
                arrivalDate = convertDateFormat(binding.etReturn.text.toString()),
                seatClass = binding.etSeatClass.text.toString().lowercase(),
                departureCity = binding.etFromTrip.text.toString(),
                departureDate = convertDateFormat(binding.etDeparture.text.toString()),
                passengersTotal = binding.etPassengers.text.toString(),
                passengersAdult = adultPassenger,
                passengersBaby = babyPassenger,
                passengersChild = childPassenger,
                isRoundTrip = binding.rbRoundTrip.isChecked,
                supportRoundTrip = binding.rbRoundTrip.isChecked,
                orderDate = orderDate(),
                // Flight Data (One Way)
                airlineCode = "",
                airlineName = "",
                arrivalAirportName = "",
                arrivalIATACode = "",
                arrivalTime = "",
                departureAirportName = "",
                departureIATACode = "",
                departureTime = "",
                flightCode = "",
                flightDescription = "",
                flightDuration = null,
                flightDurationFormat = "",
                flightId = "",
                flightStatus = "",
                flightSeat = "",
                flightArrivalDate = "",
                flightDepartureDate = "",
                planeType = "",
                priceAdult = 0,
                priceBaby = 0,
                priceChild = 0,
                priceTotal = 0,
                paymentPrice = null,
                seatsAvailable = null,
                terminal = "",
                // Flight Data (Round Trip)
                airlineCodeRoundTrip = "",
                airlineNameRoundTrip = "",
                arrivalAirportNameRoundTrip = "",
                arrivalIATACodeRoundTrip = "",
                arrivalTimeRoundTrip = "",
                departureAirportNameRoundTrip = "",
                departureIATACodeRoundTrip = "",
                departureTimeRoundTrip = "",
                flightCodeRoundTrip = "",
                flightDescriptionRoundTrip = "",
                flightDurationRoundTrip = null,
                flightDurationFormatRoundTrip = "",
                flightIdRoundTrip = "",
                flightStatusRoundTrip = "",
                flightSeatRoundTrip = "",
                flightArrivalDateRoundTrip = "",
                flightDepartureDateRoundTrip = "",
                planeTypeRoundTrip = "",
                priceAdultRoundTrip = null,
                priceBabyRoundTrip = null,
                priceChildRoundTrip = null,
                priceTotalRoundTrip = 0,
                paymentPriceRoundTrip = null,
                seatsAvailableRoundTrip = null,
                terminalRoundTrip = "",
            ),
        )
    }

    private fun clickListener() {
        binding.etPassengers.setOnClickListener {
            val passengerFragment = PassengersFragment()
            passengerFragment.passengersSelection = this@HomeFragment
            passengerFragment.show(childFragmentManager, "passengers")
        }
        binding.etFromTrip.setOnClickListener {
            val fromTripFragment = SearchFragment()
            fromTripFragment.tripSelection = this@HomeFragment
            fromTripFragment.show(childFragmentManager, "fromTrip")
        }
        binding.etToTrip.setOnClickListener {
            val toTripFragment = SearchFragment()
            toTripFragment.tripSelection = this@HomeFragment
            toTripFragment.show(childFragmentManager, "toTrip")
        }
        binding.etSeatClass.setOnClickListener {
            val seatClassFragment = FlightClassFragment()
            seatClassFragment.seatClassSelection = this@HomeFragment
            seatClassFragment.show(childFragmentManager, "flightClass")
        }
        binding.etDeparture.setOnClickListener {
            val calendarFragment = CalendarFragment()
            val bundle = Bundle()
            bundle.putString(
                "currentDateDeparture",
                binding.etDeparture.text.toString().ifEmpty { "Belum dipilih" },
            )
            bundle.putString(
                "currentDateReturn",
                binding.etReturn.text.toString().ifEmpty { "Belum dipilih" },
            )
            calendarFragment.arguments = bundle
            calendarFragment.dateSelection = this@HomeFragment
            calendarFragment.show(parentFragmentManager, "departure")
        }
        binding.etReturn.setOnClickListener {
            val calendarFragment = CalendarFragment()
            val bundle = Bundle()
            bundle.putString(
                "currentDateDeparture",
                binding.etDeparture.text.toString().ifEmpty { "Belum dipilih" },
            )
            bundle.putString(
                "currentDateReturn",
                binding.etReturn.text.toString().ifEmpty { "Belum dipilih" },
            )
            calendarFragment.arguments = bundle
            calendarFragment.dateSelection = this@HomeFragment
            calendarFragment.show(parentFragmentManager, "return")
        }
        binding.ivSwitchTrip.setOnClickListener {
            switchFromTo()
        }
        binding.tvClearHistory.setOnClickListener {
            deleteAllOrderHistory()
        }

        tripChecked()
    }

    private fun switchFromTo() {
        val fromTrip = binding.etFromTrip.text.toString()
        val toTrip = binding.etToTrip.text.toString()

        binding.etFromTrip.setText(toTrip)
        binding.etToTrip.setText(fromTrip)
    }

    private fun tripChecked() {
        binding.layoutReturn.visibility = View.GONE

        binding.rgTrip.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_one_way -> {
                    binding.layoutReturn.visibility = View.GONE
                }

                R.id.rb_round_trip -> {
                    binding.layoutReturn.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onDateSelected(
        tag: String,
        date: DateCalendar,
    ) {
        when (tag) {
            "departure" -> {
                binding.etDeparture.setText(date.ddMMMyyyy)
                formatDateDepartureIntent = date.ddMMyyyy
            }

            "return" -> {
                binding.etReturn.setText(date.ddMMMyyyy)
                formatDateReturnIntent = date.ddMMyyyy
            }
        }
    }

    override fun onPassengerSelected(
        tag: String,
        passenger: String,
        adult: Int,
        child: Int,
        baby: Int,
    ) {
        when (tag) {
            "passengers" -> {
                binding.etPassengers.setText(getString(R.string.passengers_qyt_value, passenger))
                passengers = passengers
                adultPassenger = adult
                childPassenger = child
                babyPassenger = baby
            }
        }
    }

    override fun onSeatClassSelected(
        tag: String,
        seatClass: SeatClass,
    ) {
        when (tag) {
            "flightClass" -> {
                binding.etSeatClass.setText(seatClass.classType)
            }
        }
    }

    override fun onTripSelected(
        tag: String,
        trip: Search,
    ) {
        when (tag) {
            "fromTrip" -> {
                binding.etFromTrip.setText(trip.city)
            }

            "toTrip" -> {
                binding.etToTrip.setText(trip.city)
            }
        }
    }

    private fun observeDataOrderHistory() {
        viewModel.getAllOrderHistory().observe(viewLifecycleOwner) {
            it.proceedWhen(doOnSuccess = {
                binding.shimmerViewContainer.isVisible = false
                binding.shimmerViewContainer.stopShimmer()
                binding.ivEmptyHistory.isVisible = false
                binding.rvLastSearch.isVisible = true
                binding.tvLastSearchNotFound.isVisible = false
                it.payload?.let { (item, data) ->
                    orderHistoryAdapter.submitData(item, data)
                }
            }, doOnLoading = {
                binding.shimmerViewContainer.isVisible = true
                binding.shimmerViewContainer.startShimmer()
                binding.ivEmptyHistory.isVisible = false
                binding.rvLastSearch.isVisible = false
                binding.tvLastSearchNotFound.isVisible = false
                binding.tvLastSearchNotFound.text = getString(R.string.loading)
            }, doOnError = { err ->
                binding.shimmerViewContainer.isVisible = false
                binding.shimmerViewContainer.stopShimmer()
                binding.ivEmptyHistory.isVisible = false
                binding.rvLastSearch.isVisible = false
                binding.tvLastSearchNotFound.text = err.exception?.message.orEmpty()
            }, doOnEmpty = {
                binding.shimmerViewContainer.isVisible = false
                binding.shimmerViewContainer.stopShimmer()
                binding.ivEmptyHistory.isVisible = true
                binding.rvLastSearch.isVisible = false
                binding.tvLastSearchNotFound.isVisible = false
                val linkLoad = "https://github.com/riansyah251641/food_app_asset/blob/main/banner/no_activity_order.png?raw=true"
                binding.ivEmptyHistory.load(linkLoad) {
                    crossfade(true)
                    error(R.drawable.bg_no_internet)
                }
            })
        }
    }

    private fun checkAllForm(): Boolean {
        var check = true
        if (
            binding.etFromTrip.text.toString().isEmpty() &&
            binding.etToTrip.text.toString().isEmpty() &&
            binding.etSeatClass.text.toString().isEmpty() &&
            binding.etDeparture.text.toString().isEmpty() &&
            binding.etReturn.text.toString().isEmpty() &&
            binding.etPassengers.text.toString().isEmpty()
        ) {
            check = false
        }
        return check
    }

    private fun clickItemOrderHistory() {
        binding.rvLastSearch.itemAnimator = null
        binding.rvLastSearch.adapter = orderHistoryAdapter
    }

    private fun deleteAllOrderHistory() {
        viewModel.deleteAllOrderHistory().observe(viewLifecycleOwner) {
            it.proceedWhen(doOnSuccess = {
                StyleableToast.makeText(requireContext(), "Menghapus Riwayat Pemesanan", R.style.ToastSuccess).show()
            }, doOnLoading = {
            }, doOnError = { err ->
                Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
            })
        }
    }
}
