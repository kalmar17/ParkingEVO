package com.example.kalmar17.pololop

class Car() {
    constructor(numberCar:String,colorCar:String,carNickNameCar:String,brandCar:String,parkingCar: Parking,currLocationCar:String,sharedCar:String ):this(){
        number = numberCar
        color = colorCar
        carNickName = carNickNameCar
        brand = brandCar
        parking = parkingCar
        currLocation= currLocationCar
        shared = sharedCar
    }

     var number:String = ""
        get() {return number}
     var color:String = ""
        get() {return color}
     var  carNickName:String = ""
        get() {return carNickName}
     var brand:String = ""
        get() {return brand}
     var  parking = Parking()
        get() {return parking}
     var currLocation:String = ""
        get() {return currLocation}
     var shared:String = ""
        get() {return shared}

}