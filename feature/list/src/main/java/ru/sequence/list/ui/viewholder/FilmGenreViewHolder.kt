package ru.sequence.list.ui.viewholder

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import ru.sequence.film.domain.entity.FilmGenre
import ru.sequence.list.databinding.FilmGenreItemBinding
import ru.sequence.list.presenter.FilmPresenter

class FilmGenreViewHolder private constructor(
	private val binding: FilmGenreItemBinding,
	private val context: Context
) : RecyclerView.ViewHolder(binding.root) {

	companion object {

		fun create(parent: ViewGroup, context: Context): FilmGenreViewHolder {
			val inflater = LayoutInflater.from(parent.context)
			val binding = FilmGenreItemBinding.inflate(inflater, parent, false)
			return FilmGenreViewHolder(binding, context)
		}
	}

	fun bind(genresGroup: Array<FilmGenre>, presenter: FilmPresenter) {

		if (binding.filmGenre.childCount == 0) {

			for (genre in genresGroup) {
				val chip = Chip(context).apply {
					isCheckable = true
					isCheckedIconVisible = false
					text = context.getString(genre.res)
				}

				if (presenter.getActiveGenre()?.res == genre.res) {
					chip.isChecked = true
				}

				chip.setOnCheckedChangeListener { _, isChecked ->
					presenter.onGenreSelected(isChecked, genre)
				}
				binding.filmGenre.addView(chip)
			}
		}
	}
}