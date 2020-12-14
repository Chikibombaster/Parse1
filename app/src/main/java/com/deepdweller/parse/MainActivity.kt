package com.deepdweller.parse
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
class MainActivity : AppCompatActivity() {
    lateinit var button: Button
    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "KotlinApp"
        textView = findViewById(R.id.textView)
        button = findViewById(R.id.btnParseHTML)
        button.setOnClickListener {
            getHtmlFromWeb()
        }
    }
    private fun getHtmlFromWeb() {
        Thread(Runnable {
            val stringBuilder = StringBuilder()
            try {
                val doc: Document = Jsoup.connect("https://yandex.ru/pogoda/barnaul").get()
                val title: String = doc.title()
                val links: Elements = doc.select("div.weather-table_temp")
                stringBuilder.append(title).append("\n")
                for (link in links) {
                    stringBuilder.append("\n").append("Link :").append(link.attr("weather-table_temp")).append("\n").append("Text : ").append(link.text())
                }
            } catch (e: IOException) {
                stringBuilder.append("Error : ").append(e.message).append("\n")
            }
            runOnUiThread { textView.text = stringBuilder.toString() }
        }).start()
    }
}