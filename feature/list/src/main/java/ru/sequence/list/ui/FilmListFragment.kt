package ru.sequence.list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import ru.sequence.film.domain.entity.FilmEntity
import ru.sequence.film.domain.entity.FilmGenre
import ru.sequence.libraries.core.navigation.argument.NavigationArgumentsKeys
import ru.sequence.libraries.core.presentation.fragment.BaseFragment
import ru.sequence.list.R
import ru.sequence.list.databinding.FragmentFilmListBinding
import ru.sequence.list.domain.entity.FilmUiModel
import ru.sequence.list.presenter.FilmPresenter
import ru.sequence.list.ui.adapter.FilmListAdapter
import ru.sequence.list.ui.adapter.FilmListAdapter.Companion.manager

class FilmListFragment : BaseFragment<FragmentFilmListBinding>(), FilmListView {

	private val presenter: FilmPresenter by inject { parametersOf(this) }

	private lateinit var adapter: FilmListAdapter

	override fun getBinding(
		inflater: LayoutInflater,
		container: ViewGroup?
	): FragmentFilmListBinding {
		return FragmentFilmListBinding.inflate(inflater, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		initState(savedInstanceState)
		initAdapter()
		initListeners()
	}

	override fun onViewStateRestored(savedInstanceState: Bundle?) {
		super.onViewStateRestored(savedInstanceState)

		presenter.onRestoredContent()
	}

	override fun onSaveInstanceState(outState: Bundle) {
		super.onSaveInstanceState(outState)

		presenter.onSaveState(outState)
	}

	private fun initState(savedInstanceState: Bundle?) {

		savedInstanceState?.let {
			val savedGenre = savedInstanceState.getSerializable("genre") as FilmGenre?
			if (savedGenre != null) {
				presenter.onNewGenre(savedGenre)
			}
		}
	}

	private fun initListeners() {
		binding.repeatLoad.setOnClickListener { presenter.onRepeatLoad() }
	}

	private fun initAdapter() {
		adapter = FilmListAdapter(requireContext(), presenter)
		binding.filmListAdapter.layoutManager = adapter.manager
		binding.filmListAdapter.adapter = adapter
	}

	override fun onDataReceived(cover: List<FilmUiModel>) {
		binding.filmProgressBar.visibility = View.GONE
		adapter.submitList(cover)
	}

	override fun onConnectionBroken() {
		binding.filmProgressBar.visibility = View.GONE
		binding.connectionError.visibility = View.VISIBLE
	}

	override fun goToInfoFragment(film: FilmEntity) {
		findNavController().navigate(R.id.action_filmListFragment_to_filmInfoFragment, Bundle().apply {
			putSerializable(NavigationArgumentsKeys.FILM, film)
		})
	}

	override fun onStartedLoadData() {
		binding.filmProgressBar.visibility = View.VISIBLE
		binding.connectionError.visibility = View.GONE
	}
}