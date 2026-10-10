package com.menold.satorubackend.infrastructure.adapters.out.persistence.entities.access

import com.menold.satorubackend.domain.valueobjects.UserRoleId
import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "role")
class RoleEntity(
    @Column(nullable = false, unique = true)
    var name: String,
){
    @Id
    @GeneratedValue
    @Column(name = "role_id") //, updatable = false, nullable = false
    val id: UUID = UUID.randomUUID()

    @ElementCollection
    @CollectionTable(
        name = "role_permissions",
        joinColumns = [JoinColumn(name = "role_id")]
    )
    @Column(name = "permission")
    var permissions: MutableSet<String> = mutableSetOf()
}