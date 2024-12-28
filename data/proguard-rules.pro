# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-dontwarn java.lang.invoke.StringConcatFactory

-keepclassmembers class leegroup.module.data.models.** { *; }
-keepclassmembers class leegroup.module.data.remote.models.requests.** { *; }
-keepclassmembers class leegroup.module.data.remote.models.responses.** { *; }

# Keep repositories
-keep class leegroup.module.data.repositories.** { *; }

# Keep UserDao class
-keep class leegroup.module.data.local.room.** { *; }

# Keep Service (Retrofit) class
-keep class leegroup.module.data.remote.services.** { *; }

# Keep Room Database entities and DAOs
-keep class androidx.room.** { *; }
-dontwarn androidx.room.**

# Keep all generated Room DAO classes
-keep class * extends androidx.room.RoomDatabase { *; }
-keep class * extends androidx.room.Dao { *; }
