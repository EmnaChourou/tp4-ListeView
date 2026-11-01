package com.example.contactslistview


import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Liste modifiable contenant les contacts
    private val contacts = mutableListOf(
        "Ahmed Ben Ali",
        "Sarra Trabelsi",
        "Mohamed Gharbi",
        "Ines Jaziri",
        "Youssef Mansouri",
        "Amira Chaabane",
        "Karim Bouazizi",
        "Nour Hammami"
    )

    // Adaptateur reliant les données à la ListView
    private lateinit var adapter: ArrayAdapter<String>

    // Vues de l'interface
    private lateinit var etContact: EditText
    private lateinit var btnModifier: Button
    private lateinit var lvContacts: ListView

    // -1 signifie qu'aucun contact n'est sélectionné
    private var positionSelectionnee = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Récupération des vues depuis le layout
        etContact = findViewById(R.id.etContact)
        btnModifier = findViewById(R.id.btnModifier)
        lvContacts = findViewById(R.id.lvContacts)

        // Création et association de l'adaptateur
        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_activated_1,
            contacts
        )
        lvContacts.adapter = adapter

        // Clic sur un contact : copier son nom dans l'EditText
        lvContacts.setOnItemClickListener { _, _, position, _ ->
            positionSelectionnee = position
            lvContacts.setItemChecked(position, true)

            etContact.setText(contacts[position])
            etContact.setSelection(etContact.text.length)
            etContact.requestFocus()
            etContact.error = null
        }

        // Appui long sur un contact : confirmation de suppression
        lvContacts.setOnItemLongClickListener { _, _, position, _ ->
            val contactASupprimer = contacts[position]
            AlertDialog.Builder(this)
                .setTitle(R.string.title_supprimer)
                .setMessage(getString(R.string.msg_confirmer_suppression, contactASupprimer))
                .setPositiveButton(R.string.btn_supprimer) { _, _ ->
                    contacts.removeAt(position)
                    adapter.notifyDataSetChanged()

                    if (positionSelectionnee == position) {
                        positionSelectionnee = -1
                        etContact.text.clear()
                    } else if (positionSelectionnee > position) {
                        positionSelectionnee--
                    }

                    Toast.makeText(this, R.string.msg_contact_supprime, Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton(R.string.btn_annuler, null)
                .show()
            true
        }

        // Clic sur le bouton Modifier
        btnModifier.setOnClickListener {
            modifierContact()
        }

        // Le bouton Terminé du clavier modifie aussi le contact
        etContact.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                modifierContact()
                true
            } else {
                false
            }
        }
    }

    private fun modifierContact() {
        // Vérifier qu'un contact est sélectionné
        if (positionSelectionnee == -1) {
            Toast.makeText(
                this,
                R.string.msg_aucune_selection,
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        // Récupérer le nouveau nom et supprimer les espaces inutiles
        val nouveauNom = etContact.text.toString().trim()

        // Vérifier que le nom n'est pas vide
        if (nouveauNom.isEmpty()) {
            etContact.error = getString(R.string.msg_nom_vide)
            return
        }

        // Modifier les données
        contacts[positionSelectionnee] = nouveauNom

        // Prévenir l'adaptateur pour rafraîchir la ListView
        adapter.notifyDataSetChanged()

        // Conserver la ligne sélectionnée
        lvContacts.setItemChecked(positionSelectionnee, true)

        Toast.makeText(
            this,
            getString(R.string.msg_contact_modifie, nouveauNom),
            Toast.LENGTH_SHORT
        ).show()
    }
}