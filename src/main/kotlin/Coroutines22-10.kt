import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex

//
// Add VM options: '-Dkotlinx.coroutines.debug' for debug coroutines
//  in Run/Debug Configuration
//

// 代码段10
fun main() = runBlocking {
    val mutex = Mutex()

    var i = 0
    val jobs = mutableListOf<Job>()

    repeat(10) {
        val job = launch(Dispatchers.Default) {
            repeat(1000) {
                // 变化在这里
                mutex.withLock {
                    i++
                }
            }
        }
        jobs.add(job)
    }

    jobs.joinAll()

    println("i = $i")
}

// withLock的定义
public suspend inline fun <T> Mutex.withLock(owner: Any? = null, action: () -> T): T {
    lock(owner)
    try {
        return action()
    } finally {
        unlock(owner)
    }
}