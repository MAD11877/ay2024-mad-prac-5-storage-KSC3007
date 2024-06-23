package sg.edu.np.mad.madpractical5;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    Context context;
    private List<User> myUserList;

    public UserAdapter(Context context, List<User> users) {
        this.context = context;
        myUserList = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_activity_list, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = myUserList.get(position);
        holder.name.setText(user.getName());
        holder.description.setText(user.getDescription());

        if (user.getName().endsWith("7")) {
            holder.largeImage.setVisibility(View.VISIBLE);
        } else {
            holder.largeImage.setVisibility(View.GONE);
        }

        holder.smallImage.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
            builder.setTitle("Profile");
            builder.setMessage(user.getName())
                    .setPositiveButton("View", (dialog, id) -> {
                        Intent intent = new Intent(v.getContext(), MainActivity.class);
                        intent.putExtra("USER", user);
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
}
