package com.demkin.core.services

import com.demkin.core.http.HttpLastFmService
import com.demkin.core.model.Session
import com.fasterxml.jackson.databind.ObjectMapper

open class LastFmService(val session: Session) {
  val mapper = ObjectMapper()
  val fmService = HttpLastFmService()
}