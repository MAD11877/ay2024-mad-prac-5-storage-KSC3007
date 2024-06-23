package sg.edu.np.mad.madpractical5;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {
    ImageView smallImage, largeImage;
    TextView name, description;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.tvName);
        description = itemView.findViewById(R.id.tvDescription);
        smallImage = itemView.findViewById(R.id.ivSmallImage);
        largeImage = itemView.findViewById(R.id.ivLargeImage);
    }
}
