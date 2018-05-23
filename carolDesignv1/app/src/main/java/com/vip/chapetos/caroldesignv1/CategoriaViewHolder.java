package com.vip.chapetos.caroldesignv1;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class CategoriaViewHolder extends RecyclerView.ViewHolder {

TextView title;


public CategoriaViewHolder(View item){
    super(item);

    title = (TextView) item.findViewById(R.id.txtTitle);
}

}
