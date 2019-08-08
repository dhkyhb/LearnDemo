package dhkyhb.kotlin.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlin.system.measureTimeMillis

/**
 *Create by hl
 *Data by 2019-08-07
 *Description:
 */
class coroutinesCls {

    fun funcBase() {
        GlobalScope.launch {
            //后台启动一个新协程并继续
            delay(2000)//非阻塞等待2S
            println("World!")//延迟输出
        }
        println("Hello,")//输出文字
//        Thread.sleep(3000)//阻塞3S来保证JVM存活
        runBlocking {
            //runBloacking会阻塞主程序
            delay(3000)
        }
    }

    fun funcJob(): Unit = runBlocking {
        val job = GlobalScope.launch {
            delay(1000)
            println("World!")
        }
        println("Hello.")
        job.join()
    }

    /*
    相对于上面代码
    此次操作执行在当前指定作用域内启用协程,而不是在通常使用线程（线程总是全局的）那种在GlobalScope中启动协程
     */
    fun funcRunBlock(): Unit = runBlocking {
        launch {
            delay(1000)
            println("World!")
        }
        println("Hello.")
    }

    fun funcCoroutinesScope() = runBlocking {
        launch {
            delay(2000)
            println("launch in mainScope")
        }

        /*
        runBlocking与coroutinesScope区别在于
        后者在子协程完毕之前不会阻塞当前线程
         */
        coroutineScope {
            //新建一个coroutines协程作用域
            launch {
                delay(500)
                println("launch in new Scope")
            }

            delay(100)
            println("Task from coroutines scoope")
        }

        println("coroutine scope is over")
    }

    /*
    syspend挂起函数可以在其他挂起函数或者协程作用域中使用
     */
    suspend fun funcSuspend() {
        delay(2000)
        println("launch in mainScope")
    }

    fun funcLight() = runBlocking {
        repeat(100_000) {
            launch {
                delay(1000)
                println("$it.......")
            }
        }
    }

    fun funcGlobal() = runBlocking {
        GlobalScope.launch {
            repeat(100_00) {
                println("i'm sleeping.....")
                delay(500)
            }
        }
        delay(1500)
    }

    fun funcTimeout() = runBlocking {
        launch {
            try {
//                withTimeout(1300) {
                withTimeoutOrNull(1300) {
                    repeat(1000) {
                        println("I'm sleeping$it.....")
                        delay(500)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * 通道
     * 延期的值提供一种便捷的方法在不同协程之间进行相互传输。通道提供一种在流中传输值得方法.
     */

    fun funcChannel() = runBlocking {
        val channel = Channel<Int>()
        launch {
            //这里可以使消耗大量CPU运算的异步逻辑，我们只做简单的算法
            for (x in 1..5) channel.send(x * x)
        }
        repeat(5) { println("recevie:${channel.receive()}") }
        println("Done!")
    }

    fun funcCloseChannel() = runBlocking {
        val channel = Channel<Int>()
        launch {
            //这里可以使消耗大量CPU运算的异步逻辑，我们只做简单的算法
            for (x in 1..5) channel.send(x * x)
            channel.close()//结束发送
        }
        for (it in channel) println("recevie:$it")
        println("Done!")
    }

    fun CoroutineScope.produceSquares() = produce {
        for (x in 1..5) send(x * x)
    }

    fun funcProduce() = runBlocking {
        val squares = produceSquares()
        squares.consumeEach { println(it) }
        println("Done!")
    }

    fun funcCapacity() = runBlocking {
        val channel = Channel<Int>(4)//启动带缓冲的通道
        val sender = launch {
            repeat(10) {
                println("send $it")//放入通道前打印 总共打印五次 四次放入通道中，试图发起第五次的时候挂起
                channel.send(it)//将在缓冲通道被占满的时候挂起
            }
        }
        //没有接受东西,等待
        delay(1500)
        sender.cancel()
    }

    suspend fun answerOne(): Int {
        delay(1000)
        return 13
    }

    suspend fun answerTwo(): Int {
        delay(1000)
        return 25
    }

    fun funcCalTIme() = runBlocking {
        val taskTime = measureTimeMillis {
            val one = answerOne()
            val two = answerTwo()
            println("The Answer is ${one + two}")
        }
        println("The Task Time is $taskTime")
    }

    /*
    在概念上，async 就类似于 launch。它启动了⼀个单独的协程，这是⼀个轻量级的线程并与其它所有的 协程⼀起并发的⼯作。
    不同之处在于 launch 返回⼀个 Job 并且不附带任何结果值，⽽ async 返回 ⼀个 Deferred —— ⼀个轻量级的⾮阻塞 future，
    这代表了⼀个将会在稍后提供结果的 promise。你 可以使⽤ .await() 在⼀个延期的值上得到它的最终结果，
    但是 Deferred 也是⼀个 Job ，所以 如果需要的话，你可以取消它。
     */
    fun funcAsync() = runBlocking {
        val taskTime = measureTimeMillis {
            val one = async { answerOne() }
            val two = async { answerTwo() }
            println("The Answer is ${one.await() + two.await()}")
        }
        println("The Task Time is $taskTime")
    }

    fun funcAsyncLazy() = runBlocking {
        val time = measureTimeMillis {
            val one = async(start = CoroutineStart.LAZY) { answerOne() }
            val two = async(start = CoroutineStart.LAZY) { answerTwo() }
            one.start()
            two.start()
            println("The answer is ${one.await() + two.await()}")
        }
        println("The time is $time")
    }

    fun funcThreadNmae() = runBlocking {
        launch {
            // 运⾏在⽗协程的上下⽂中，即 runBlocking 主协程
            println("main runBlocking : I'm working in thread ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Unconfined) {
            // 不受限的——将⼯作在主线程中
            println("Unconfined : I'm working in thread ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Default) {
            // 将会获取默认调度器
            println("Default : I'm working in thread ${Thread.currentThread().name}")
        }
        launch(newSingleThreadContext("MyOwnThread")) {
            // 将使它获得⼀个新的线程
            println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
        }
    }

}