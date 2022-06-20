import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex

//
// Add VM options: '-Dkotlinx.coroutines.debug' for debug coroutines
//  in Run/Debug Configuration
//


// 代码段9
fun main() = runBlocking {
    val mutex = Mutex()

    var i = 0
    val jobs = mutableListOf<Job>()

    repeat(10) {
        val job = launch(Dispatchers.Default) {
            repeat(1000) {
                try {
                    mutex.lock()
                    i++
                    i/0 // 故意制造异常
                    mutex.unlock()
                } catch (e: Exception) {
                    println(e)
                }
            }
        }
        jobs.add(job)
    }

    jobs.joinAll()

    println("i = $i")
}

// 程序无法退出