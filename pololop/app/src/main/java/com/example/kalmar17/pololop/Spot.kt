package com.example.kalmar17.pololop

class Spot() {
    constructor(numberSpot:Int,stateSpot:Boolean,socketSpot:Boolean):this(){
        number = numberSpot
        state = stateSpot
        socket = socketSpot
    }
     var number:Int
        get() {return number}
        set(value){number = value}
     var state:Boolean
        get() {return state}
        set(value) {state = value}
     var socket :Boolean
        get() {return socket}
        set(value) {socket = value}
}