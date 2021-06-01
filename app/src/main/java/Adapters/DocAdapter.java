package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lawnics.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import Models.Documents;

public class DocAdapter extends RecyclerView.Adapter<DocAdapter.ViewHolder> {

   private ArrayList<Documents> docList;
   Context context;

    public DocAdapter(ArrayList<Documents> docList, Context context) {
        this.docList = docList;
        this.context = context;
    }


    @NonNull
    @Override
    public DocAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_sample_doc, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mDocImage;
        TextView mDocTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mDocImage = (ImageView) itemView.findViewById(R.id.sample_docImage);
            mDocTitle = (TextView) itemView.findViewById(R.id.sample_docTitle);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull DocAdapter.ViewHolder holder, int position) {

        Documents doc = docList.get(position);

        holder.mDocTitle.setText(doc.getDocName());
        Picasso.get().load(doc.getDocImage()).into(holder.mDocImage);

    }

    @Override
    public int getItemCount() {
        return docList.size();
    }
}
