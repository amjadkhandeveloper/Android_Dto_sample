package demo.amjadkhan.dtosample

import android.app.Application

/**
 * Application class for the DTO Sample app.
 * 
 * This class can be used for:
 * - Initializing app-wide dependencies (dependency injection, analytics, etc.)
 * - Setting up app-level configurations
 * - Initializing third-party libraries
 * 
 * To use this class, add it to AndroidManifest.xml:
 * <application
 *     android:name=".QuoteApplication"
 *     ...>
 */
class QuoteApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        
        // Example: Initialize dependency injection
        // Example: Initialize analytics SDK
        // Example: Initialize crash reporting
        // Example: Set up app-wide logging
        
        // Future use cases:
        // - Initialize Room Database
        // - Set up WorkManager
        // - Configure app-wide settings
        // - Initialize Firebase
        // - Set up Timber for logging
    }
}