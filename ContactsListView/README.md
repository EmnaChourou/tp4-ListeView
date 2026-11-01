# 📱 Application ContactsListView (Android Kotlin)

Une application Android simple en **Kotlin** pour gérer une liste de contacts interactive.

---

## 🎯 Fonctionnalités

* 📋 **Affichage** : Affiche une liste de contacts dans une `ListView`.
* 👆 **Sélection** : Cliquez sur un contact pour afficher son nom dans le champ de texte en haut.
* ✏️ **Modification** : Modifiez le nom dans le champ de texte puis cliquez sur le bouton **Modifier** (ou appuyez sur Entrée).
* 🗑️ **Suppression** : Maintenez un appui long sur un contact pour le supprimer après confirmation.
* ⚠️ **Vérifications** :
  * Alerte si vous cliquez sur **Modifier** sans sélectionner de contact.
  * Message d'erreur si le champ de texte est vide.

---

## 🛠️ Technologies utilisées

* **Langage** : Kotlin
* **Interface (UI)** : Layouts XML (`LinearLayout`, `EditText`, `Button`, `ListView`)
* **Composants** : `ArrayAdapter`, `AlertDialog`, `Toast`
* **Version Android minimale** : API 24 (Android 7.0)

---

## 🚀 Comment exécuter le projet

1. Ouvrez le projet dans **Android Studio**.
2. Choisissez un **émulateur** (ou branchez votre téléphone Android).
3. Cliquez sur le bouton vert **Run (▶)** (ou appuyez sur `Shift + F10`).
