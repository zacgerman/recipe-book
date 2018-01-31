package com.german.zac.recipebook

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_new_recipe.*

class NewRecipeActivity : AppCompatActivity() {

    val map = intent.getSerializableExtra("hash")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_recipe)
        //setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_save_nr, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> addToDatabase(map as HashMap<String, Long>)
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToDatabase(map: HashMap<String, Long>): Boolean {
        var title = etRecipeTitle.text.toString()
        var r = RecipeModel(title,"Ungrouped",etRecipeBody.text.toString())

        //make length appropriate
        if (title.length > 32){
            title = title.substring(0,31)
        }

        //check for repeating key
        if (map.containsKey(title)){
            Toast.makeText(this, "Recipe not added. Please select an unused title.", Toast.LENGTH_LONG).show()
            return false
        }

        //add recipe to map and db
        map.put(title, RecipeDbHelper.gethelp(this).createRecipe(r))

        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("hash", map)
        startActivity(intent)

        return true
        // if name or hash code is repeated

    }
}
