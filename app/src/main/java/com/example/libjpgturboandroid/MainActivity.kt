package com.example.libjpgturboandroid

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.libjpgturboandroid.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var bitmap = BitmapFactory.decodeResource(resources,R.drawable.jett)
        var file=File(cacheDir,"jett.jpeg")
        var result= compressJpg(bitmap,5,
            file.absolutePath)
        Toast.makeText(this, "压缩结果："+result, Toast.LENGTH_SHORT).show()
        binding.ivCompress.setImageBitmap(BitmapFactory.decodeFile(file.absolutePath));

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
//    jobject bitmap, jint optimize,
//    jstring destFile_
    external fun compressJpg(bitmap: Bitmap,optimize:Int,destFile:String): Int

    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}