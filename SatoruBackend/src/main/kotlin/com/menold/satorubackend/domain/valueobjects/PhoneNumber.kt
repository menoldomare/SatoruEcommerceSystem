package com.menold.satorubackend.domain.valueobjects

import com.menold.satorubackend.domain.references.CountryCode
import com.menold.satorubackend.domain.references.CountryCodes


/**
 * Immutable value object representing a validated phone number.
 *
 * Stored/compared in E.164 form (e.g. "+254712345678"), so equals/hashCode
 * (free from `data class`) work correctly for persistence and comparisons.
 *
 * Construction is private — always go through `PhoneNumber.of(...)` so an
 * invalid PhoneNumber can never exist in memory.
 */

//value class PhoneNumber(val value: String) {
//    init {
//        require(value.isNotBlank()) { "Phone number cannot be blank" }
//    }
//}

@ConsistentCopyVisibility
data class PhoneNumber private constructor(
    val countryCode: CountryCode,
    val nationalNumber: String, // digits only, no leading zero, no dial code
    val e164: String            // full "+<dialcode><nationalnumber>"
) {
    init {
        require(nationalNumber.isNotBlank()) { "Phone number cannot be blank" }
    }

    companion object {

        // Basic sanity bounds; real-world subscriber numbers are 4-14 digits.
        private const val MIN_NATIONAL_DIGITS = 4
        private const val MAX_NATIONAL_DIGITS = 14

        /**
         * Build a PhoneNumber from a dial code (e.g. "+254") and a local/national
         * number as typically entered by a user (e.g. "0712345678" or "712345678").
         *
         * @throws IllegalArgumentException if the dial code is unknown or the
         *         remaining digits don't look like a plausible subscriber number.
         */
        fun of(dialCode: String, rawNationalNumber: String): PhoneNumber {
            val country = CountryCodes.list.firstOrNull { it.dialCode == normalizeDialCode(dialCode) }
                ?: throw IllegalArgumentException("Unknown dial code: $dialCode")

            val digitsOnly = rawNationalNumber.filter { it.isDigit() }
                .trimStart('0') // drop a local trunk-prefix zero, e.g. 0712... -> 712...

            require(digitsOnly.length in MIN_NATIONAL_DIGITS..MAX_NATIONAL_DIGITS) {
                "National number must be $MIN_NATIONAL_DIGITS-$MAX_NATIONAL_DIGITS digits, was ${digitsOnly.length}"
            }

            return PhoneNumber(
                countryCode = country,
                nationalNumber = digitsOnly,
                e164 = "${country.dialCode}$digitsOnly"
            )
        }

        /**
         * Build a PhoneNumber directly from a full E.164 string, e.g. "+254712345678".
         * Matches the longest known dial code so "+1..." resolves correctly even
         * though multiple countries (US, Canada) share it.
         */
        fun ofE164(fullNumber: String): PhoneNumber {
            val trimmed = fullNumber.trim()
            require(trimmed.startsWith("+")) { "E.164 number must start with '+': $trimmed" }

            val country = CountryCodes.list
                .filter { trimmed.startsWith(it.dialCode) }
                .maxByOrNull { it.dialCode.length }
                ?: throw IllegalArgumentException("No known dial code matches: $trimmed")

            val digitsOnly = trimmed.removePrefix(country.dialCode).filter { it.isDigit() }
            return of(country.dialCode, digitsOnly)
        }

        private fun normalizeDialCode(dialCode: String): String =
            if (dialCode.startsWith("+")) dialCode else "+$dialCode"
    }

    /** Human-friendly display, e.g. "🇰🇪 +254 712 345 678" (simple grouping, not locale-perfect). */
    fun display(): String {
        val grouped = nationalNumber.chunked(3).joinToString(" ")
        return "${countryCode.flagEmoji} ${countryCode.dialCode} $grouped"
    }

    override fun toString(): String = e164
}

