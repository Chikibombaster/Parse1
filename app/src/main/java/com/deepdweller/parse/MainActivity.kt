package com.deepdweller.parse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.deepdweller.parse.R
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.w3c.dom.Text
import java.io.IOException
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun parse(view: View){
        Thread(Runnable {
            val stringBuilder = StringBuilder()
            try {
                val doc: Document = Jsoup.connect("https://yandex.ru/pogoda/barnaul").get()
                val temp: String = doc.select("div.temp.fact__temp.fact__temp_size_s > span.temp__value").text()
                val title: String = doc.title()
                stringBuilder.append(title).append("\n").append(temp)
            } catch (e: IOException) {
                stringBuilder.append("Error : ").append(e.message).append("\n")
            }
            runOnUiThread { textView.text = stringBuilder.toString() }
        }).start()
    }
}