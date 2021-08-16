package ru.sequence.list.ui.viewholder

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.sequence.list.databinding.FilmTitleItemBinding

class FilmTitleViewHolder private constructor(
	private val context: Context,
	private val binding: FilmTitleItemBinding
) : RecyclerView.ViewHolder(binding.root) {

	companion object {

		fun create(parent: ViewGroup, context: Context): FilmTitleViewHolder {
			val inflater = LayoutInflater.from(parent.context)
			val binding = FilmTitleItemBinding.inflate(inflater, parent, false)
			return FilmTitleViewHolder(context, binding)
		}
	}

	fun bind(title: Int) {

		binding.filmTitle.text = context.getString(title)
	}
}