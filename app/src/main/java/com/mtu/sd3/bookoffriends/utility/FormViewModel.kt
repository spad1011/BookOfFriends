package com.mtu.sd3.bookoffriends.utility

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class FormViewModel : ViewModel() {
    var firstName by mutableStateOf("")
    var lastName by mutableStateOf("")
    var height by mutableStateOf("n/a")
    var address by mutableStateOf("")
    var phoneNumber by mutableStateOf("")
    var birthdate by mutableStateOf("")


    var occupation by mutableStateOf("")
    var hobbies by mutableStateOf("")
    var birthplace by mutableStateOf("")
    var favFood by mutableStateOf("")
    var favMovie by mutableStateOf("")
    var mostLoved by mutableStateOf("")
    var mostHated by mutableStateOf("")

    var messageToOwner by mutableStateOf("")

    var isFirstNameValid by mutableStateOf(true)
    var isLastNameValid by mutableStateOf(true)
    var isAddressValid by mutableStateOf(true)
    var isPhoneNumberValid by mutableStateOf(true)
    var isBirthdateValid by mutableStateOf(true)
    @Suppress("RegExpRedundantEscape", "unused")
    private val PHONE_NUMBER_REGEX =
        Regex("^[+]?[(]?[0-9]{3}[)]?[-\\s.]?[0-9]{3}[-\\s.]?[0-9]{4,6}$")
    //private val PHONE_NUMBER_REGEX_TESTING = Regex("^\\d{4}\$")

    fun validateInputs(): Boolean {
        isFirstNameValid = firstName.length >= 3 && firstName.isNotBlank()
        isLastNameValid = firstName.length >= 3 && firstName.isNotBlank()
        isAddressValid = address.isNotBlank()
        isPhoneNumberValid =
            PHONE_NUMBER_REGEX.matches(phoneNumber)
        isBirthdateValid = birthdate.isNotBlank()
        val returnCheck = listOf(
            isFirstNameValid,
            isLastNameValid,
            isAddressValid,
            isPhoneNumberValid,
            isBirthdateValid
        )
        return returnCheck.all { it }
    }

    fun resetForm() {
        firstName = ""
        lastName = ""
        height = "n/a"
        address = ""
        phoneNumber = ""
        birthdate = ""
        occupation = ""
        hobbies = ""
        birthplace = ""
        favFood = ""
        favMovie = ""
        mostLoved = ""
        mostHated = ""
        messageToOwner = ""
        isFirstNameValid = true
        isLastNameValid = true
        isAddressValid = true
        isPhoneNumberValid = true
        isBirthdateValid = true
    }

}