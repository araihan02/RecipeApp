package ac.id.pei.rpl.recipeapp

import retrofit2.Call
import retrofit2.http.*

interface ServiceInterface {
    @GET("Resep")
    fun getData(): Call<List<ResepData>>

    @POST("Resep")
    fun postResep(@Body resepData: ResepData): Call<ResepData>

    @FormUrlEncoded
    @HTTP(method="PUT", path="Resep", hasBody = true)
    fun updateResep(
        @Field("id") id: Int,
        @Field("nama_hidangan") nama_hidangan: String,
        @Field("jenis_hidangan") jenis_hidangan: String,
        @Field("durasi") durasi: String,
        @Field("bahan") bahan: String,
        @Field("langkah") langkah: String,
        @Field("nama_pembuat") nama_pembuat: String): Call<ResepData>
//
//    @FormUrlEncoded
//    @HTTP(method = "DELETE", path = "Resep", hasBody = true)
//    fun deleteResep(@Field("id") id: Int): Call<ResepData>

}
