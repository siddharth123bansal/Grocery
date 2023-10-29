package com.example.grocery.Admin.Models

class UserData {
    var useraddressline1: String = ""
    var useraddressline2: String = ""
    var usercity: String = ""
    var usercountry: String = ""
    var usergovid: String = ""
    var userid: String = ""
    var usermobile: String = ""
    var username: String = ""
    var userprofile: String = ""
    var userstate: String = ""
    var useruploadimg: String = ""
    var userzipcode: String = ""
    constructor()
    constructor(
        useraddressline1: String,
        useraddressline2: String,
        usercity: String,
        usercountry: String,
        usergovid: String,
        userid: String,
        usermobile: String,
        username: String,
        userprofile: String,
        userstate: String,
        useruploadimg: String,
        userzipcode: String
    ) {
        this.useraddressline1 = useraddressline1
        this.useraddressline2 = useraddressline2
        this.usercity = usercity
        this.usercountry = usercountry
        this.usergovid = usergovid
        this.userid = userid
        this.usermobile = usermobile
        this.username = username
        this.userprofile = userprofile
        this.userstate = userstate
        this.useruploadimg = useruploadimg
        this.userzipcode = userzipcode
    }

}