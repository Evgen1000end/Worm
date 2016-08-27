package com.demkin.core.services

import com.demkin.core.http.HttpLastFmService
import com.demkin.core.model.Session
import com.fasterxml.jackson.databind.ObjectMapper

/**
 * Created by demkinev on 27.08.2016.
 */
open class LastFmService(val session:Session){
  val mapper = ObjectMapper()
  val fmService = HttpLastFmService()
}