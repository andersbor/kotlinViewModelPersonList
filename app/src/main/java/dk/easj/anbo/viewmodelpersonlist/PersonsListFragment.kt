package dk.easj.anbo.viewmodelpersonlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dk.easj.anbo.viewmodelpersonlist.databinding.PersonsListFragmentBinding

class PersonsListFragment : Fragment() {

    private var _binding: PersonsListFragmentBinding? = null
    private val viewModel: PersonsViewModel by activityViewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PersonsListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.persons.observe(viewLifecycleOwner, { persons: List<Person> ->
            if (persons.isEmpty()) {
                binding.messageTextView.text = "No words"
            } else {
                binding.messageTextView.text = ""
                binding.recyclerView.layoutManager = LinearLayoutManager(activity)
                binding.recyclerView.adapter =
                    MyAdapter(persons) { position -> onListItemClick(position) }
            }
        })
    }

    private fun onListItemClick(position: Int) {
        Log.d("APPLE", viewModel[position].toString())
        Toast.makeText(activity, "You clicked " + viewModel[position], Toast.LENGTH_SHORT).show()
        viewModel.remove(position)
    }


}