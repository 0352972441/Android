package com.example.cocoshop.screen.audioscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.cocoshop.adapter.ViewPageAdapter;
import com.example.cocoshop.models.audiomodels.Category;
import com.example.cocoshop.R;
import com.example.cocoshop.fragment.AudioCategoryFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainAudioActivity extends AppCompatActivity {
    /*private RecyclerView cardItemAudioRecycler;
    private RecyclerView itemCategoryRecycler;
    private ItemCategoryAudioAdapter categoryAdapter;
    private CardItemAudioPopularAdapter cardItemAdapter;
    private static int previousPosition = 0;
    private View viewItemCategory;
    private ArrayList<Audio> data;
    private int afterChangeState =0;
    private int beforeChangeState = 0;
    private boolean isChange = false;
    RelativeLayout background;*/

    private ViewPager2 viewPager2;
    private TabLayout tabCategory;
    List<Map<String,Object>> page;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_audio);
        tabCategory = findViewById(R.id.tab_bar_layout);
        viewPager2 = findViewById(R.id.viewPage);
        page = tab();
        ViewPageAdapter adapter = new ViewPageAdapter(this,page);
        viewPager2.setAdapter(adapter);
        new TabLayoutMediator(tabCategory, viewPager2, new TabLayoutMediator.OnConfigureTabCallback() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText((String)page.get(position).get("name"));
                tab.setIcon((int) page.get(position).get("icon"));
            }
        }).attach();
    }

    private List<Map<String,Object>> tab(){
        List<Map<String,Object>> data = new ArrayList<>();
        int[] icon = {R.drawable.ic_format_list_bulleted_black_24dp,R.drawable.ic_account_balance_black_24dp,
                R.drawable.ic_airplanemode_active_black_24dp,R.drawable.ic_build_black_24dp,R.drawable.ic_directions_run_black_24dp,
        R.drawable.ic_music_note_black_24dp,R.drawable.ic_laptop_mac_black_24dp};
        for(int i=0; i< Category.values().length; i++){
            Map<String,Object> item = new HashMap<>();
            String nameCategory = Category.values()[i].toString();
            AudioCategoryFragment categoryFragment = new AudioCategoryFragment(nameCategory);
            item.put("icon",icon[i]);
            item.put("name",nameCategory);
            item.put("fragment",categoryFragment);
            data.add(item);
        }
        return data;
    }

    /*private void displayAudioByGenre(){
        categoryAdapter.setCardItemCategoryListener(new Listener() {
            @Override
            public void listener(int position) {
                if(position == previousPosition){
                    if(itemCategoryRecycler.findViewHolderForAdapterPosition(position) != null){
                        viewItemCategory = itemCategoryRecycler.findViewHolderForAdapterPosition(position).itemView;
                        background = (RelativeLayout)viewItemCategory.findViewById(R.id.background_item_card_category);
                        background.setBackgroundResource(R.color.colorPrimary);
                        previousPosition = position;
                    }
                }else{
                    if(itemCategoryRecycler.findViewHolderForAdapterPosition(previousPosition) != null){
                        viewItemCategory = itemCategoryRecycler.findViewHolderForAdapterPosition(previousPosition).itemView;
                        background = (RelativeLayout)viewItemCategory.findViewById(R.id.background_item_card_category);
                        background.setBackgroundResource(R.color.white);
                    }
                    viewItemCategory = itemCategoryRecycler.findViewHolderForAdapterPosition(position).itemView;
                    background = (RelativeLayout)viewItemCategory.findViewById(R.id.background_item_card_category);
                    background.setBackgroundResource(R.color.colorPrimary);
                    previousPosition = position;
                }
                if(Category.values()[position] != Category.ALL){
                    for(Audio i : Sound.listAllData){
                        if(i.getCategory().equals(Category.values()[position])){
                            data.add(i);
                        }
                    }
                    cardItemAdapter.notifyDataSetChanged();
                }else{
                    for(int i=0; i< Sound.listAllData.size(); i++){
                        data.add(Sound.listAllData.get(i));
                    }
                    cardItemAdapter.notifyDataSetChanged();
                }
            }
        });
    }*/

    /*private void onClickPlayAudio(){
        cardItemAdapter.setPlayAudioListener(new Listener() {
            @Override
            public void listener(int position) {
                Intent intent = new Intent(MainAudioActivity.this,PlayAudioActivity.class);
                Audio audio = data.get(position);
                intent.putExtra(PlayAudioActivity.KEYAUDIO, BundleData.sendData(audio));
                startActivity(intent);
            }
        });
    }*/
}
