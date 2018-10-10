package com.maximeroussy.trendhub.data.mappers

import com.maximeroussy.trendhub.data.api.models.GithubRepositoryContentItemResponse
import com.maximeroussy.trendhub.domain.models.GithubRepositoryContentFile
import com.maximeroussy.trendhub.domain.models.GithubRepositoryContentFile.GithubFileType
import com.maximeroussy.trendhub.domain.models.GithubRepositoryContentFile.GithubFileType.DIRECTORY
import com.maximeroussy.trendhub.domain.models.GithubRepositoryContentFile.GithubFileType.FILE
import javax.inject.Inject

class GithubRepositoryContentFileMapper @Inject constructor() {
  fun map(githubRepositoryContentItemResponse: GithubRepositoryContentItemResponse): GithubRepositoryContentFile {
    return GithubRepositoryContentFile(
        githubRepositoryContentItemResponse.name,
        githubRepositoryContentItemResponse.size,
        convertTypeStringToEnum(githubRepositoryContentItemResponse.type)
    )
  }

  private fun convertTypeStringToEnum(typeString: String): GithubFileType {
    return when(typeString) {
      "file" -> FILE
      "dir" -> DIRECTORY
      else -> FILE
    }
  }
}
