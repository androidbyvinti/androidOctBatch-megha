package com.bmpl.sqlitedatabase_nishamohit

//POJO --> pure old java object --> Java Bean

class Details {
    var id: Int = 0
    var name: String? = null
    var phn: Long = 0

    internal constructor() {}

    internal constructor(id: Int, name: String, phn: Long) {
        this.id = id
        this.name = name
        this.phn = phn
    }
}
