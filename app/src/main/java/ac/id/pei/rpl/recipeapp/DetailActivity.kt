package ac.id.pei.rpl.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    lateinit var etId: TextView
    lateinit var etNamaHidangan: TextView
    lateinit var etJenisHidangan: TextView
    lateinit var etDurasi: TextView
    lateinit var etBahan: TextView
    lateinit var etLangkah: TextView
    lateinit var etNamaPembuat: TextView
    lateinit var valNamaHidangan: String
    lateinit var valJenisHidangan: String
    lateinit var valDurasi: String
    lateinit var valBahan: String
    lateinit var valLangkah: String
    lateinit var valNamaPembuat: String
    lateinit var valId: String
    lateinit var update: Button
    lateinit var apiService: ServiceInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        getMyData()
        declaration()
        etId.setText(valId)
        etNamaHidangan.setText(valNamaHidangan)
        etJenisHidangan.setText(valJenisHidangan)
        etDurasi.setText(valDurasi)
        etBahan.setText(valBahan)
        etLangkah.setText(valLangkah)
        etNamaPembuat.setText(valNamaPembuat)

//        var idx = valId
//        var namaHidanganx = valNamaHidangan
//        var jenisHidanganx = valJenisHidangan
//        var durasix = valDurasi
//        var bahanx = valBahan
//        var langkahx = valLangkah
//        var namaPembuatx = valNamaPembuat
        update.setOnClickListener {
            val bundle= Bundle()
            val pindah = Intent(this, UpdateActivity::class.java)
            bundle.putString("id", valId.toString())
            bundle.putString("nama_hidangan", valNamaHidangan.toString())
            bundle.putString("jenis_hidangan", valJenisHidangan.toString())
            bundle.putString("durasi", valDurasi.toString())
            bundle.putString("bahan", valBahan.toString())
            bundle.putString("langkah", valLangkah.toString())
            bundle.putString("nama_pembuat", valNamaPembuat.toString())
            pindah.putExtras(bundle)
            startActivity(pindah)
        }

    }
    fun declaration(){
        etId = findViewById(R.id.dt_id)
        etNamaHidangan = findViewById(R.id.dt_nama_hidangan)
        etJenisHidangan = findViewById(R.id.dt_jenis_hidangan)
        etDurasi = findViewById(R.id.dt_durasi)
        etBahan = findViewById(R.id.dt_bahan)
        etLangkah = findViewById(R.id.dt_langkah)
        etNamaPembuat = findViewById(R.id.dt_nama_pembuat)
        update = findViewById(R.id.btn_main_edit)
        apiService = Repository.getDataAPI().create(ServiceInterface::class.java)
    }
    fun getMyData(){
        val myValue = intent.extras
        if (myValue!=null){
            valNamaHidangan = myValue.getString("nama_hidangan").toString()
            valJenisHidangan = myValue.getString("jenis_hidangan").toString()
            valDurasi = myValue.getString("durasi").toString()
            valBahan = myValue.getString("bahan").toString()
            valLangkah = myValue.getString("langkah").toString()
            valNamaPembuat = myValue.getString("nama_pembuat").toString()
            valId = myValue.getString("id").toString()
        }


    }
}