import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*

//
// Add VM options: '-Dkotlinx.coroutines.debug' for debug coroutines
//  in Run/Debug Configuration
//
fun main(args: Array<String>) = runBlocking {
    println("${Date()}: =>" + Thread.currentThread().name)

    launch {
        println("${Date()}: =>" + Thread.currentThread().name)
        delay (100L)
        println("${Date()}: <-" + Thread.currentThread().name)
    }

    println("${Date()}: => Thread.sleep()")
    Thread.sleep(1000L)
    println("${Date()}: <- Thread.sleep()")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("${Date()}: Program arguments: ${args.joinToString()}")

    println("${Date()}: <-" + Thread.currentThread().name)
}