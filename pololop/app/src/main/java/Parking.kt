class Parking() {
    constructor(addressParking:String, spot_countParking:Int, spotsParking: Spot, priceParking: Float):this(){
        address = addressParking
        spot_count = spot_countParking
        price = priceParking
        spots = spotsParking
    }
   var address:String =  ""
        get() {return address}
    var spot_count:Int = 0
        get() {return spot_count}
  var spots[spot_count] = Spot()
        get() {return spots}
    var price:Float = 0.0f
        get() {return price}

fun getSpots(){

}
}