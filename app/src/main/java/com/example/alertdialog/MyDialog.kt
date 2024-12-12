package com.example.alertdialog

import android.app.AlertDialog
import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Toast

class MyDialog(
    private val context: Context,
    private val user: User,
    private val userList: MutableList<User>,
    private val userListAdapter: ArrayAdapter<String>,
    private val position: Int
) {
    fun show() {
        AlertDialog.Builder(context)
            .setTitle("Удаляем пользователя")
            .setMessage("Вы действительно хотите удалить пользователя '${user.name} - ${user.age} лет'?")
            .setIcon(R.drawable.ic_delete_user)
            .setPositiveButton("Да") { _, _ ->
                confirmAction()
            }
            .setNegativeButton("Нет") { dialog, _ ->
                onCancel()
                dialog.dismiss()
            }
            .show()
    }

    private fun confirmAction() {
        userList.removeAt(position)
        userListAdapter.remove(userListAdapter.getItem(position))
        Toast.makeText(context, "Пользователь '${user.name} - ${user.age} лет' удалён", Toast.LENGTH_LONG).show()
    }

    private fun onCancel() {
        Toast.makeText(context, "Вы отменили удаление пользователя '${user.name} - ${user.age} лет'", Toast.LENGTH_LONG).show()
    }
}