import kotlinx.coroutines.*
import java.util.concurrent.Executors

//
// Add VM options: '-Dkotlinx.coroutines.debug' for debug coroutines
//  in Run/Debug Configuration
//

// 代码段4

fun main() = runBlocking {
    val parentJob = launch(fixedDispatcher) {

        // 变化在这里
        launch {
            var i = 0
            while (isActive) {
                Thread.sleep(500L)
                i ++
                println("First i = $i")
            }
        }

        launch {
            var i = 0
            while (isActive) {
                Thread.sleep(500L)
                i ++
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
First i = 1
Second i = 1
First i = 2
Second i = 2
First i = 3
Second i = 3
First i = 4
Second i = 4
End
*/