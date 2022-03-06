package com.example.erudite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment

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