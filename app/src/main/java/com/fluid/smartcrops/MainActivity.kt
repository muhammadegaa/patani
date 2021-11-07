package com.fluid.smartcrops

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.google.android.material.snackbar.Snackbar
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var ApiService: APIService
    private lateinit var params : List<Double>
    private lateinit var data : List<List<Double>>
    private lateinit var input: InputParams
    private var nitrogen : Double = 0.0
    private var phosphorus: Double = 0.0
    private var potassium: Double = 0.0
    private var temperature: Double = 0.0
    private var humidity: Double = 0.0
    private var ph: Double = 0.0
    private var rainfall: Double = 0.0
    private var predictionRes = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ApiService = RetrofitInstance
            .getRetrofitInstance()
            .create(APIService::class.java)

        val tv_recommendation : TextView = findViewById(R.id.tv_crops_rec)
        val edt_nitrogen : EditText = findViewById(R.id.edt_nitrogen)
        val edt_phosphorus : EditText = findViewById(R.id.edt_phosphorus)
        val edt_potassium : EditText = findViewById(R.id.edt_potassium)
        val edt_temperature : EditText = findViewById(R.id.edt_temp)
        val edt_humidity : EditText = findViewById(R.id.edt_humid)
        val edt_ph : EditText = findViewById(R.id.edt_ph)
        val edt_rainfall : EditText = findViewById(R.id.edt_rainfall)
        val btn_rec : Button = findViewById(R.id.btn_recommend)
        val progress_circular : ProgressBar = findViewById(R.id.progress_circular)
        val btn_dashboard : ImageView = findViewById(R.id.btn_dashboard)

        edt_nitrogen.setText("150")
        edt_phosphorus.setText("40")
        edt_potassium.setText("200")
        edt_temperature.setText("29")
        edt_humidity.setText("100")
        edt_ph.setText("120")
        edt_rainfall.setText("220")

        btn_dashboard.setOnClickListener {
            Log.d("DASHBOARD", "TRUE")
            startActivity(Intent(this, Dashboard::class.java))
        }

        btn_rec.setOnClickListener{
            if(edt_nitrogen.text.toString().trim().isEmpty() || edt_phosphorus.text.toString().trim().isEmpty() ||
                edt_potassium.text.toString().trim().isEmpty() || edt_temperature.text.toString().trim().isEmpty() ||
                edt_humidity.text.toString().trim().isEmpty() || edt_ph.text.toString().trim().isEmpty() ||
                edt_rainfall.text.toString().trim().isEmpty()){


                val parentLayout = findViewById<View>(android.R.id.content)
                Snackbar.make(parentLayout, "Input field cannot be empty", Snackbar.LENGTH_LONG)
                    .setAction("CLOSE") { }
                    .setActionTextColor(resources.getColor(R.color.blue))
                    .show()

            } else {
                tv_recommendation.visibility = View.GONE
                progress_circular.visibility = View.VISIBLE
                nitrogen = edt_nitrogen.text.toString().toDouble()
                phosphorus = edt_phosphorus.text.toString().toDouble()
                potassium = edt_potassium.text.toString().toDouble()
                temperature = edt_temperature.text.toString().toDouble()
                humidity = edt_humidity.text.toString().toDouble()
                ph = edt_ph.text.toString().toDouble()
                rainfall = edt_rainfall.text.toString().toDouble()

                params =
                    listOf(nitrogen, phosphorus, potassium, temperature, humidity, ph, rainfall)
                data = listOf(params)

                input = InputParams(data)

                predictCrops(input)

                Handler(Looper.getMainLooper()).postDelayed({
                    progress_circular.visibility = View.GONE
                    tv_recommendation.text = predictionRes.capitalize()
                    tv_recommendation.visibility = View.VISIBLE
                }, 2000)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return networkCapabilities != null &&
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SimpleDateFormat")
    private fun predictCrops(input: InputParams) {
        val getData: LiveData<Response<OutputParam>> = liveData {
            val response = ApiService.predictCrops(
                input
            )
            emit(response)
        }
        getData.observe(this, androidx.lifecycle.Observer {
            if (isNetworkConnected()) {
                if (it.code() == 200){
                    predictionRes = it.body()!![0]
                } else {
                    val parentLayout = findViewById<View>(android.R.id.content)
                    Snackbar.make(parentLayout, it.errorBody()!!.string(), Snackbar.LENGTH_LONG)
                        .setAction("CLOSE") { }
                        .setActionTextColor(resources.getColor(R.color.blue))
                        .show()
                }
            } else {
                val parentLayout = findViewById<View>(android.R.id.content)
                Snackbar.make(parentLayout, "No internet connection.", Snackbar.LENGTH_LONG)
                    .setActionTextColor(resources.getColor(R.color.blue))
                    .setAction("REFRESH") {
                        finish()
                        startActivity(Intent(this, MainActivity::class.java))
                    }
                    .show()
            }
        })
    }
}