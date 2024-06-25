package com.virgen.peregrina.demo.service

import com.virgen.peregrina.demo.data.entity.Contact
import com.virgen.peregrina.demo.data.model.ContactDTO

interface ContactService {
    fun getAllContacts(): List<ContactDTO>
    fun getContactById(id: Long): ContactDTO?
    fun createContact(contactDTO: ContactDTO): ContactDTO
    fun updateContact(id: Long, contactDTO: ContactDTO): ContactDTO?
    fun deleteContact(id: Long)
}
