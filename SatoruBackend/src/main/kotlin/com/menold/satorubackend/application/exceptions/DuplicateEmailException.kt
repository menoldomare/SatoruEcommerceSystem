package com.menold.satorubackend.application.exceptions

class DuplicateEmailException(email: String) :
    RuntimeException("User with the email $email already exists")