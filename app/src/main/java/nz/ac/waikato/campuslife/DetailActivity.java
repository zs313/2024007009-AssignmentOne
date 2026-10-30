package nz.ac.waikato.campuslife;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

/** A reusable information screen with a banner and a scrollable content area. */
public class DetailActivity extends AppCompatActivity {

    // The supplied image array contains names, so runtime resource lookup is intentional.
    @Override
    @SuppressLint("DiscouragedApi")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        setContentView(R.layout.activity_detail);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detail_root), (view, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars()
                    | WindowInsetsCompat.Type.displayCutout());
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // One index selects matching entries from all three supplied arrays.
        // DetailActivity is private to this app; MainActivity supplies only indices 0..3.
        int topicIndex = getIntent().getIntExtra(MainActivity.EXTRA_TOPIC_INDEX, 0);
        String[] titles = getResources().getStringArray(R.array.string_array_titles);
        String[] content = getResources().getStringArray(R.array.string_array_content);
        String[] imageNames = getResources().getStringArray(R.array.string_array_images);

        TextView categoryView = findViewById(R.id.detail_category);
        TextView titleView = findViewById(R.id.detail_title);
        TextView contentView = findViewById(R.id.detail_content);
        ImageView imageView = findViewById(R.id.detail_image);

        categoryView.setText(getIntent().getStringExtra(MainActivity.EXTRA_TOPIC_NAME));
        titleView.setText(titles[topicIndex].trim());
        contentView.setText(content[topicIndex]);
        int imageResource = getResources().getIdentifier(
                imageNames[topicIndex].trim(), "drawable", getPackageName());
        imageView.setImageResource(imageResource);
    }
}
