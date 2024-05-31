package com.example.project.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project.R;

import java.util.ArrayList;

public class StoryInforFragment extends AppCompatActivity {

    EditText etxt_cmt;
    Button btn_cmt;
    ListView listView_cmt;
    ArrayList<String> arrList_cmt=null;
    ArrayAdapter<String> adapter_cmt=null;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.item_content);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.item), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView txt_noiDungChinh = findViewById(R.id.txt_noiDungChinh);

        String text = "Tinh không mênh mông, thâm thúy mà có vẻ hơi thần bí, sao lốm đốm đầy trời, rộng lớn vô biên. Hai thân ảnh xếp bằng ở hư không bên trong, thân thể cũng không nhúc nhích, cùng hoàn cảnh chung quanh liền thành một khối, phảng phất một mực tồn tại với thiên địa này.\n" +
                "\n" +
                "Lúc này trong đó một thân ảnh chậm rãi đứng người lên, trong lúc mơ hồ có thể nhìn ra đây là thân hình nữ tử, có thể là bởi vì ngồi thời gian có chút lâu, để nàng có chút khó chịu, liền đứng dậy hoạt động một chút thân thể. Nàng ước chừng gần ba mươi tuổi, khoác trên người một màu lam áo khoác ngoài, mặc dù không tính là tuyệt sắc, nhưng cũng không kém cạnh giai nhân.\n" +
                "\n";


        txt_noiDungChinh.setText(text);


        //1. Khởi tạo dữ liệu cho mảng arr (còn gọi là data source)
        final String[] arr ={"Chapter 1","Chapter 2","Chapter 3","Chapter 4","Chapter 5","Chapter 6","Chapter 7","Chapter 8"};
        //2. Lấy đối tượng Listview dựa vào id
        ListView lv=(ListView) findViewById(R.id.list_chapter);
        //3. Gán Data source vào ArrayAdapter
        ArrayAdapter<String> adapter=new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, arr);
        //4. Đưa Data source vào ListView
        lv.setAdapter(adapter);


        etxt_cmt = (EditText) findViewById(R.id.etxt_cmt);
        btn_cmt = (Button) findViewById(R.id.btn_cmt);
        listView_cmt = (ListView) findViewById(R.id.list_cmt);

        arrList_cmt = new ArrayList<String>();
        adapter_cmt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrList_cmt);
        listView_cmt.setAdapter(adapter_cmt);

        btn_cmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrList_cmt.add("Nguoi dung an danh : "+ etxt_cmt.getText());
                adapter_cmt.notifyDataSetChanged();
            }
        });
    }
}
