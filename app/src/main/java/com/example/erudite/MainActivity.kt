package com.example.erudite

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       val navHostFragment = supportFragmentManager.findFragmentById(R.id.openQuizFragmentContainerView) as NavHostFragment
       val navController = navHostFragment.navController
       navController.navigate(R.id.rulesFragment)
    //   Navigation.findNavController(this, R.id.rulesFragment)

    }
  //  override fun onSupportNavigateUp(): Boolean {
  //      val navController = findNavController(R.id.rulesFragment)
  //      return navController.navigateUp() || super.onSupportNavigateUp()
  // }


}