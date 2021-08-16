package ru.sequence.list.ui.viewholder

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import ru.sequence.film.domain.entity.FilmEntity
import ru.sequence.list.databinding.FilmCoverItemBinding
import ru.sequence.list.presenter.FilmPresenter

class FilmCoverViewHolder private constructor(
	private val binding: FilmCoverItemBinding,
	private val context: Context
) : RecyclerView.ViewHolder(binding.root) {

	companion object {

		fun create(parent: ViewGroup, context: Context): FilmCoverViewHolder {
			val inflater = LayoutInflater.from(parent.context)
			val binding = FilmCoverItemBinding.inflate(inflater, parent, false)
			return FilmCoverViewHolder(binding, context)
		}
	}

	fun bind(film: FilmEntity, presenter: FilmPresenter) {

		binding.filmCover.setOnClickListener {
			presenter.onItemClick(film)
		}

		binding.filmName.text = film.localized_name
		Glide
			.with(context)
			.load(film.image_url)
			.addListener(object : RequestListener<Drawable> {
				override fun onLoadFailed(
					e: GlideException?,
					model: Any?,
					target: Target<Drawable>?,
					isFirstResource: Boolean
				): Boolean {
					binding.filmCoverNotFound.visibility = View.VISIBLE
					return false
				}

				override fun onResourceReady(
					resource: Drawable?,
					model: Any?,
					target: Target<Drawable>?,
					dataSource: DataSource?,
					isFirstResource: Boolean
				): Boolean {
					binding.filmCoverNotFound.visibility = View.GONE
					return false
				}
			})
			.into(binding.filmCover)
	}
}