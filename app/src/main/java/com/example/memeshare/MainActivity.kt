package com.example.memeshare

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.RequestBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var currentImageUrl: String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        load()
    }
    private fun load(){
        progressBar.visibility=View.VISIBLE
        NukeSSLCerts.nuke()

// Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.4.1/ir"

// Request a string response from the provided URL.
//        val jsonObjectRequest = JsonObjectRequest(
//            Request.Method.GET, url, null,
//            { response ->
//
//                currentImageUrl =response.getString("url")
//                Glide.with(this ).load(currentImageUrl).listener(object : RequestListener<Drawable>{
//                    override fun onLoadFailed(
//                        e: GlideException?,
//                        model: Any?,
//                        target: Target<Drawable>?,
//                        isFirstResource: Boolean
//                    ): Boolean {
//                        progressBar.visibility=View.GONE
//                        return false
//                    }
//
//                    override fun onResourceReady(
//                        resource: Drawable?,
//                        model: Any?,
//                        target: Target<Drawable>?,
//                        dataSource: DataSource?,
//                        isFirstResource: Boolean
//                    ): Boolean {
//                        progressBar.visibility=View.GONE
//                        return false
//                    }
//                }).into(textView2)
//
//
//
//            },
//            {
//                Toast.makeText(this,it.localizedMessage, Toast.LENGTH_LONG).show()
//
//            })
//
//// Add the requ est to the RequestQueue.
//        queue.add(jsonObjectRequest)


        val mRequestQueue = Volley.newRequestQueue(this)

        // String Request initialized
        val mStringRequest = StringRequest(Request.Method.GET, url, {
            textView2.text = it
        }

        ) { error -> Log.i("Error", "Error :$error") }
        mRequestQueue.add(mStringRequest)


}

    fun shareMeme(view: View) {
        val intent= Intent(Intent.ACTION_SEND)
        intent.type="text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,"Hey, checkout this cool meme $currentImageUrl")
        val chooser=Intent.createChooser(intent,"Share this meme using...")
        startActivity(chooser)

    }
    fun nextMeme(view: View) {
        load()
    }
}

private fun <TranscodeType> RequestBuilder<TranscodeType>.into(textView2: TextView?) {

}
