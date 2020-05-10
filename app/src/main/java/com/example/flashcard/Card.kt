package com.example.flashcard

data class Card (
    val title: String,
    val content: String
){
    override fun toString(): String {
        return """{"title": "$title", "content" : "$content"}"""
    }
}