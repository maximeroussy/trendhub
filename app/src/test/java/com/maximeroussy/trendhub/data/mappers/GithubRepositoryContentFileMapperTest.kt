package com.maximeroussy.trendhub.data.mappers

import com.maximeroussy.trendhub.data.api.models.GithubRepositoryContentItemResponse
import com.maximeroussy.trendhub.domain.models.GithubRepositoryContentFile.GithubFileType.DIRECTORY
import com.maximeroussy.trendhub.domain.models.GithubRepositoryContentFile.GithubFileType.FILE
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class GithubRepositoryContentFileMapperTest {
  private lateinit var sut: GithubRepositoryContentFileMapper

  @Before
  fun setUp() {
    sut = GithubRepositoryContentFileMapper()
  }

  @Test
  fun `map returns correct model with DIRECTORY`() {
    val type = "dir"
    val size = 500
    val name = "readme.md"
    val path = "/readme.md"
    val githubRepositoryContentItemResponse = GithubRepositoryContentItemResponse(type, size, name, path)

    val result = sut.map(githubRepositoryContentItemResponse)

    assertThat(result.name, `is`(name))
    assertThat(result.size, `is`(size))
    assertThat(result.type, `is`(DIRECTORY))
  }

  @Test
  fun `map returns correct model with FILE`() {
    val type = "file"
    val size = 500
    val name = "readme.md"
    val path = "/readme.md"
    val githubRepositoryContentItemResponse = GithubRepositoryContentItemResponse(type, size, name, path)

    val result = sut.map(githubRepositoryContentItemResponse)

    assertThat(result.name, `is`(name))
    assertThat(result.size, `is`(size))
    assertThat(result.type, `is`(FILE))
  }
}
