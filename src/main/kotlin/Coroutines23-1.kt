import kotlinx.coroutines.*

//
// Add VM options: '-Dkotlinx.coroutines.debug' for debug coroutines
//  in Run/Debug Configuration
//


// 代码段1
fun main() = runBlocking {
    val job = launch(Dispatchers.Default) {
        var i = 0
        while (true) {
            Thread.sleep(500L)
            i++
            println("i = $i")
        }
    }

    delay(2000L)

    job.cancel()
    job.join()

    println("End")
}

/*
输出结果

i = 1
i = 2
i = 3
i = 4
i = 5
// 永远停不下来
*/