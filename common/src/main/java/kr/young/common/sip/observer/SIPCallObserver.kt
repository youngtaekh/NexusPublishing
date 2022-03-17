package kr.young.common.sip.observer

import kr.young.common.sip.model.CallModel

interface SIPCallObserver {
    fun onOutgoingCall(model: CallModel)
    fun onCallConnected(model: CallModel)
    fun onCallUpdated(model: CallModel)
    fun onCallTerminated(model: CallModel)
}