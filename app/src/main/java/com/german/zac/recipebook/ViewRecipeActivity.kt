package com.german.zac.recipebook

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ViewRecipeActivity : AppCompatActivity() {

    private val hashv = intent.getLongExtra("hashvalue", -1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_recipe)

        val r: RecipeModel? = RecipeDbHelper.gethelp(this).readRecipe(hashv ,this)

        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        tvTitle.text = r!!.title
        val tvBody = findViewById<TextView>(R.id.tvBody)
        tvBody.text = r.body


    }
}
