package com.dev.movieapp.models.movieslist

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty

data class Link (

	@JsonProperty("type") val type : String?,
	@JsonProperty("url") val url : String?,
	@JsonProperty("suggested_link_text") val suggested_link_text : String?
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readString()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(type)
		parcel.writeString(url)
		parcel.writeString(suggested_link_text)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Link> {
		override fun createFromParcel(parcel: Parcel): Link {
			return Link(parcel)
		}

		override fun newArray(size: Int): Array<Link?> {
			return arrayOfNulls(size)
		}
	}
}