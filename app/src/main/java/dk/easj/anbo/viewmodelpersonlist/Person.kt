package dk.easj.anbo.viewmodelpersonlist

import java.io.Serializable

class Person(val name: String, val age: Int) /*: Serializable*/ {
    override fun toString(): String {
        return "$name $age"
    }
}