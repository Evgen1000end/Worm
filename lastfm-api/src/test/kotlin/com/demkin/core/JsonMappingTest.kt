package com.demkin.core

import org.testng.annotations.Test
import javax.annotation.Generated
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.databind.ObjectMapper
import org.testng.Assert
import org.testng.Assert.assertEquals

/**
 * Created by demkinev on 25.08.2016.
 */
class JsonMappingTest {

  val json = "{\"a\":\"a\",\"b\":\"b\"}"
  val mapper = ObjectMapper()

  @Test
  fun testMoreFieldsInJson(){
    val actual = mapper.readValue(json, ExampleLess::class.java)
    val expected = ExampleLess()
    expected.a = "a"
    assertEquals(actual, expected)
  }

  @Test
  fun testLessFieldsInJson(){
    val actual = mapper.readValue(json, ExampleMore::class.java)
    val expected = ExampleMore()
    expected.a = "a"
    expected.b ="b"
    assertEquals(actual, expected)
  }




}