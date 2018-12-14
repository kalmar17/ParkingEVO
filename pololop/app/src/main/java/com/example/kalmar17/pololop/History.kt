package com.example.kalmar17.pololop

import java.sql.Time
import java.util.*

open class History() {
    var date:Date
    get() {return date}
    set(value) {date = value}
    var time:Time
    get() {return time}
    set(value) {time = value}
    var car = Car()
    get() {return car}


}