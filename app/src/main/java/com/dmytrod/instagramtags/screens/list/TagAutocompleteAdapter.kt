package com.dmytrod.instagramtags.screens.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.dmytrod.instagramtags.data.model.Tag


/**
 * Created by Dmytro Denysenko on 23.01.18.
 */
class TagAutocompleteAdapter(context: Context, val tags: MutableList<Tag>, val findTags: (String) -> Unit)
    : ArrayAdapter<Tag>(context, android.R.layout.simple_dropdown_item_1line, tags), Filterable {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView
                ?: LayoutInflater.from(context).inflate(android.R.layout.simple_dropdown_item_1line, parent, false)
        val textView = view.findViewById(android.R.id.text1) as TextView?
        textView?.text = getItem(position)?.name
        return view
    }

    override fun getFilter() = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            if (constraint != null) {
                //TODO implement some kind of debouncing
                findTags(constraint.toString())
            }
            return FilterResults()
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if (tags.size > 0) {
                notifyDataSetChanged()
            } else {
                notifyDataSetInvalidated()
            }
        }
    }
}