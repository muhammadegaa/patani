package com.fluid.smartcrops

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton




class CropsPick : AppCompatActivity() {

    private var crops = false
    private lateinit var onionCB: CheckBox
    private lateinit var dunnoCB: CheckBox
    private lateinit var recBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crops_pick)

        onionCB = findViewById(R.id.cb_whonion)
        dunnoCB = findViewById(R.id.cb_dunno)
        recBtn = findViewById(R.id.btn_recommend)

        onionCB.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                crops = true
            } else {
                crops = false
            }
        }

        dunnoCB.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                crops = false
            } else {
                crops = true
            }
        }

        recBtn.setOnClickListener {
            if(crops == false){
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                startActivity(Intent(this, SoilTreatment::class.java))
            }
        }

    }
}