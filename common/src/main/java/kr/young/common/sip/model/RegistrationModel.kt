package kr.young.common.sip.model

class RegistrationModel constructor() {
    var account: String? = null

    constructor(account: String?) : this() {
        this.account = account
    }
}
