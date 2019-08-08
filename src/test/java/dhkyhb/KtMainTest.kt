package dhkyhb

import dhkyhb.kotlin.coroutines.coroutinesCls
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

/**
 * Create by hl
 * Data by 2019-08-05
 * Description:
 */
class KtMainTest{

    @Test fun textCoroutines() {
        coroutinesCls().funcThreadNmae()
    }

}