package com.example.newapp.data.models

import kotlinx.serialization.Serializable


@Serializable
data class Character(val _id : String? , val name : String? , val imageUrl : String?)