package ac.id.pei.rpl.recipeapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateAdapter(var listku: ArrayList<ResepData>): RecyclerView.Adapter<UpdateAdapter.ResepViewHolder>(){
    inner class ResepViewHolder(viewku: View): RecyclerView.ViewHolder(viewku) {
        var tvId: TextView = viewku.findViewById(R.id.tv_id)
        var tvNamaHidangan: TextView = viewku.findViewById(R.id.tv_nama_hidangan)
        var tvDurasi: TextView = viewku.findViewById(R.id.tv_durasi)
        var tvNamaPembuat: TextView = viewku.findViewById(R.id.tv_nama_pembuat)
        var lDetail: LinearLayout = viewku.findViewById(R.id.lDetail)
//        var btnDelte: ImageButton = viewku.findViewById(R.id.btn_data_del)
        var btnUpdate: Button = viewku.findViewById(R.id.btn_main_edit)
        var apiIterface: ServiceInterface? = Repository.getDataAPI().create(ServiceInterface::class.java)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResepViewHolder {
        val viewView: View = LayoutInflater.from(parent.context).inflate(R.layout.activity_detail, parent, false)
        return ResepViewHolder(viewView)
    }

    override fun onBindViewHolder(holder: ResepViewHolder, position: Int) {
        val dataku = listku[position]
        holder.tvId.text = dataku.id.toString()
        holder.tvNamaHidangan.text = dataku.nama_hidangan
        holder.tvDurasi.text = dataku.durasi
        holder.tvNamaPembuat.text = dataku.nama_pembuat
        holder.lDetail.setOnClickListener {
            val bundle= Bundle()
            val pindah = Intent(holder.itemView.context, DetailActivity::class.java)
            bundle.putString("id", dataku.id.toString())
            bundle.putString("nama_hidangan", dataku.nama_hidangan.toString())
            bundle.putString("jenis_hidangan", dataku.jenis_hidangan.toString())
            bundle.putString("durasi", dataku.durasi.toString())
            bundle.putString("bahan", dataku.bahan.toString())
            bundle.putString("langkah", dataku.langkah.toString())
            bundle.putString("nama_pembuat", dataku.nama_pembuat.toString())
            pindah.putExtras(bundle)
            holder.itemView.context.startActivity(pindah)
        }
        holder.btnUpdate.setOnClickListener {
            val bundle= Bundle()
            val pindah = Intent(holder.itemView.context, UpdateActivity::class.java)
            bundle.putString("id", dataku.id.toString())
            bundle.putString("nama_hidangan", dataku.nama_hidangan.toString())
            bundle.putString("jenis_hidangan", dataku.jenis_hidangan.toString())
            bundle.putString("durasi", dataku.durasi.toString())
            bundle.putString("bahan", dataku.bahan.toString())
            bundle.putString("langkah", dataku.langkah.toString())
            bundle.putString("nama_pembuat", dataku.nama_pembuat.toString())
            pindah.putExtras(bundle)
            holder.itemView.context.startActivity(pindah)
        }
//        holder.btnDelte.setOnClickListener {
//            holder.apiIterface!!.deleteResep(dataku.id!!).enqueue(object : Callback<ResepData>{
//                override fun onResponse(call: Call<ResepData>, response: Response<ResepData>) {
//                    if (response.isSuccessful){
//                        listku.removeAt(position)
//                        notifyDataSetChanged()
//                        Toast.makeText(holder.itemView.context, "Delete Data Success", Toast.LENGTH_SHORT).show()
//                    }
//                }
//                override fun onFailure(call: Call<ResepData>, t: Throwable) {
//                    Toast.makeText(holder.itemView.context, "Delete Data Failed", Toast.LENGTH_SHORT).show()
//                }
//
//            })
//        }
    }

    override fun getItemCount(): Int {
        return listku.size
    }

}
