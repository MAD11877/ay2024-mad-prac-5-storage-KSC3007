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
    ArrayList<User> myUserList;
    UserDBHandler dbHandler;

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

        dbHandler = new UserDBHandler(this, null, null, 1);

        myUserList = new ArrayList<>(); // Initialize myUserList
        // Generate and populate database if empty
        if (dbHandler.getUsers().isEmpty()) {
            generateUserData();
            populateDatabase();
        }

        myUserList = dbHandler.getUsers();  // Retrieve users from the database

        RecyclerView recyclerView = findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        UserAdapter userAdapter = new UserAdapter(this, myUserList);  // Pass context and user list
        recyclerView.setAdapter(userAdapter);
    }

    private void generateUserData() {
        for (int i = 0; i < 20; i++) {
            int nameRandomNo = new Random().nextInt(999999999);
            int descRandomNo = new Random().nextInt(999999999);
            boolean followed = new Random().nextBoolean();
            myUserList.add(new User("Name" + nameRandomNo, "Description " + descRandomNo, i + 1, followed));
        }
    }

    private void populateDatabase() {
        for (User user : myUserList) {
            dbHandler.addUser(user);
        }
    }
}
