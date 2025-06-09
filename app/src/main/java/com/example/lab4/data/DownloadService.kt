package com.example.lab4.data

import android.content.Context
import android.os.Environment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL

class DownloadService(private val context: Context) {
    suspend fun downloadFile(url: String): Result<File> = withContext(Dispatchers.IO) {
        try {
            val urlConnection = URL(url).openConnection() as HttpURLConnection
            urlConnection.connect()
            
            val inputStream = BufferedInputStream(urlConnection.inputStream)
            val directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val file = File(directory, "downloaded_file.pdf")
            
            FileOutputStream(file).use { fileOutput ->
                BufferedOutputStream(fileOutput).use { bufferedOutput ->
                    val buffer = ByteArray(1024)
                    var bytesRead: Int
                    while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                        bufferedOutput.write(buffer, 0, bytesRead)
                    }
                }
            }
            
            inputStream.close()
            Result.success(file)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
} 