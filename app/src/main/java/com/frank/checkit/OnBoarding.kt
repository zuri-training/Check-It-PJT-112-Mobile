package com.frank.checkit

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.frank.checkit.adapters.OnBoardingItemAdapter
import com.frank.checkit.model.OnBoardingItem
import com.google.android.material.button.MaterialButton

class OnBoarding : AppCompatActivity() {
    lateinit var onBoardingItemAdapter: OnBoardingItemAdapter
    lateinit var indicatorContainers:LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)
        setUpAdapter()
        setUpIndicator()
    }

    private fun setUpAdapter(){
        onBoardingItemAdapter = OnBoardingItemAdapter(
                listOf(
                        OnBoardingItem(
                                R.drawable.search_for_images,
                                "Search Product",
                                "Choose or Search Product"
                        ),
                        OnBoardingItem(
                                R.drawable.select_products,
                                "Compare Price",
                                "Compare price from different stores"
                        ),
                        OnBoardingItem(
                                R.drawable.s,
                                "Search price",
                                "Choose or Search Product"
                        )

                )
        )

        val onBoardingViewPager = findViewById<ViewPager2>(R.id.onboardingViewPager)
        onBoardingViewPager.adapter = onBoardingItemAdapter

        findViewById<ImageView>(R.id.imageNext).setOnClickListener {
            if (onBoardingViewPager.currentItem + 1 < onBoardingItemAdapter.itemCount){
                onBoardingViewPager.currentItem += 1
            }else{
                navigateToMainActivity()
            }
        }
        findViewById<TextView>(R.id.textSkip).setOnClickListener { navigateToMainActivity() }
        findViewById<MaterialButton>(R.id.getStarted).setOnClickListener { navigateToMainActivity() }
    }

    private fun navigateToMainActivity(){
        startActivity(Intent(applicationContext,MainActivity::class.java))
        finish()
    }

    private fun setUpIndicator(){
        indicatorContainers = findViewById(R.id.indicatorContainer)
        val indicators = arrayOfNulls<ImageView>(onBoardingItemAdapter.itemCount)
        val layoutParams :LinearLayout.LayoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)

        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i].let {
                it?.setImageDrawable(
                        ContextCompat.getDrawable(
                                applicationContext,
                                R.drawable.indicator_inactive_background
                        )
                )
                it?.layoutParams = layoutParams
                indicatorContainers.addView(it)
            }
        }
    }
}