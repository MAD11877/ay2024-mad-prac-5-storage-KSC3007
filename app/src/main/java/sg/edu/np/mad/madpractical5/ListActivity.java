package sg.edu.np.mad.madpractical5;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView1);

        ArrayList<sg.edu.np.mad.madpractical5.User> myUserList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {  // Fixed the loop to iterate correctly
            int nameRandomNo = new Random().nextInt(999999999);
            int descRandomNo = new Random().nextInt(999999999);
            boolean followed = new Random().nextBoolean();
            myUserList.add(new sg.edu.np.mad.madpractical5.User("Name"+String.valueOf(nameRandomNo), "Description "+String.valueOf(descRandomNo), i+1, followed));
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        UserAdapter userAdapter = new UserAdapter(this, myUserList);  // Pass context here
        recyclerView.setAdapter(userAdapter);

    }
}
