package com.example.kakao.ui.ext

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("setText")
fun TextView.setText(inputText: String?) {
    inputText?.let { this.text = it }
}

@BindingAdapter("setPlayTime")
fun TextView.setPlayTime(playTime: Int?) {
    playTime?.let {
        val hours = it / 3600
        val minutes = (it % 3600) / 60
        val remainingSeconds = it % 60

        if (hours == 0) {
            this.text = String.format("%02d분 %02d초", minutes, remainingSeconds)
            return
        }
        if (minutes == 0) {
            this.text = String.format("%02d초", remainingSeconds)
            return
        }
        this.text = String.format("%02d시간 %02d분 %02d초", hours, minutes, remainingSeconds)
    }
}