package com.virgen.peregrina.demo.service.implement

import com.virgen.peregrina.demo.data.converter.ContactConverter
import com.virgen.peregrina.demo.data.model.ContactDTO
import com.virgen.peregrina.demo.repository.ContactRepository
import com.virgen.peregrina.demo.service.ContactService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ContactServiceImpl @Autowired constructor(
    private val contactRepository: ContactRepository,
    private val contactConverter: ContactConverter
) : ContactService {

    override fun getAllContacts(): List<ContactDTO> =
        contactRepository.findAll().map { contactConverter.entityToDTO(it) }

    override fun getContactById(id: Long): ContactDTO? {
        val contact = contactRepository.findById(id).orElse(null)
        return contact?.let { contactConverter.entityToDTO(it) }
    }

    override fun createContact(contactDTO: ContactDTO): ContactDTO {
        val contact = contactConverter.dtoToEntity(contactDTO)
        val savedContact = contactRepository.save(contact)
        return contactConverter.entityToDTO(savedContact)
    }

    override fun updateContact(id: Long, contactDTO: ContactDTO): ContactDTO? {
        val existingContact = contactRepository.findById(id)
        return if (existingContact.isPresent) {
            val updatedContact = existingContact.get().copy(
                direccion = contactDTO.direccion,
                telefono1 = contactDTO.telefono1,
                telefono2 = contactDTO.telefono2,
                email = contactDTO.email,
                diasHabiles = contactDTO.diasHabiles,
                horarioHabil = contactDTO.horarioHabil
            )
            contactRepository.save(updatedContact)
            contactConverter.entityToDTO(updatedContact)
        } else {
            null
        }
    }

    override fun deleteContact(id: Long) {
        contactRepository.deleteById(id)
    }
}