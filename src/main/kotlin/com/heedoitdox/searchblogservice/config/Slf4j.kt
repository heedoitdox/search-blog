package com.heedoitdox.searchblogservice.config

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Slf4j {
    companion object {
        val <reified T : Any> T.log: DslLogger<T>
            inline get() = DslLogger(T::class)
    }
}
