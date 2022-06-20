import kotlinx.coroutines.*

//
// Add VM options: '-Dkotlinx.coroutines.debug' for debug coroutines
//  in Run/Debug Configuration
//

// 代码段13

fun main() = runBlocking {
    val result = (1..10).map {
        async(Dispatchers.Default) {
            var i = 0
            repeat(1000) {
                i++
            }
            return@async i
        }
    }.awaitAll()
        .sum()

    println("i = $result")
}