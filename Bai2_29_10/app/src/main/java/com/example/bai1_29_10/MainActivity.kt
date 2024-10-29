package com.example.bai1_29_10

import android.os.Bundle
import android.view.View;
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var input : EditText
    lateinit var listView : ListView
    lateinit var error : TextView
    lateinit var btnShow : Button
    lateinit var radioGroup : RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()



        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        input = findViewById(R.id.etInput)
        radioGroup = findViewById(R.id.radioGroup)
        error = findViewById(R.id.tvError)
        btnShow = findViewById(R.id.btnShow)
        listView = findViewById(R.id.listView)

        btnShow.setOnClickListener {
            error.visibility = View.GONE
            val input = input.text.toString()

            if (input.isEmpty()) {
                error.text = "Vui lòng nhập một số nguyên dương."
                error.visibility = View.VISIBLE
            } else {
                val n = input.toIntOrNull()
                if (n == null || n <= 0) {
                    error.text = "Số nhập không hợp lệ. Vui lòng nhập số nguyên dương."
                    error.visibility = View.VISIBLE
                } else {
                    val selectedRadioButtonId = radioGroup.checkedRadioButtonId
                    if (selectedRadioButtonId == -1) {
                        error.text = "Vui lòng chọn loại số."
                        error.visibility = View.VISIBLE
                    } else {
                        val resultList = mutableListOf<Int>()
                        when (selectedRadioButtonId) {
                            R.id.rbEven -> {
                                for (i in 0..n step 2) resultList.add(i)
                            }

                            R.id.rbOdd -> {
                                for (i in 1..n step 2) resultList.add(i)
                            }

                            R.id.rbPerfectSquare -> {
                                for (i in 0..n) {
                                    val square = i * i
                                    if (square > n) break
                                    resultList.add(square)
                                }
                            }
                        }

                        val adapter =
                            ArrayAdapter(this, android.R.layout.simple_list_item_1, resultList)
                        listView.visibility = View.VISIBLE
                        listView.adapter = adapter
                    }
                }
            }
        }

    }
}