package com.app.credit.application.entity

import jakarta.persistence.*

@Entity
data class Customer (
        @Column(nullable = false) var firstName:String=" ",
        @Column(nullable = false) var lastName:String=" ",
        @Column(nullable = false, unique = true) val cpf:String,
        @Column(nullable = false, unique = true) var email:String,
        @Column(nullable = false) var password:String,
        @Column(nullable = false) @Embedded var adress: Adress = Adress(),
        @Column(nullable = false) @OneToMany(fetch = FetchType.LAZY, cascade = arrayOf(CascadeType.REMOVE), mappedBy = "customer") var credis:List<Credit> = mutableListOf(),
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id:Long? =  null
)