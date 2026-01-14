package com.family.okr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.room.*

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            OkrDatabase::class.java,
            "okr_db"
        ).allowMainThreadQueries().build()

        val dao = db.okrDao()

        setContent {
            var title by remember { mutableStateOf("") }
            val okrs = remember { mutableStateListOf<Okr>() }

            okrs.clear()
            okrs.addAll(dao.getAll())

            Column(modifier = Modifier.padding(16.dp)) {

                Text("家庭 OKR", style = MaterialTheme.typography.headlineMedium)

                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("输入目标") }
                )

                Button(onClick = {
                    if (title.isNotBlank()) {
                        dao.insert(Okr(title = title))
                        title = ""
                    }
                }) {
                    Text("添加目标")
                }

                LazyColumn {
                    items(okrs) {
                        Text("• ${it.title}", modifier = Modifier.padding(8.dp))
                    }
                }
            }
        }
    }
}