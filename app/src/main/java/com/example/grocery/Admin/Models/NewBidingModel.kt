package com.example.grocery.Admin.Models
class NewBidingModel {
         var id:String=""
         var image:String=""
         var imageName:String=""
         var description:String=""
         var bidAmount:Int=0
         var bidingStartTime:String=""
         var bidingEndingTime:String=""
    constructor()
    constructor(id:String,image:String,imageName:String,imageDescription:String,bidAmount:Int,bidingStartTime:String,bidingEndingTime:String){
        this.id=id
        this.image=image
        this.imageName=imageName
        this.description=imageDescription
        this.bidAmount=bidAmount
        this.bidingStartTime=bidingStartTime
        this.bidingEndingTime=bidingEndingTime
    }
}