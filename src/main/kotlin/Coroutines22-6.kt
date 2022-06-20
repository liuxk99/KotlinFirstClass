import kotlinx.coroutines.*
import java.util.concurrent.Executors

//
// Add VM options: '-Dkotlinx.coroutines.debug' for debug coroutines
//  in Run/Debug Configuration
//


// 代码段6
fun main() = runBlocking {
    val mySingleDispatcher = Executors.newSingleThreadExecutor {
        Thread(it, "MySingleThread").apply { isDaemon = true }
    }.asCoroutineDispatcher()

    var i = 0
    val jobs = mutableListOf<Job>()

    repeat(10) {
        val job = launch(mySingleDispatcher) {
            repeat(1000) {
                i++
            }
        }
        jobs.add(job)
    }

    jobs.joinAll()

    println("i = $i")
}

/*
输出结果
i = 10000
*/