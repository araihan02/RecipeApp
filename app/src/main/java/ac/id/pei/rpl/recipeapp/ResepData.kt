package ac.id.pei.rpl.recipeapp

data class ResepData (
    var id:Int?=0,
    var nama_hidangan:String?="",
    var jenis_hidangan:String?="",
    var durasi:String?="",
    var bahan:String?="",
    var langkah:String?="",
    var nama_pembuat:String?=""
)
