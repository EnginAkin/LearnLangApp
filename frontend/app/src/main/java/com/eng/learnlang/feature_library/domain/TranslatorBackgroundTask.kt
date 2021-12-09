package com.eng.learnlang.feature_library.domain

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import okio.utf8Size
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


class TranslatorBackgroundTask internal constructor(ctx: Context) :
    AsyncTask<String?, Void?, String?>() {
    //Declare Context
    var ctx: Context

     lateinit var  responseYandexTranslateApi:ResponseYandexTranslateApi


    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun onPostExecute(result: String?) {}
    protected override fun onProgressUpdate(vararg values: Void?) {
        super.onProgressUpdate(*values)
    }

    //Set Context
    init {
        this.ctx = ctx
    }

    override fun doInBackground(vararg params: String?): String? {
        //String variables
        val textToBeTranslated = params[0]
        val languagePair = params[1]
        var jsonString: String
        try {
            //Set up the translation call URL
            val yandexKey = "trnsl.1.1.20211010T170159Z.7123a258c5086cb0.d9d48f4ab28f7a341187fb09600f82cf1b1ca166"
            val yandexUrl =
                ("https://translate.yandex.net/api/v1.5/tr.json/translate?key=" + yandexKey
                        + "&text=" + textToBeTranslated + "&lang=" + languagePair)
            val yandexTranslateURL = URL(yandexUrl)

            //Set Http Conncection, Input Stream, and Buffered Reader
            val httpJsonConnection: HttpURLConnection =
                yandexTranslateURL.openConnection() as HttpURLConnection
            val inputStream: InputStream = httpJsonConnection.getInputStream()
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            val allText = inputStream.bufferedReader().use(BufferedReader::readText)
            //Set string builder and insert retrieved JSON result into it
            val stringArray = allText.split(",").toTypedArray()
            val code = stringArray.get(0).split(":")[1]
            val lang = stringArray.get(1).split(":")[1]
            val text=stringArray.get(2).split(":")[1]
            val jsonStringBuilder = StringBuilder()
            responseYandexTranslateApi= ResponseYandexTranslateApi(code,lang.substring(1,lang.length-1),text.substring(2,text.length-3))

            bufferedReader.close()
            inputStream.close()
            httpJsonConnection.disconnect()

            var resultString = allText.toString().trim { it <= ' ' }
            resultString = resultString.substring(resultString.indexOf('[') + 1)
            resultString = resultString.substring(0, resultString.indexOf("]"))
            //Getting the characters between " and "
            resultString = resultString.substring(resultString.indexOf("\"") + 1)
            resultString = resultString.substring(0, resultString.indexOf("\""))
            Log.d("Translation Result:", resultString)
            return jsonStringBuilder.toString().trim { it <= ' ' }
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }
}