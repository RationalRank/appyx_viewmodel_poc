package dev.ranjith.appyx.viewmodel.poc.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumble.appyx.core.integrationpoint.ActivityIntegrationPoint

class CustomActivityIntegrationPoint(
    val activity: AppCompatActivity,
    savedInstanceState: Bundle?
) : ActivityIntegrationPoint(activity, savedInstanceState)
