package ru.sequence.list.ui.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.sequence.list.R
import ru.sequence.list.domain.entity.FilmUiModel
import ru.sequence.list.presenter.FilmPresenter
import ru.sequence.list.ui.viewholder.FilmCoverViewHolder
import ru.sequence.list.ui.viewholder.FilmGenreViewHolder
import ru.sequence.list.ui.viewholder.FilmTitleViewHolder

class FilmListAdapter(
	private val context: Context,
	private val presenter: FilmPresenter
) : ListAdapter<FilmUiModel, RecyclerView.ViewHolder>(COMPARATOR) {

	override fun getItemViewType(position: Int): Int =
		when (getItem(position)) {
			is FilmUiModel.Cover -> R.layout.film_cover_item
			is FilmUiModel.Title -> R.layout.film_title_item
			is FilmUiModel.Genre -> R.layout.film_genre_item
			else                 -> throw UnsupportedOperationException("Unknown view")
		}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		return when (viewType) {
			R.layout.film_cover_item -> FilmCoverViewHolder.create(parent, context)
			R.layout.film_title_item -> FilmTitleViewHolder.create(parent, context)
			R.layout.film_genre_item -> FilmGenreViewHolder.create(parent, context)
			else                     -> throw UnsupportedOperationException("Unknown view")
		}
	}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		when (val uiModel = getItem(position)) {
			is FilmUiModel.Cover -> (holder as FilmCoverViewHolder).bind(uiModel.filmEntity, presenter)
			is FilmUiModel.Title -> (holder as FilmTitleViewHolder).bind(uiModel.title)
			is FilmUiModel.Genre -> (holder as FilmGenreViewHolder).bind(uiModel.genre, presenter)
		}
	}

	companion object {

		private val COMPARATOR = object : DiffUtil.ItemCallback<FilmUiModel>() {

			override fun areItemsTheSame(
				oldItem: FilmUiModel,
				newItem: FilmUiModel
			): Boolean {
				return (oldItem is FilmUiModel.Title && newItem is FilmUiModel.Title && oldItem.title == newItem.title)
					|| (oldItem is FilmUiModel.Genre && newItem is FilmUiModel.Genre && oldItem.genre.contentEquals(newItem.genre))
					|| (oldItem is FilmUiModel.Cover && newItem is FilmUiModel.Cover && oldItem.filmEntity.id == newItem.filmEntity.id)
			}

			override fun areContentsTheSame(
				oldItem: FilmUiModel,
				newItem: FilmUiModel
			): Boolean {
				return oldItem == newItem
			}
		}

		val FilmListAdapter.manager: GridLayoutManager
			get() {

				val countColumn = 2
				val maxColumnWeight = 2
				val minColumnWeight = 1

				return GridLayoutManager(context, countColumn).apply {

					spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {

						override fun getSpanSize(position: Int): Int {
							return when (getItem(position)) {
								is FilmUiModel.Cover -> minColumnWeight
								is FilmUiModel.Title -> maxColumnWeight
								is FilmUiModel.Genre -> maxColumnWeight
								else                 -> throw UnsupportedOperationException("Unknown view")
							}
						}
					}
				}
			}
	}
}