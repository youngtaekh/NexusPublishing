package kr.young.common

import android.content.Context
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.FileReader
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class FileUtil {
    companion object {
        private const val TAG = "FileUtil"

        @JvmStatic
        fun getFileList(path: String): Array<String>? {
            val dir = File(path)
            for (fileName in dir.list()!!) {
                DebugLog.i(TAG, fileName)
            }
            return dir.list()
        }

        @JvmStatic
        fun readFile(file: File) {
            try {
                val fileReader = FileReader(file)
                val bufferedReader = BufferedReader(fileReader)
                var line = bufferedReader.readLine()
                while (line != null) {
                    DebugLog.i(TAG, line)
                    line = bufferedReader.readLine()
                }
                bufferedReader.close()
                fileReader.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        @JvmStatic
        fun copy(inputStream: InputStream, outputStream: OutputStream) {
            inputStream.use {
                outputStream.use {
                    val buf = ByteArray(1024)
                    var len: Int
                    while (inputStream.read(buf).also { len = it } > 0) {
                        outputStream.write(buf, 0, len)
                    }
                }
            }
        }

        @Throws(IOException::class)
        @JvmStatic
        fun copy(src: File?, dst: File?) {
            val inputStream: InputStream = FileInputStream(src)
            val outputStream: OutputStream = FileOutputStream(dst)
            copy(inputStream, outputStream)
        }

        @JvmStatic
        fun copyAssets(context: Context, fileName: String) {
            val assetManager = context.assets

            val list = assetManager.list("")
            if (list!!.isEmpty()) {
                DebugLog.i(TAG, "asset list is empty")
            }

            val filePath = context.filesDir.path + File.separator + fileName

            val inputStream = assetManager.open(fileName)
            val outputStream = FileOutputStream(filePath)
            copy(inputStream, outputStream)
        }
    }
}
