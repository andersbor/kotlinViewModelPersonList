package dk.easj.anbo.viewmodelpersonlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PersonsViewModel : ViewModel() {
    private var _personList = mutableListOf<Person>()
    private var mutableLiveData: MutableLiveData<List<Person>> = MutableLiveData()
    val persons: LiveData<List<Person>> = mutableLiveData

    fun add(person: Person) {
        _personList.add(person)
        mutableLiveData.value = _personList // notifies observers
    }

    operator fun get(position: Int): Person { // [] operator overloading
        return _personList[position]
    }

    fun remove(position: Int) {
        _personList.removeAt(position)
        mutableLiveData.value = _personList
    }
}