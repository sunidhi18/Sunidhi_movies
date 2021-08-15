package com.dev.movieapp.models.movieslist

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty

data class MovieResults(

	@JsonProperty("display_title") val display_title: String?,
	@JsonProperty("mpaa_rating") val mpaa_rating: String?,
	@JsonProperty("critics_pick") val critics_pick: Int,
	@JsonProperty("byline") val byline: String?,
	@JsonProperty("headline") val headline: String?,
	@JsonProperty("summary_short") val summary_short: String?,
	@JsonProperty("publication_date") val publication_date: String?,
	@JsonProperty("opening_date") val opening_date: String?,
	@JsonProperty("date_updated") val date_updated: String?,
	@JsonProperty("link") val link: Link?,
	@JsonProperty("multimedia") val multimedia: Multimedia?
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readInt(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readParcelable(Link::class.java.classLoader),
		parcel.readParcelable(Multimedia::class.java.classLoader)
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(display_title)
		parcel.writeString(mpaa_rating)
		parcel.writeInt(critics_pick)
		parcel.writeString(byline)
		parcel.writeString(headline)
		parcel.writeString(summary_short)
		parcel.writeString(publication_date)
		parcel.writeString(opening_date)
		parcel.writeString(date_updated)
		parcel.writeParcelable(link, flags)
		parcel.writeParcelable(multimedia, flags)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<MovieResults> {
		override fun createFromParcel(parcel: Parcel): MovieResults {
			return MovieResults(parcel)
		}

		override fun newArray(size: Int): Array<MovieResults?> {
			return arrayOfNulls(size)
		}
	}
}