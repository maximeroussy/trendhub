package com.maximeroussy.trendhub.util

import android.content.Context
import java.io.File
import javax.inject.Inject

class FileManager @Inject constructor(private val context: Context) {
  fun getCacheDirectory(): File {
    return context.cacheDir
  }
}
