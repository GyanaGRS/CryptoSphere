package com.example.cryptosphere.Model

data class Model (
    var name: String,
    var symbol:String,
    var price:Double,
    var changePercent:Double,
    var lineData:ArrayList<Int>,
    var propertyAmount:Double,
    var propertySize:Double
)