package com.example.navigation

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import java.util.Locale



fun updateLanguage(context: Context, languageCode: String) {
//    val locale = Locale(languageCode)
//    Locale.setDefault(locale)
//    val resources = context.resources
//    val configuration = resources.configuration
//
//    configuration.setLocale(locale)
//    context.createConfigurationContext(configuration)
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)

        context.resources.updateConfiguration(config, context.resources.displayMetrics)


}
