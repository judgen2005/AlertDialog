package com.example.alertdialog

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etAge: EditText
    private lateinit var btnSave: Button
    private lateinit var lvUsers: ListView
    private lateinit var userListAdapter: ArrayAdapter<String>
    private val users = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etName = findViewById(R.id.etName)
        etAge = findViewById(R.id.etAge)
        btnSave = findViewById(R.id.btnSave)
        lvUsers = findViewById(R.id.lvUsers)
        userListAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf())
        lvUsers.adapter = userListAdapter
        setSupportActionBar(findViewById(R.id.toolbar))
        btnSave.setOnClickListener { saveUser() }
        lvUsers.setOnItemClickListener { _, _, position, _ -> deleteUser(position) }
    }

    private fun saveUser() {
        val name = etName.text.toString()
        val age = etAge.text.toString().toIntOrNull()
        if (name.isBlank() || age == null) {
            Toast.makeText(this, "Введите корректные данные", Toast.LENGTH_LONG).show()
            return
        }
        val user = User(name, age)
        users.add(user)
        userListAdapter.add("$name - $age лет")
        etName.text.clear()
        etAge.text.clear()
    }

    private fun deleteUser(position: Int) {
        val user = users[position]
        val dialog = MyDialog(
            context = this,
            user = user,
            userList = users,
            userListAdapter = userListAdapter,
            position = position
        )
        dialog.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_exit -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}