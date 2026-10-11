package it.bsoft.bauto.poc

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView

/** Phone-only information; Android Auto renders its own template-based surface. */
class PhoneInfoActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(TextView(this).apply {
            text = getString(R.string.phone_intro)
            textSize = 18f
            setPadding(32, 40, 32, 32)
            gravity = Gravity.CENTER_VERTICAL
        })
    }
}
