package com.demo.core.extensions

import com.demo.core.ProgressDialog

fun ProgressDialog.showProgressDialog(){
    this.show()
}


fun ProgressDialog.hideProgressDialog(){
    this.dismiss()
}

