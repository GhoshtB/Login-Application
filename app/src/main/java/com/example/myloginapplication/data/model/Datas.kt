package com.example.myloginapplication.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


/*data class Login(var email:String,var password:String,var userName:String){
   @PrimaryKey(autoGenerate = true)
   var userId:Int=0
//   @Embedded var user:User? =null
}
@Entity(foreignKeys = arrayOf(ForeignKey(entity = Login::class,childColumns = arrayOf("userId"),parentColumns = arrayOf("userId"),
onDelete = ForeignKey.CASCADE)))*/
@Entity
data class User(var name:String,var phone:String,var email:String,var password:String,var userName:String){
   @PrimaryKey(autoGenerate = true) var userId: Int=0
}