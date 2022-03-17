package kr.young.common.sip.observer

import kr.young.common.sip.model.CallModel
import kr.young.common.sip.model.RegistrationModel

class SIPObserverImpl: SIPPublisher {

    private val registrationObservers = mutableListOf<SIPRegistrationObserver>()
    private val callObservers = mutableListOf<SIPCallObserver>()

    override fun add(observer: SIPRegistrationObserver) {
        registrationObservers.add(observer)
    }

    override fun add(observer: SIPCallObserver) {
        callObservers.add(observer)
    }

    override fun remove(observer: SIPRegistrationObserver) {
        registrationObservers.remove(observer)
    }

    override fun remove(observer: SIPCallObserver) {
        callObservers.remove(observer)
    }

    override fun onRegistrationSuccessObserver(model: RegistrationModel) {
        for (observer in registrationObservers) {
            observer.onRegistrationSuccess(model)
        }
    }

    override fun onRegistrationFailedObserver(model: RegistrationModel) {
        for (observer in registrationObservers) {
            observer.onRegistrationFailed(model)
        }
    }

    override fun onUnRegistrationSuccessObserver(model: RegistrationModel) {
        for (observer in registrationObservers) {
            observer.onUnRegistrationSuccess(model)
        }
    }

    override fun onUnRegistrationFailedObserver(model: RegistrationModel) {
        for (observer in registrationObservers) {
            observer.onUnRegistrationFailed(model)
        }
    }

    override fun onIncomingCallObserver(model: CallModel) {
        for (observer in registrationObservers) {
            observer.onIncomingCall(model)
        }
    }

    override fun onOutgoingCallObserver(model: CallModel) {
        for (observer in callObservers) {
            observer.onOutgoingCall(model)
        }
    }

    override fun onCallConnectedObserver(model: CallModel) {
        for (observer in callObservers) {
            observer.onCallConnected(model)
        }
    }

    override fun onCallUpdatedObserver(model: CallModel) {
        for (observer in callObservers) {
            observer.onCallUpdated(model)
        }
    }

    override fun onCallTerminatedObserver(model: CallModel) {
        for (observer in callObservers) {
            observer.onCallTerminated(model)
        }
    }

    private object Holder {
        val INSTANCE = SIPObserverImpl()
    }

    companion object {
        val instance: SIPObserverImpl by lazy { Holder.INSTANCE }
        private const val TAG = "SIPObserverImpl"
    }
}