package com.gl4.myapplication

class Student (val firstname:String,val lastname:String, var present:Boolean = true){
    fun changePresence(){
        present=!present
    }

}