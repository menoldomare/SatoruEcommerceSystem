package com.menold.satorubackend.domain.references

/**
 * Country dial-code data for building a "select country" mobile number prefix picker.
 *
 * Usage example (Jetpack Compose dropdown):
 *
 *   var selected by remember { mutableStateOf(CountryCodes.list.first { it.isoCode == "KE" }) }
 *   Text("${selected.flagEmoji} ${selected.dialCode}")
 */

data class CountryCode(
    val name: String,
    val isoCode: String,   // ISO 3166-1 alpha-2
    val dialCode: String   // e.g. "+254"
) {
    /** Builds the flag emoji from the ISO code (regional indicator symbols). */
    val flagEmoji: String
        get() = isoCode
            .uppercase()
            .map { char -> 0x1F1E6 + (char - 'A') }
            .joinToString("") { codePoint -> String(Character.toChars(codePoint)) }

    val displayLabel: String
        get() = "$flagEmoji $name ($dialCode)"
}

object CountryCodes {

    val list: List<CountryCode> = listOf(
        CountryCode("Afghanistan", "AF", "+93"),
        CountryCode("Albania", "AL", "+355"),
        CountryCode("Algeria", "DZ", "+213"),
        CountryCode("Argentina", "AR", "+54"),
        CountryCode("Australia", "AU", "+61"),
        CountryCode("Austria", "AT", "+43"),
        CountryCode("Bangladesh", "BD", "+880"),
        CountryCode("Belgium", "BE", "+32"),
        CountryCode("Brazil", "BR", "+55"),
        CountryCode("Burundi", "BI", "+257"),
        CountryCode("Canada", "CA", "+1"),
        CountryCode("China", "CN", "+86"),
        CountryCode("Colombia", "CO", "+57"),
        CountryCode("Democratic Republic of the Congo", "CD", "+243"),
        CountryCode("Egypt", "EG", "+20"),
        CountryCode("Ethiopia", "ET", "+251"),
        CountryCode("France", "FR", "+33"),
        CountryCode("Germany", "DE", "+49"),
        CountryCode("Ghana", "GH", "+233"),
        CountryCode("Greece", "GR", "+30"),
        CountryCode("India", "IN", "+91"),
        CountryCode("Indonesia", "ID", "+62"),
        CountryCode("Iran", "IR", "+98"),
        CountryCode("Iraq", "IQ", "+964"),
        CountryCode("Ireland", "IE", "+353"),
        CountryCode("Israel", "IL", "+972"),
        CountryCode("Italy", "IT", "+39"),
        CountryCode("Japan", "JP", "+81"),
        CountryCode("Kenya", "KE", "+254"),
        CountryCode("Malawi", "MW", "+265"),
        CountryCode("Malaysia", "MY", "+60"),
        CountryCode("Mexico", "MX", "+52"),
        CountryCode("Morocco", "MA", "+212"),
        CountryCode("Mozambique", "MZ", "+258"),
        CountryCode("Netherlands", "NL", "+31"),
        CountryCode("New Zealand", "NZ", "+64"),
        CountryCode("Nigeria", "NG", "+234"),
        CountryCode("Norway", "NO", "+47"),
        CountryCode("Pakistan", "PK", "+92"),
        CountryCode("Philippines", "PH", "+63"),
        CountryCode("Poland", "PL", "+48"),
        CountryCode("Portugal", "PT", "+351"),
        CountryCode("Qatar", "QA", "+974"),
        CountryCode("Rwanda", "RW", "+250"),
        CountryCode("Saudi Arabia", "SA", "+966"),
        CountryCode("Senegal", "SN", "+221"),
        CountryCode("Singapore", "SG", "+65"),
        CountryCode("Somalia", "SO", "+252"),
        CountryCode("South Africa", "ZA", "+27"),
        CountryCode("South Korea", "KR", "+82"),
        CountryCode("South Sudan", "SS", "+211"),
        CountryCode("Spain", "ES", "+34"),
        CountryCode("Sri Lanka", "LK", "+94"),
        CountryCode("Sudan", "SD", "+249"),
        CountryCode("Sweden", "SE", "+46"),
        CountryCode("Switzerland", "CH", "+41"),
        CountryCode("Tanzania", "TZ", "+255"),
        CountryCode("Thailand", "TH", "+66"),
        CountryCode("Turkey", "TR", "+90"),
        CountryCode("Uganda", "UG", "+256"),
        CountryCode("Ukraine", "UA", "+380"),
        CountryCode("United Arab Emirates", "AE", "+971"),
        CountryCode("United Kingdom", "GB", "+44"),
        CountryCode("United States", "US", "+1"),
        CountryCode("Vietnam", "VN", "+84"),
        CountryCode("Yemen", "YE", "+967"),
        CountryCode("Zambia", "ZM", "+260"),
        CountryCode("Zimbabwe", "ZW", "+263")
        // Add more as needed — this covers the most commonly requested countries.
    ).sortedBy { it.name }

    /** Look up a country by its ISO code, e.g. "KE" -> Kenya. */
    fun findByIso(isoCode: String): CountryCode? =
        list.firstOrNull { it.isoCode.equals(isoCode, ignoreCase = true) }

    /** Look up all countries sharing a dial code, e.g. "+1" -> US & Canada. */
    fun findByDialCode(dialCode: String): List<CountryCode> =
        list.filter { it.dialCode == dialCode }
}

/* -------------------------------------------------------------------- */
/* Example: Jetpack Compose dropdown using this data                     */
/* -------------------------------------------------------------------- */

/*
@Composable
fun CountryCodeDropdown(
    selected: CountryCode,
    onSelectedChange: (CountryCode) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedButton(onClick = { expanded = true }) {
            Text(selected.displayLabel)
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            CountryCodes.list.forEach { country ->
                DropdownMenuItem(
                    text = { Text(country.displayLabel) },
                    onClick = {
                        onSelectedChange(country)
                        expanded = false
                    }
                )
            }
        }
    }
}
*/
