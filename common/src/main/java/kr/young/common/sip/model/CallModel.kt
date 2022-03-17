package kr.young.common.sip.model

class CallModel constructor() {
    var counterpart: String? = null

    constructor(counterpart: String?) : this() {
        this.counterpart = counterpart
    }
}
