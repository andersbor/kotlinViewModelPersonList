package dk.easj.anbo.viewmodelpersonlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dk.easj.anbo.viewmodelpersonlist.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val viewModel: PersonsViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.persons.observe(viewLifecycleOwner, {
            binding.personsTextView.text = it.toString()
        })

        binding.addButton.setOnClickListener {
            val name = binding.nameEditText.text.trim().toString()
            if (name.isEmpty()) {
                binding.nameEditText.error = "No name"
                return@setOnClickListener
            }
            val ageStr = binding.ageEditText.text.trim().toString()
            if (ageStr.isEmpty()) {
                binding.ageEditText.error = "No age"
                return@setOnClickListener
            }
            val p = Person(name, ageStr.toInt())
            viewModel.add(p)
        }

        binding.showSimpleButton.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.showListButton.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_personListFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}