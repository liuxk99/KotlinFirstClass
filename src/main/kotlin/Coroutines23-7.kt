import kotlinx.coroutines.*

//
// Add VM options: '-Dkotlinx.coroutines.debug' for debug coroutines
//  in Run/Debug Configuration
//


// 代码段7

fun main() = runBlocking {

    val parentJob = launch(Dispatchers.Default) {
        launch {
            var i = 0
            while (true) {
                try {
                    delay(500L)
                } catch (e: CancellationException) {
                    println("Catch CancellationException")
                    // 1，注意这里
                    // throw e
                }
                i++
                println("First i = $i")
            }
        }

        launch {
            var i = 0
            while (true) {
                delay(500L)
                i++
                println("Second i = $i")
            }
        }
    }

    delay(2000L)

    parentJob.cancel()
    parentJob.join()

    println("End")
}

/*
输出结果
输出结果
First i = 1
Second i = 1
First i = 2
Second i = 2
First i = 3
Second i = 3
Second i = 4
..
First i = 342825
Catch CancellationException
// 程序将永远无法终止
*/