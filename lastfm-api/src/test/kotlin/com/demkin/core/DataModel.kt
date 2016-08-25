package com.demkin.core

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.annotation.Generated
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("a", "b", "c")
class ExampleMore {
  @JsonProperty("a")
  var a: String? = null

  @JsonProperty("b")
  var b: String? = null

  @JsonProperty("c")
  var c: String? = null

  override fun equals(other: Any?): Boolean{
    if (this === other) return true
    if (other?.javaClass != javaClass) return false

    other as ExampleMore

    if (a != other.a) return false
    if (b != other.b) return false
    if (c != other.c) return false

    return true
  }

  override fun hashCode(): Int{
    var result = a?.hashCode() ?: 0
    result = 31 * result + (b?.hashCode() ?: 0)
    result = 31 * result + (c?.hashCode() ?: 0)
    return result
  }

  override fun toString(): String{
    return "ExampleMore(a=$a, b=$b, c=$c)"
  }
}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("a")
@JsonIgnoreProperties(ignoreUnknown = true)
class ExampleLess {
  @JsonProperty("a")
  var a: String? = null

  override fun equals(other: Any?): Boolean{
    if (this === other) return true
    if (other?.javaClass != javaClass) return false

    other as ExampleLess

    if (a != other.a) return false

    return true
  }

  override fun hashCode(): Int{
    return a?.hashCode() ?: 0
  }


}