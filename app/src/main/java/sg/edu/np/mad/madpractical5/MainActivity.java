package sg.edu.np.mad.madpractical5;

//import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private DatabaseHandler dbHandler;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHandler = new DatabaseHandler(this, null, null, 1);

        TextView tvName = findViewById(R.id.tvName);
        TextView tvDescription = findViewById(R.id.tvDescription);
        Button btnFollow = findViewById(R.id.btnFollow);

        // Retrieve the User object passed from ListActivity
        User user = (User) getIntent().getSerializableExtra("USER");

        if (user != null) {
            tvName.setText(user.getName());
            tvDescription.setText(user.getDescription());
            btnFollow.setText(user.isFollowed() ? "Unfollow" : "Follow");
            btnFollow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    user.setFollowed(!user.isFollowed());
                    dbHandler.updateUser(user);
                    btnFollow.setText(user.isFollowed() ? "Unfollow" : "Follow");
                    Toast.makeText(MainActivity.this, user.isFollowed() ? "Followed" : "Unfollowed", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // Handle the case when user is null
            Toast.makeText(this, "User data not found", Toast.LENGTH_LONG).show();
        }
    }
}