package dependencies

object Dependencies {
    val kotlin_standard_library = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val kotlin_reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"

    val navigation_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.nav_components}"
    val navigation_runtime = "androidx.navigation:navigation-runtime:${Versions.nav_components}"
    val navigation_ui = "androidx.navigation:navigation-ui-ktx:${Versions.nav_components}"
    val navigation_dynamic = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.nav_components}"
    val material_dialogs = "com.afollestad.material-dialogs:core:${Versions.material_dialogs}"
    val material_dialogs_input = "com.afollestad.material-dialogs:input:${Versions.material_dialogs}"
    val room_runtime = "androidx.room:room-runtime:${Versions.room}"
    val room_ktx = "androidx.room:room-ktx:${Versions.room}"
    val play_core = "com.google.android.play:core:${Versions.play_core}"
    val lifecycle_runtime = "androidx.lifecycle:lifecycle-runtime:${Versions.lifecycle_version}"
    val lifecycle_coroutines = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_version}"
    val room_compiler = "androidx.room:room-compiler:${Versions.room}"
    val room_rxjava = "androidx.room:room-rxjava2:${Versions.room}"
    val androidx_appcompat = "androidx.appcompat:appcompat:${Versions.androidx_core}"
    val androidx_constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}"
    val rxjava = "io.reactivex.rxjava2:rxjava:${Versions.rxjava}"
    val rxandroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxandroid}"
    val markdown_processor = "com.yydcdut:markdown-processor:${Versions.markdown_processor}"
    val youtubeplayer= "com.pierfrancescosoffritti.androidyoutubeplayer:core:${Versions.youtubeplayer}"
    val recycler_view  = "androidx.recyclerview:recyclerview:${Versions.androidx_core}"
    val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
    val rxrelay = "com.jakewharton.rxrelay3:rxrelay:${Versions.rxrelay}"
    val lifecycle_extentions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle_extentions}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit2_version}"
    val retrofit_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit2_version}"
    val retrofit_rxjava = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofir_rxjava}"
    val room_rxjava_persistence = "com.squareup.retrofit2:adapter-rxjava2:${Versions.room_rxjava}"
    val androidx_media = "androidx.media:media:${Versions.androidx_core}"
    val reactive_streams = "androidx.lifecycle:lifecycle-reactivestreams:${Versions.reactive_streams}"
    val androidx_core = "androidx.core:core:${Versions.androidx_core}"
    val android_material = "com.google.android.material:material:${Versions.android_material}"
}