package com.maximeroussy.trendhub.data.api

import org.junit.Before

import org.junit.Assert.*

class GithubHeaderParserTest {
  private lateinit var sut: GithubHeaderParser

  @Before
  fun setUp() {
    sut = GithubHeaderParser()
  }
}
