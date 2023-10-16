package br.com.igorbag.githubsearch.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.igorbag.githubsearch.R
import br.com.igorbag.githubsearch.domain.Repository

class RepositoryAdapter(
    private val repositories: List<Repository>,
    private val onShareClick: (String) -> Unit,
    private val onOpenBrowserClick: (String) -> Unit
) : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    inner class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btnShare: Button = itemView.findViewById(R.id.btnShare)
        val btnOpenBrowser: Button = itemView.findViewById(R.id.btnOpenBrowser)

        init {
            btnShare.setOnClickListener {
                val repositoryUrl = repositories[adapterPosition].htmlUrl
                onShareClick(repositoryUrl)
            }

            btnOpenBrowser.setOnClickListener {
                val repositoryUrl = repositories[adapterPosition].htmlUrl
                onOpenBrowserClick(repositoryUrl)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repository_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repository = repositories[position]

        // Bind dos dados do repositório
        holder.bind(repository)

        // Configurar o listener de clique no item
        holder.itemView.setOnClickListener {
            carItemLister(repository)
        }

        // Configurar o listener de clique no botão Share
        holder.btnShare.setOnClickListener {
            btnShareLister(repository)
        }
    }

    private fun btnShareLister(repository: Repository) {
        val repositoryUrl = repository.htmlUrl
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, repositoryUrl)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        val itemView = null
        itemView(shareIntent)
    }

    private fun carItemLister(repository: Repository) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val repositoryName: TextView = view.findViewById(R.id.repositoryName)
        val btnShare: ImageView = view.findViewById(R.id.btnShare)

        fun bind(repository: Repository) {
            repositoryName.text = repository.name
        }
    }
}

private operator fun Nothing?.invoke(shareIntent: Intent?) {

}
