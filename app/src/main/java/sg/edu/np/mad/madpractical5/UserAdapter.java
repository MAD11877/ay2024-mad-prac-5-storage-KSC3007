package sg.edu.np.mad.madpractical5;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    Context context;
    private List<User> myUserList;

    public UserAdapter(Context context, List<User> users) {
        this.context = context;
        myUserList = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_activity_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = myUserList.get(position);
        holder.name.setText(myUserList.get(position).getName());
        holder.description.setText(myUserList.get(position).getDescription());

        if (myUserList.get(position).getName().endsWith("7")) {
            holder.largeImage.setVisibility(View.VISIBLE);
        } else {
            holder.largeImage.setVisibility(View.GONE);
        }

        holder.smallImage.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
            builder.setTitle("Profile");
            builder.setMessage(myUserList.get(position).getName())
                    .setPositiveButton("View", (dialog, id) -> {
                        Intent intent = new Intent(v.getContext(), MainActivity.class);
                        intent.putExtra("USER", myUserList.get(position));
                        v.getContext().startActivity(intent);
                    })
                    .setNegativeButton("Close", null);
            builder.create().show();
        });
    }

    @Override
    public int getItemCount() {
        return myUserList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        ImageView smallImage, largeImage;
        TextView name, description;

        public UserViewHolder(@NonNull View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            description = itemView.findViewById(R.id.tvDescription);
            smallImage = itemView.findViewById(R.id.ivSmallImage);
            largeImage = itemView.findViewById(R.id.ivLargeImage);
        }
    }

    @Override
    public int getItemViewType(int position) {
        String name = myUserList.get(position).getName();
        return name.endsWith("7") ? 1 : 0;
    }
}
