package com.demkin.core

import com.demkin.core.http.apiParam
import org.testng.Assert
import org.testng.Assert.*
import org.testng.annotations.Test

/**
 * Created by demkinev on 24.08.2016.
 */

class UtilsTest {


    @Test
    fun paramGenerateTest(){

        val paramArray = arrayOf(Pair("Eugene","26"), Pair("Kate","21"))

        val params  = paramArray.apiParam()

        assertEquals(params, "&Eugene=26&Kate=21")

    }
}
