package com.example.coroutinesexampleodc_d

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.coroutinesexampleodc_d.ui.theme.CoroutinesExampleODC_DTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


private const val TAG = "Youssef MainActivity"
class MainActivity : ComponentActivity() {
    lateinit var textView : TextView
    lateinit var parentJob : Job
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.actvity_main)
        textView = findViewById<TextView>(R.id.textView)

       parentJob = GlobalScope.launch() {
          val job1 = launch { getDataNetwork() }
          val job2 = launch { getDataNetwork2() }
           joinAll(job1,job2)

            launch { getDataNetwork2() }
        }

    }

    suspend fun getDataNetwork() : String {
        delay(3000)
        return "Youssef"
    }

    suspend fun getDataNetwork2() : String {
        delay(7000)
        return "Youssef"
    }

    suspend fun printTextAfterDelay(text : String){
        withContext(Dispatchers.IO) {
            delay(2000)
            Log.d(TAG, "onCreate: $text")
        }
    }

    override fun onStop() {
        parentJob.cancel()
        super.onStop()
    }
}


fun printTextAfterDelay2(text : String){
    Thread.sleep(2000)
    Log.d(TAG,text)
}


