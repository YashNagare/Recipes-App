package com.practice.recipesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.practice.recipesapp.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {

    private lateinit var rvAdapter: CategoryAdapter
    private lateinit var dataList: ArrayList<Recipe>
    private val binding by lazy {
        ActivityCategoryBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        title = intent.getStringExtra("TITTLE")
        setupRecyclerView()
        binding.goBackHome.setOnClickListener {
            finish()
        }

    }

    private fun setupRecyclerView() {

        dataList = ArrayList()
        binding.rvCategory.layoutManager =
            LinearLayoutManager(this)

        var db = Room.databaseBuilder(this@CategoryActivity, AppDatabase::class.java, "db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()

        var daoObject = db.getDao()
        var recipes = daoObject.getAll()

        for (i in recipes!!.indices) {
            if (recipes[i]!!.category.contains(intent.getStringExtra("CATEGORY")!!)) {
                dataList.add(recipes[i]!!)
            }
            rvAdapter = CategoryAdapter(dataList, this)
            binding.rvCategory.adapter = rvAdapter
        }
    }

}