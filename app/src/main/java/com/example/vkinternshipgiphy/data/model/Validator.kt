package com.example.vkinternshipgiphy.data.model

import com.google.android.material.textfield.TextInputEditText

class Validator(
    private val inputText: String,
    private val viewInputText: TextInputEditText
) {

    fun validate(): Boolean {

        if (inputText.isEmpty()) {
            viewInputText.error = "Введите ключевое слово"
            return false
        }
        if (inputText.length > 20){
            viewInputText.error = "Слишком большое слово"
            return false
        }
        if (!checkByRegex(inputText, LATIN_REGEX)) {
            viewInputText.error = "Напишите на английском"
            return false
        }
        return true
    }

    private fun checkByRegex(str: String, regex: String): Boolean {
        return str.matches(Regex(regex))
    }
    companion object {
        const val LATIN_REGEX = "[A-Za-z]+"
    }
}