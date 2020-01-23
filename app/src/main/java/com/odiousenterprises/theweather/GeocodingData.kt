package com.odiousenterprises.theweather

data class GeocodingData(val results: Array<GeocodingResult>)

data class GeocodingResult(val geometry: GeocodingGeometry)

data class GeocodingGeometry(val location: GeocodingLocation)

data class GeocodingLocation(val lat: Float, val lng: Float)