package com.virgen.peregrina.demo.data.converter
import com.virgen.peregrina.demo.data.entity.Contact
import com.virgen.peregrina.demo.data.model.ContactDTO
import org.springframework.stereotype.Component

@Component
class ContactConverter {

    fun entityToDTO(contact: Contact): ContactDTO {
        return ContactDTO(
            id = contact.id,
            direccion = contact.direccion,
            telefono1 = contact.telefono1,
            telefono2 = contact.telefono2,
            email = contact.email,
            diasHabiles = contact.diasHabiles,
            horarioHabil = contact.horarioHabil
        )
    }

    fun dtoToEntity(contactDTO: ContactDTO): Contact {
        return Contact(
            id = contactDTO.id,
            direccion = contactDTO.direccion,
            telefono1 = contactDTO.telefono1,
            telefono2 = contactDTO.telefono2,
            email = contactDTO.email,
            diasHabiles = contactDTO.diasHabiles,
            horarioHabil = contactDTO.horarioHabil
        )
    }
}
