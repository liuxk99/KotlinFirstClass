import kotlinx.coroutines.*

//
// Add VM options: '-Dkotlinx.coroutines.debug' for debug coroutines
//  in Run/Debug Configuration
//


// 代码段3

fun main() = runBlocking {
    var i = 0
    val lock = Any() // 变化在这里

    val jobs = mutableListOf<Job>()

    repeat(10){
        val job = launch(Dispatchers.Default) {
            repeat(1000) {
                // 变化在这里
                synchronized(lock) {
                    i++
                }
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