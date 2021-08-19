package dk.easj.anbo.viewmodelpersonlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PersonsViewModel : ViewModel() {
    private var _persons = mutableListOf<Person>()
    private var mutableLiveData: MutableLiveData<List<Person>> = MutableLiveData()
    val persons: LiveData<List<Person>> = mutableLiveData

    fun add(person: Person) {
        _persons.add(person)
        mutableLiveData.value = _persons // notifies observers
    }

    operator fun get(position: Int): Person { // [] operator overloading
        return _persons[position]
    }

    fun remove(position: Int) {
        _persons.removeAt(position)
        mutableLiveData.value = _persons
    }
}