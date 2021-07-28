package ac.id.pei.rpl.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateActivity : AppCompatActivity() {
    lateinit var btnSubmit:Button
    lateinit var btnCancel:Button
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
    lateinit var apiService: ServiceInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        getMyData()
        declaration()
        myfunction()
    }
    fun declaration(){
        btnSubmit = findViewById(R.id.btn_edit_submit)
        btnCancel = findViewById(R.id.btn_edit_cancel)
        etNamaHidangan = findViewById(R.id.et_edit_nama_hidangan)
        etJenisHidangan = findViewById(R.id.et_edit_jenis_hidangan)
        etDurasi = findViewById(R.id.et_edit_durasi)
        etBahan = findViewById(R.id.et_edit_bahan)
        etLangkah = findViewById(R.id.et_edit_langkah)
        etNamaPembuat = findViewById(R.id.et_edit_nama_pembuat)
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
    fun myfunction(){
        etNamaHidangan.setText(valNamaHidangan)
        etJenisHidangan.setText(valJenisHidangan)
        etDurasi.setText(valDurasi)
        etBahan.setText(valBahan)
        etLangkah.setText(valLangkah)
        etNamaPembuat.setText(valNamaPembuat)
        val pindah = Intent(this, MainActivity::class.java)
        btnSubmit.setOnClickListener {
            apiService.updateResep(valId.toInt(), etNamaHidangan.text.toString(), etJenisHidangan.text.toString(), etDurasi.text.toString(), etBahan.text.toString(), etLangkah.text.toString(), etNamaPembuat.text.toString()).enqueue(object : Callback<ResepData>{
                override fun onResponse(call: Call<ResepData>, response: Response<ResepData>) {
                    startActivity(pindah)
                    Toast.makeText(baseContext, "Update Data Success", Toast.LENGTH_SHORT).show()
                }
                override fun onFailure(call: Call<ResepData>, t: Throwable) {
                    Toast.makeText(baseContext, "Update Data Failed", Toast.LENGTH_SHORT).show()
                }

            })
        }
        btnCancel.setOnClickListener {
            startActivity(pindah)
        }
    }
}