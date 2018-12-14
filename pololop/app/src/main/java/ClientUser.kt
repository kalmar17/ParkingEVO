import java.util.*

class ClientUser() {
   constructor(fullNameUser:String,nickNameUser:String, licenceUser:String, balanceUser:Int, historyUser:String,
     birthdayUser: Date,  phoneUser:String, emailUser:String, sexUser:Boolean) :this(){
       fullName = fullNameUser
       nickName=nickNameUser
       licence=licenceUser
       balance=balanceUser
       birthday=birthdayUser
       phone=phoneUser
       email=emailUser
       sex=sexUser
    }
     var fullName:String = ""
        get() {return fullName   }
     var car = Car()
        get() {return car}
     var nickName:String = ""
        get() {return nickName}
     var licence:String = ""
        get() {return licence}
     var  balance:Int = 0
        get() {return balance}
     var history = History()
        get() {return history}
     var  birthday: Date? = null
        get() {return birthday}
     var phone:String = ""
        get() {return phone}
     var email:String = ""
        get() {return email}
     var sex:Boolean = false
        get() {return sex}


}