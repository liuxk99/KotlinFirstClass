import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//
// Add VM options: '-Dkotlinx.coroutines.debug' for debug coroutines
//  in Run/Debug Configuration
//

// 代码段1
fun main() = runBlocking {
    var i = 0

    // Default 线程池
    launch(Dispatchers.Default) {
        var stamp = System.currentTimeMillis()
        repeat(1000) {
            i++
        }
        println("cost: ${System.currentTimeMillis() - stamp}")
    }

    delay(1000L)

    println("i = $i")
}
