package ru.sequence.info.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import ru.sequence.film.domain.entity.FilmEntity
import ru.sequence.info.R
import ru.sequence.info.databinding.FragmentFilmInfoBinding
import ru.sequence.libraries.core.navigation.argument.NavigationArgumentsKeys
import ru.sequence.libraries.core.presentation.fragment.BaseFragment

class FilmInfoFragment : BaseFragment<FragmentFilmInfoBinding>() {

	override fun getBinding(
		inflater: LayoutInflater,
		container: ViewGroup?
	): FragmentFilmInfoBinding {
		return FragmentFilmInfoBinding.inflate(inflater, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		initArguments()
		initListeners()
	}

	private fun initArguments() {

		val film: FilmEntity = requireArguments().getSerializable(NavigationArgumentsKeys.FILM) as FilmEntity

		binding.infoToolbar.title = film.localized_name
		binding.filmDescription.text = film.description
		binding.filmOriginalName.text = film.name
		binding.filmRating.append(film.rating.toString())
		binding.filmYear.append(film.year.toString())
		Glide
			.with(requireContext())
			.load(film.image_url)
			.addListener(object : RequestListener<Drawable> {

				override fun onLoadFailed(
					e: GlideException?,
					model: Any?,
					target: Target<Drawable>?,
					isFirstResource: Boolean
				): Boolean {
					binding.filmCover.background = ContextCompat.getDrawable(requireContext(), R.drawable.unknown_cover)
					return false
				}

				override fun onResourceReady(
					resource: Drawable?,
					model: Any?,
					target: Target<Drawable>?,
					dataSource: DataSource?,
					isFirstResource: Boolean
				): Boolean {
					return false
				}
			})
			.into(binding.filmCover)
	}

	private fun initListeners() {
		binding.infoToolbar.setNavigationOnClickListener { findNavController().popBackStack() }
	}
}