package com.maximeroussy.trendhub.presentation

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.maximeroussy.trendhub.R

abstract class BaseActivity : AppCompatActivity() {
  fun showErrorDialog(messageResId: Int) {
    val builder = AlertDialog.Builder(this)
    builder.setTitle(R.string.error)
    builder.setMessage(messageResId)
    builder.setPositiveButton(R.string.ok) { dialog, _ -> dialog.dismiss() }
    builder.show()
  }
}
