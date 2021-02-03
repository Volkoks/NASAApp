package com.example.nasaapp.ui.test_recycler_view.recycler

import android.graphics.Color
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.nasaapp.R
import com.example.nasaapp.common.Constant.FLAG_APOD
import com.example.nasaapp.common.Constant.FLAG_HEADING
import com.example.nasaapp.common.Constant.FLAG_TECHPORT
import com.example.nasaapp.data.recycler.DataForRecyclerView
import com.example.nasaapp.ui.recycler.ItemTouchHelperAdapter
import com.example.nasaapp.ui.recycler.ItemTouchHelperViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_apod.view.*
import kotlinx.android.synthetic.main.item_heading.view.*
import kotlinx.android.synthetic.main.item_techport.view.*

class TestRVAdapter(
    private val data: MutableList<DataForRecyclerView>
) : RecyclerView.Adapter<BaseViewHolder>(), ItemTouchHelperAdapter {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            FLAG_HEADING -> HeadingViewHolder(
                inflater.inflate(
                    R.layout.item_heading,
                    parent,
                    false
                )
            )
            FLAG_APOD -> APODViewHolder(inflater.inflate(R.layout.item_apod, parent, false))
            FLAG_TECHPORT -> TechportViewHolder(
                inflater.inflate(R.layout.item_techport, parent, false)
            )

            else -> HeadingViewHolder(inflater.inflate(R.layout.item_heading, parent, false))
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    override fun getItemViewType(position: Int): Int {
        return when (data[position].viewType) {
            FLAG_APOD -> FLAG_APOD
            FLAG_TECHPORT -> FLAG_TECHPORT
            else -> 0
        }
    }

    fun addItem(addedData: DataForRecyclerView) {
        data.add(addedData)
        notifyItemInserted(itemCount - 1)
    }


    inner class APODViewHolder(override val containerView: View) : BaseViewHolder(containerView),
        LayoutContainer, ItemTouchHelperViewHolder {

        override fun bind(data: DataForRecyclerView) {
            itemView.tv_title_item_apod.text = data.title
            itemView.image_picture_of_the_day_rv.load(data.url)
        }

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }
    }

    inner class TechportViewHolder(override val containerView: View) :
        BaseViewHolder(containerView), LayoutContainer, ItemTouchHelperViewHolder {

        override fun bind(data: DataForRecyclerView) {
            itemView.tv_title_item_techport.text = data.title
            itemView.tv_start_data_item_techport.text = "START DATE: ${data.startData}"
            itemView.tv_end_data_item_techport.text = "END DATE: ${data.endData}"
        }

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemClear() {
            val typedValue = TypedValue()
            containerView.context.theme.resolveAttribute(R.attr.colorSurface, typedValue, true)

            itemView.setBackgroundColor(
                ContextCompat.getColor(
                    containerView.context,
                    typedValue.resourceId
                )
            )
        }
    }

    inner class HeadingViewHolder(override val containerView: View) : BaseViewHolder(containerView),
        LayoutContainer {
        override fun bind(data: DataForRecyclerView) {
            itemView.tv_heading_rv.text = data.title

        }


    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        data.removeAt(fromPosition).apply {
            data.add(
                if (toPosition > fromPosition) toPosition - 1
                else toPosition, this
            )
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }
}