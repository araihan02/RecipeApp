package ac.id.pei.rpl.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddActivity : AppCompatActivity() {
    lateinit var btnSubmit: Button
    lateinit var btnCancel: Button
    lateinit var etId: EditText
    lateinit var etNamaHidangan: EditText
    lateinit var etJenisHidangan: EditText
    lateinit var etDurasi: EditText
    lateinit var etBahan: EditText
    lateinit var etLangkah: EditText
    lateinit var etNamaPembuat: EditText
    lateinit var apiService: ServiceInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        declaration()
        myfunction()
    }

    fun declaration(){
        btnSubmit = findViewById(R.id.btn_add_submit)
        btnCancel = findViewById(R.id.btn_add_cancel)
        etId = findViewById(R.id.et_add_id)
        etNamaHidangan = findViewById(R.id.et_add_nama_hidangan)
        etJenisHidangan = findViewById(R.id.et_add_jenis_hidangan)
        etDurasi = findViewById(R.id.et_add_durasi)
        etBahan = findViewById(R.id.et_add_bahan)
        etLangkah = findViewById(R.id.et_add_langkah)
        etNamaPembuat = findViewById(R.id.et_add_nama_pembuat)
        apiService = Repository.getDataAPI().create(ServiceInterface::class.java)
    }

    fun myfunction(){
        val pindah = Intent(this, MainActivity::class.java)
        btnSubmit.setOnClickListener {
            val array = ResepData()
            array.id=etId.text.toString().toInt()
            array.nama_hidangan= etNamaHidangan.text.toString()
            array.jenis_hidangan = etJenisHidangan.text.toString()
            array.durasi = etDurasi.text.toString()
            array.bahan = etBahan.text.toString()
            array.langkah = etLangkah.text.toString()
            array.nama_pembuat = etNamaPembuat.text.toString()
            apiService.postResep(array).enqueue(object : Callback<ResepData>{
                override fun onResponse(call: Call<ResepData>, response: Response<ResepData>) {
                    startActivity(Intent(this@AddActivity, MainActivity::class.java))
                    Toast.makeText(baseContext, "Add Data Success", Toast.LENGTH_SHORT).show()
                }
                override fun onFailure(call: Call<ResepData>, t: Throwable) {
                    Toast.makeText(baseContext, "Add Data Failed", Toast.LENGTH_SHORT).show()
                }
            })
        }
        btnCancel.setOnClickListener {
            startActivity(pindah)
        }
    }
}
