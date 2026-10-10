package com.menold.satorubackend.infrastructure.adapters.out.persistence.entities.access

import com.menold.satorubackend.domain.AddressType
import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.UUID


@Entity
@Table(name = "addresses")
class AddressEntity(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: UserEntity,

    @Column(nullable = false)
    var country: String,

    var label: String? = null, // "Home", "Work"

    @Enumerated(EnumType.STRING)
    var type: AddressType = AddressType.SHIPPING,

    @Column(name = "street_line1", nullable = false)
    var streetLine1: String,

    @Column(name = "street_line2", nullable = true)
    var streetLine2: String? = null,


    @Column(name = "recipient_name", nullable = false)
    var recipientName: String,

    @Column(nullable = false)
    var city: String,

    var state: String? = null,

    @Column(name = "postal_code")
    var postalCode: String? = null,

    var phone: String? = null,

    @Column(name = "is_default_shipping")
    var isDefaultShipping: Boolean = false,

    @Column(name = "is_default_billing")
    var isDefaultBilling: Boolean = false,

    @Column(name = "geo_lat")
    var geoLat: Double? = null,

    @Column(name = "geo_lng")
    var geoLng: Double? = null

){
    @Id
    @GeneratedValue
    @Column(name = "address_id")
    var id: UUID = UUID.randomUUID()

    @ElementCollection
    @CollectionTable(
        name = "role_permissions",
        joinColumns = [JoinColumn(name = "role_id")]
    )
    @Column(name = "permission")
    var permissions: MutableSet<String> = mutableSetOf()

}