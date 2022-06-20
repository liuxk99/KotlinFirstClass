import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//
// Add VM options: '-Dkotlinx.coroutines.debug' for debug coroutines
//  in Run/Debug Configuration
//


// 代码段5

fun main() = runBlocking {

    val parentJob = launch(Dispatchers.Default) {
        launch {
            var i = 0
            while (true) {
                // 变化在这里
                delay(500L)
                i++
                println("First i = $i")
            }
        }

        launch {
            var i = 0
            while (true) {
                // 变化在这里
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
First i = 1
Second i = 1
First i = 2
Second i = 2
First i = 3
Second i = 3
End
*/