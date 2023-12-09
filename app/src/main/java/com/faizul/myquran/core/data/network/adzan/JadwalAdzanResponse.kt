package com.faizul.myquran.core.data.network.adzan

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class JadwalAdzanResponse(

	@Json(name="code")
	val code: Int? = null,

	@Json(name="data")
	val data: List<DataItem?>? = null,

	@Json(name="status")
	val status: String? = null
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Weekday(

	@Json(name="en")
	val en: String? = null,

	@Json(name="ar")
	val ar: String? = null
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Gregorian(

	@Json(name="date")
	val date: String? = null,

	@Json(name="month")
	val month: Month? = null,

	@Json(name="year")
	val year: String? = null,

	@Json(name="format")
	val format: String? = null,

	@Json(name="weekday")
	val weekday: Weekday? = null,

	@Json(name="designation")
	val designation: Designation? = null,

	@Json(name="day")
	val day: String? = null
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Month(

	@Json(name="number")
	val number: Int? = null,

	@Json(name="en")
	val en: String? = null,

	@Json(name="ar")
	val ar: String? = null
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Params(

	@Json(name="Isha")
	val isha: Int? = null,

	@Json(name="Fajr")
	val fajr: Int? = null
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Method(

	@Json(name="name")
	val name: String? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="params")
	val params: Params? = null
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Meta(

	@Json(name="method")
	val method: Method? = null,

	@Json(name="offset")
	val offset: Offset? = null,

	@Json(name="school")
	val school: String? = null,

	@Json(name="timezone")
	val timezone: String? = null,

	@Json(name="midnightMode")
	val midnightMode: String? = null,

	@Json(name="latitudeAdjustmentMethod")
	val latitudeAdjustmentMethod: String? = null
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Timings(

	@Json(name="Sunset")
	val sunset: String? = null,

	@Json(name="Asr")
	val asr: String? = null,

	@Json(name="Isha")
	val isha: String? = null,

	@Json(name="Fajr")
	val fajr: String? = null,

	@Json(name="Dhuhr")
	val dhuhr: String? = null,

	@Json(name="Maghrib")
	val maghrib: String? = null,

	@Json(name="Sunrise")
	val sunrise: String? = null,

	@Json(name="Midnight")
	val midnight: String? = null,

	@Json(name="Imsak")
	val imsak: String? = null
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Offset(

	@Json(name="Sunset")
	val sunset: Int? = null,

	@Json(name="Asr")
	val asr: Int? = null,

	@Json(name="Isha")
	val isha: Int? = null,

	@Json(name="Fajr")
	val fajr: Int? = null,

	@Json(name="Dhuhr")
	val dhuhr: Int? = null,

	@Json(name="Maghrib")
	val maghrib: Int? = null,

	@Json(name="Sunrise")
	val sunrise: Int? = null,

	@Json(name="Midnight")
	val midnight: Int? = null,

	@Json(name="Imsak")
	val imsak: Int? = null
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class DataItem(

	@Json(name="date")
	val date: Date? = null,

	@Json(name="meta")
	val meta: Meta? = null,

	@Json(name="timings")
	val timings: Timings? = null
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Hijri(

	@Json(name="date")
	val date: String? = null,

	@Json(name="month")
	val month: Month? = null,

	@Json(name="holidays")
	val holidays: List<String?>? = null,

	@Json(name="year")
	val year: String? = null,

	@Json(name="format")
	val format: String? = null,

	@Json(name="weekday")
	val weekday: Weekday? = null,

	@Json(name="designation")
	val designation: Designation? = null,

	@Json(name="day")
	val day: String? = null
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Date(

	@Json(name="readable")
	val readable: String? = null,

	@Json(name="hijri")
	val hijri: Hijri? = null,

	@Json(name="gregorian")
	val gregorian: Gregorian? = null,

	@Json(name="timestamp")
	val timestamp: String? = null
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Designation(

	@Json(name="expanded")
	val expanded: String? = null,

	@Json(name="abbreviated")
	val abbreviated: String? = null
) : Parcelable
