package kr.young.common

class Singleton private constructor() {

    private object Holder {
        val INSTANCE = Singleton()
    }

    companion object {
        val instance: Singleton by lazy { Holder.INSTANCE }
        @Volatile
        private var instance1: Singleton? = null

        @JvmStatic
        fun getInstance1(): Singleton {
            if (instance1 == null) {
                instance1 = Singleton()
            }
            return instance1!!
        }
    }
}
