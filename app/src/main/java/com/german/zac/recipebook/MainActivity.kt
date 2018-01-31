package com.german.zac.recipebook

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.AdapterView.OnItemClickListener



class MainActivity : AppCompatActivity() {

    companion object {
        private val map = HashMap<String, Long>()
        fun getmap(): HashMap<String, Long> {
            return this.map
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // disabled on purpose
        //setSupportActionBar(toolbar)

        floatingActionButton.setOnClickListener { //view ->
            val intent = Intent(this, NewRecipeActivity::class.java)
            intent.putExtra("hash", getmap())
            startActivity(intent)
        }

        val lv = this.findViewById<ListView>(R.id.lvRecipes)
        lv.adapter = ListAdapter(this)
        lv.onItemClickListener = this.clickedHandler
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.app_bar_search ->{
                val intent = Intent(this, SearchActivity::class.java)
                intent.putExtra("hash", map)
                startActivity(intent)
                return true
            }
            R.id.action_settings -> {
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private val clickedHandler = OnItemClickListener { parent, v, position, id ->
        val intent = Intent(parent.context, ViewRecipeActivity::class.java)
//  todo get better key
        val k = getmap()[v.toString()]
        intent.putExtra("hashvalue", k)
        startActivity(intent)
    }

    private class ListAdapter(context: Context): BaseAdapter(){
        internal var maplist = getmap().keys.toTypedArray()
//        internal var maplist = getmap().values.toTypedArray()

        private val mInflator: LayoutInflater = LayoutInflater.from(context)

        override fun getCount(): Int {
            return maplist.size
        }

        override fun getItem(position: Int): String {
            return maplist[position]
//            parameter context
//            val r = RecipeDbHelper.gethelp(context).readRecipe(maplist[position], context)
//            if (r != null) return r.title
//            return "error"
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
            val view: View?
            val vh: ListRowHolder
            if (convertView == null) {
                view = this.mInflator.inflate(R.layout.list_row, parent, false)
                vh = ListRowHolder(view)
                if (view != null) {
                    view.tag = vh
                }
            } else {
                view = convertView
                vh = view.tag as ListRowHolder
            }

            vh.label.text = getItem(position)
            return view
        }
    }

    private class ListRowHolder(row: View?) {
        val label: TextView = row?.findViewById(R.id.label) as TextView

    }


}
