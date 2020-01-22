package com.odiousenterprises.theweather

data class WeatherData(val latitude: Float,
                       val longitude: Float,
                       val timezone: String,
                       val currently: WeatherTimepointData)


data class WeatherTimepointData(val time: Int,
                                val summary: String,
                                val temperature: Float,
                                val apparentTemperature: Float,
                                val nearestStormDistance: Float,
                                val precipIntensity: Float,
                                val precipProbability: Float,
                                val dewPoint: Float,
                                val humidity: Float,
                                val pressure: Float,
                                val windSpeed: Float,
                                val windBearing: Int /* degrees? */,
                                val windGust: Float,
                                val cloudCover: Float,
                                val uvIndex: Float,
                                val visibility: Float,
                                val ozone: Float)