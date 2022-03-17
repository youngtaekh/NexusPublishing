package kr.young.common.sip.observer

import kr.young.common.sip.model.CallModel
import kr.young.common.sip.model.RegistrationModel

interface SIPPublisher {
    fun add(observer: SIPRegistrationObserver)
    fun remove(observer: SIPRegistrationObserver)
    fun add(observer: SIPCallObserver)
    fun remove(observer: SIPCallObserver)

    fun onRegistrationSuccessObserver(model: RegistrationModel)
    fun onRegistrationFailedObserver(model: RegistrationModel)
    fun onUnRegistrationSuccessObserver(model: RegistrationModel)
    fun onUnRegistrationFailedObserver(model: RegistrationModel)

    fun onIncomingCallObserver(model: CallModel)
    fun onOutgoingCallObserver(model: CallModel)
    fun onCallConnectedObserver(model: CallModel)
    fun onCallUpdatedObserver(model: CallModel)
    fun onCallTerminatedObserver(model: CallModel)
}