package com.dev.movieapp.models.movieslist

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty

data class Multimedia(

	@JsonProperty("type") val type: String?,
	@JsonProperty("src") val src: String?,
	@JsonProperty("height") val height: Int,
	@JsonProperty("width") val width: Int
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readInt(),
		parcel.readInt()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(type)
		parcel.writeString(src)
		parcel.writeInt(height)
		parcel.writeInt(width)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Multimedia> {
		override fun createFromParcel(parcel: Parcel): Multimedia {
			return Multimedia(parcel)
		}

		override fun newArray(size: Int): Array<Multimedia?> {
			return arrayOfNulls(size)
		}
	}
}