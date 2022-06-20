import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex

//
// Add VM options: '-Dkotlinx.coroutines.debug' for debug coroutines
//  in Run/Debug Configuration
//

// 代码段7

fun main() = runBlocking {
    val mutex = Mutex()

    var i = 0
    val jobs = mutableListOf<Job>()

    repeat(10) {
        val job = launch(Dispatchers.Default) {
            logX("Before i++")
            repeat(1000) {
                // 变化在这里
                mutex.lock()
                i++
                mutex.unlock()
            }
            logX("After i++")
        }
        jobs.add(job)
    }

    jobs.joinAll()

    println("i = $i")
}