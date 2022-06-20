import kotlinx.coroutines.*

//
// Add VM options: '-Dkotlinx.coroutines.debug' for debug coroutines
//  in Run/Debug Configuration
//


// 代码段4

fun main() = runBlocking {
    suspend fun prepare(){
        // 模拟准备工作
    }
    var i = 0
    val lock = Any()

    val jobs = mutableListOf<Job>()

    repeat(10){
        val job = launch(Dispatchers.Default) {
            repeat(1000) {
                synchronized(lock) {
                    // 编译器报错！
//                    prepare()
                    i++
                }
            }
        }
        jobs.add(job)
    }

    jobs.joinAll()

    println("i = $i")
}