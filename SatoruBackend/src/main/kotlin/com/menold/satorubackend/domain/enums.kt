package com.menold.satorubackend.domain


/**
 * Enums shared across entities. Kept in one file so entity classes
 * don't need to cross-reference each other's files for status values.
 */

enum class UserStatus { ACTIVE, SUSPENDED, DELETED }
enum class RoleType { CUSTOMER, ADMIN, SELLER, SUPPORT, SUPER_ADMIN }


enum class Platform { WEB, IOS, ANDROID }

enum class AddressType { SHIPPING, BILLING, BOTH }

enum class PaymentMethodType { M_PESA, CARD, WALLET, BANK_TRANSFER }

enum class ProductStatus { DRAFT, ACTIVE, ARCHIVED }

enum class ListingCondition { NEW, USED_LIKE_NEW, USED_GOOD, USED_ACCEPTABLE, REFURBISHED }

enum class SellerStatus { PENDING, APPROVED, SUSPENDED, REJECTED }
enum class CartStatus { ACTIVE, CONVERTED, ABANDONED }

enum class OrderStatus {
    PENDING, CONFIRMED, PROCESSING, PARTIALLY_SHIPPED, SHIPPED, DELIVERED, CANCELLED, REFUNDED
}

enum class OrderItemFulfillmentStatus {
    PENDING, ALLOCATED, SHIPPED, DELIVERED, CANCELLED, RETURNED
}

enum class ShipmentStatus { LABEL_CREATED, IN_TRANSIT, OUT_FOR_DELIVERY, DELIVERED, FAILED, RETURNED }

enum class PaymentStatus { AUTHORIZED, CAPTURED, FAILED, REFUNDED, PARTIALLY_REFUNDED }

enum class RefundStatus { REQUESTED, APPROVED, REJECTED, PROCESSED }

enum class ReturnStatus { REQUESTED, APPROVED, REJECTED, IN_TRANSIT, RECEIVED, REFUNDED }

enum class ReviewStatus { PENDING, PUBLISHED, REJECTED }

enum class PromotionType { PERCENTAGE, FIXED_AMOUNT, FREE_SHIPPING }

enum class TicketStatus { OPEN, IN_PROGRESS, WAITING_ON_CUSTOMER, RESOLVED, CLOSED }

enum class TicketPriority { LOW, MEDIUM, HIGH, URGENT }

enum class NotificationChannel { EMAIL, PUSH, SMS, IN_APP }

enum class NotificationStatus { PENDING, SENT, FAILED }

// ── Customer profile ─────────────────────────────────────────

/** Overall lifecycle stage of a customer account. */
enum class CustomerTier {
    STANDARD,   // default on registration
    SILVER,     // e.g. ≥ 5 orders or ≥ $500 lifetime spend
    GOLD,       // e.g. ≥ 20 orders or ≥ $2 000 lifetime spend
    PLATINUM    // e.g. invite-only / highest spend band
}

// ── Admin profile ────────────────────────────────────────────

/** Scope of platform access granted to an admin. */
enum class AdminAccessLevel {
    READ_ONLY,      // can view everything, change nothing
    OPERATOR,       // day-to-day ops: orders, refunds, basic user management
    MANAGER,        // + promotions, seller approvals, content
    SUPER_ADMIN     // full access including other admin accounts
}

// ── Support agent profile ────────────────────────────────────

/** The team/queue this agent belongs to. */
enum class AgentDepartment {
    GENERAL,
    ORDERS_AND_RETURNS,
    PAYMENTS,
    SELLER_SUPPORT,
    FRAUD_AND_TRUST,
    TECHNICAL
}

/** Current availability state of an agent. */
enum class AgentStatus {
    ONLINE,     // accepting new tickets
    BUSY,       // at ticket capacity
    AWAY,       // temporarily unavailable
    OFFLINE
}