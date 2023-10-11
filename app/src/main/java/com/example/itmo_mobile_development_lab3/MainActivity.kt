package com.example.itmo_mobile_development_lab3

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val startButton: Button = findViewById(R.id.button)
        startButton.text = "Вывести с задержкой поток корутины"
        startButton.setOnClickListener {
            CoroutineScope(IO).launch {
                changeText()
            }
        }

        val countText : TextView = findViewById(R.id.countText)
        CoroutineScope(IO).launch {
            repeat(9999) {
                delay(1000)
                countText.text = it.toString()
            }
        }
    }

    private fun setNewText(input: String){
        val text: TextView = findViewById(R.id.textView)
        val newText = text.text.toString() + "\n$input"
        text.text = newText
    }
    private suspend fun getCurrentThread(): String{
        delay(1000)
        return "Hello from thread ${Thread.currentThread().name}!"
    }
    private suspend fun changeText(){
        val text = getCurrentThread()
        setNewText(text)
    }
}