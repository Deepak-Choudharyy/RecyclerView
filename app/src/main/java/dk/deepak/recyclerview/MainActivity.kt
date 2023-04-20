package dk.deepak.recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var myRecyclerView: RecyclerView
    lateinit var newsArrayList: ArrayList<News>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        myRecyclerView = findViewById(R.id.rcView)


        val newsImageArray = arrayOf(
            R.drawable.pic1,
            R.drawable.pic2,
            R.drawable.pic3,
            R.drawable.pic4,
            R.drawable.pic5,
            R.drawable.pic6,
            R.drawable.pic7
        )

        val newsHeadingArray = arrayOf(
            "U.K. Foreign Secretary James Cleverly raises issue of BBC tax searches with EAM Jaishankar",
            "Ukraine updates: 10 people killed in Russian shelling barrage",
            "Emerging with Resilience: Fostering a Smarter Future",
            "Stop Artificial Intelligence! 1000 experts call for a pause in AI training",
            "Why Are People Rushing To Get This Stylish New SmartWatch? The Health Benefits Are Incredible",
            "China rips new U.S. House committee on countering Beijing",
            "Arunachal CM Pema Khandu Shares Majestic Aerial View Of Bhupen Hazarika Setu"
        )

        val newsContent = arrayOf(
            getString(R.string.news_content), getString(R.string.news_war),
            getString(R.string.news_deo), getString(R.string.news_ai),
            getString(R.string.news_watch), getString(R.string.news_rahul),getString(R.string.news_Setu)
        )

        myRecyclerView.layoutManager = LinearLayoutManager(this)
        newsArrayList = arrayListOf<News>()

        for( index in newsImageArray.indices){
            val news = News(newsHeadingArray[index], newsImageArray[index],newsContent[index ])
            newsArrayList.add(news)
        }

//        myRecyclerView.adapter = MyAdapter(newsArrayList,this)
        var myAdapter = MyAdapter(newsArrayList, this)
        myRecyclerView.adapter = myAdapter

        myAdapter.setOnItemClickListener(object : MyAdapter.onItemClickListener {
            override fun onItemClicking(position: Int) {
                // on clicking each item , what action do you want to perform

                val intent = Intent(this@MainActivity, NewsDetails::class.java)
                intent.putExtra("heading", newsArrayList[position].newsHeading)
                intent.putExtra("imageId", newsArrayList[position].newsImage)
                intent.putExtra("newscontent", newsArrayList[position].newsContent)
                startActivity(intent)
            }

        })
    }
}