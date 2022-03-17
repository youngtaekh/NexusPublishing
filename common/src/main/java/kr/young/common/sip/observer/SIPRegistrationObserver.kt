package kr.young.common.sip.observer

import kr.young.common.sip.model.CallModel
import kr.young.common.sip.model.RegistrationModel

interface SIPRegistrationObserver {
    fun onIncomingCall(model: CallModel)
    fun onRegistrationSuccess(model: RegistrationModel)
    fun onRegistrationFailed(model: RegistrationModel)
    fun onUnRegistrationSuccess(model: RegistrationModel)
    fun onUnRegistrationFailed(model: RegistrationModel)
}