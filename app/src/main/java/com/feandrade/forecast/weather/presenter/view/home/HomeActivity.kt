package com.feandrade.forecast.weather.presenter.view.home


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.feandrade.forecast.weather.R
import com.google.android.gms.maps.SupportMapFragment


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        transactionToHomeFragment()
    }

    private fun transactionToHomeFragment(){
        supportFragmentManager.beginTransaction()
            .add(R.id.container, HomeFragment())
            .commit()
    }
}