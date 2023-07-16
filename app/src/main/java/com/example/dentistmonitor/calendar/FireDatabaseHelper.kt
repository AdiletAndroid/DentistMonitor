package com.example.dentistmonitor.calendar

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FireDatabaseHelper {
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val rootRef: DatabaseReference = database.reference

    // Метод для добавления нового доктора в базу данных
    fun addDoctor(doctorId: String, name: String, calendarId: String) {
        val doctorRef = rootRef.child("doctors").child(doctorId)
        doctorRef.child("name").setValue(name)
        doctorRef.child("calendarId").setValue(calendarId)
    }

    // Метод для добавления нового календаря в базу данных
    fun addCalendar(calendarId: String, name: String) {
        val calendarRef = rootRef.child("calendars").child(calendarId)
        calendarRef.child("name").setValue(name)
    }

    // Метод для добавления новой записи пациента в календарь
    fun addAppointment(calendarId: String, appointmentId: String, patientId: String, time: String, additionalData: String) {
        val appointmentRef = rootRef.child("calendars").child(calendarId).child("appointments").child(appointmentId)
        appointmentRef.child("patientId").setValue(patientId)
        appointmentRef.child("time").setValue(time)
        appointmentRef.child("additionalData").setValue(additionalData)
    }
}