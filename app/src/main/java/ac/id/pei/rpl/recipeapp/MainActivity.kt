package ac.id.pei.rpl.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
//    private var resepAdapter: ResepAdapter = ResepAdapter(arrList)
    lateinit var rvdata: RecyclerView
    lateinit var searchView: SearchView
    lateinit var apiService: ServiceInterface
    private var ambilData: ArrayList<ResepData> = arrayListOf()
    lateinit var btnadd: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvdata = findViewById(R.id.rv_data)
        searchView = findViewById(R.id.search_view)
        btnadd = findViewById(R.id.btn_main_add)
        btnadd.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
        apiService = Repository.getDataAPI().create(ServiceInterface::class.java)
        apiService.getData().enqueue(object : Callback<List<ResepData>>{
            override fun onResponse(
                call: retrofit2.Call<List<ResepData>>,
                response: Response<List<ResepData>>
            ) {
                if (response.isSuccessful){
                    val res = response.body()
                    ambilData.addAll(res!!)
                    rvdata.layoutManager = LinearLayoutManager(this@MainActivity)
                    rvdata.adapter = ResepAdapter(ambilData)
                }
            }

            override fun onFailure(call: retrofit2.Call<List<ResepData>>, t: Throwable) {
            }
        })

        searchView.setOnQueryTextListener( object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {

                var tempArr = ArrayList<ResepData>()

                for (arr in ambilData){
                    if (arr.nama_hidangan!!.toLowerCase(Locale.getDefault()).contains(p0.toString())){
                        tempArr.add(arr)
                    }
                }
                rvdata.layoutManager = LinearLayoutManager(this@MainActivity)
                rvdata.adapter = ResepAdapter(tempArr)
                return true
            }

        })
    }
}