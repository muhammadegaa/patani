package com.fluid.smartcrops

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RelativeLayout
import android.widget.Spinner
import androidx.core.content.ContentProviderCompat.requireContext

class LandList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_land_list)

        val crop_dropdown = findViewById<Spinner>(R.id.sp_crops)
        val layout_crop = findViewById<RelativeLayout>(R.id.rl_loc)

        layout_crop.setOnClickListener {
            startActivity(Intent(this, SoilTreatment::class.java))
        }

        // Spinner Origin
        crop_dropdown.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.crops_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        // Spinner Item Listener
        crop_dropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            }
        }
    }
}