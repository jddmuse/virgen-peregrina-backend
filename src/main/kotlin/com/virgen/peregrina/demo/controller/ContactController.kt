package com.virgen.peregrina.demo.controller
import com.virgen.peregrina.demo.data.model.ContactDTO
import com.virgen.peregrina.demo.service.ContactService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://127.0.0.1:5500"])  // Permite solicitudes desde http://127.0.0.1:5500
@RestController
@RequestMapping("/contacts")
class ContactController @Autowired constructor(
    private val contactService: ContactService
) {

    @GetMapping
    fun getAllContacts(): ResponseEntity<List<ContactDTO>> =
        ResponseEntity.ok(contactService.getAllContacts())

    @GetMapping("/{id}")
    fun getContactById(@PathVariable id: Long): ResponseEntity<ContactDTO> {
        val contact = contactService.getContactById(id)
        return if (contact != null) {
            ResponseEntity.ok(contact)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createContact(@RequestBody contactDTO: ContactDTO): ResponseEntity<ContactDTO> =
        ResponseEntity.status(HttpStatus.CREATED).body(contactService.createContact(contactDTO))

    @PutMapping("/{id}")
    fun updateContact(@PathVariable id: Long, @RequestBody contactDTO: ContactDTO): ResponseEntity<ContactDTO> {
        val updatedContact = contactService.updateContact(id, contactDTO)
        return if (updatedContact != null) {
            ResponseEntity.ok(updatedContact)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteContact(@PathVariable id: Long): ResponseEntity<Void> {
        contactService.deleteContact(id)
        return ResponseEntity.noContent().build()
    }
}
