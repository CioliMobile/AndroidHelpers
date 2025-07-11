plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.kotlin.android)
}

android {
	namespace = "com.cioli.helpers"
	compileSdk = 35

	defaultConfig {
		minSdk = 24

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		consumerProguardFiles("consumer-rules.pro")
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}
	kotlinOptions {
		jvmTarget = "11"
	}
}

dependencies {

	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.material)

	implementation(libs.androidx.navigation.runtime.android)
	implementation(libs.kotlinx.serialization.json)
	implementation(libs.androidx.runtime.android)
	implementation(libs.androidx.lifecycle.runtime.compose.android)

	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
}