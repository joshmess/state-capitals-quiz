package edu.uga.cs.state_capitals_quiz;
import java.util.Random;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {


    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    ActionBar mActionBar;
    private AppData appData = null;

    public final static Integer[] imageIds = new Integer[]{
            R.drawable.alabama, R.drawable.alaska, R.drawable.arizona,
            R.drawable.arkansas, R.drawable.california, R.drawable.colorado,
            R.drawable.connecticut, R.drawable.delaware, R.drawable.florida,
            R.drawable.georgia, R.drawable.hawaii, R.drawable.idaho,
            R.drawable.illinois, R.drawable.indiana, R.drawable.iowa,
            R.drawable.kansas, R.drawable.kentucky, R.drawable.louisiana,
            R.drawable.maine, R.drawable.maryland, R.drawable.massachusetts,
            R.drawable.michigan, R.drawable.minnesota, R.drawable.mississippi,
            R.drawable.missouri, R.drawable.montana, R.drawable.nebraska,
            R.drawable.nevada, R.drawable.new_hampshire, R.drawable.new_jersey,
            R.drawable.new_mexico, R.drawable.new_york, R.drawable.north_carolina,
            R.drawable.north_dakota, R.drawable.ohio, R.drawable.oklahoma,
            R.drawable.oregon, R.drawable.pennsylvania, R.drawable.rhode_island,
            R.drawable.south_carolina, R.drawable.south_dakota, R.drawable.tennessee,
            R.drawable.texas, R.drawable.utah, R.drawable.vermont,
            R.drawable.virginia, R.drawable.washington, R.drawable.west_virginia,
            R.drawable.wisconsin, R.drawable.wyoming
    };

    public final static String[] imageDescriptions = new String[]{
            "Alabama", "Alaska", "Arizona",
            "Arkansas", "California", "Colorado",
            "Connecticut", "Delaware", "Florida",
            "Georgia", "Hawaii", "Idaho",
            "Illinois", "Indiana", "Iowa",
            "Kansas", "Kentucky", "Louisiana",
            "Maine", "Maryland", "Massachusetts",
            "Michigan", "Minnesota", "Mississippi",
            "Missouri", "Montana", "Nebraska",
            "Nevada", "New Hampshire", "New Jersey",
            "New Mexico", "New York", "North Carolina",
            "North Dakota", "Ohio", "Oklahoma",
            "Oregon", "Pennsylvania", "Rhode Island",
            "South Carolina", "South Dakota", "Tennessee",
            "Texas", "Utah", "Vermont",
            "Virgina", "Washington", "West Virgina",
            "Wisconsin", "Wyoming"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_layout);

        mActionBar = getSupportActionBar();
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), 6);
        mActionBar.setTitle(mSectionsPagerAdapter.getPageTitle(0));
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        appData = new AppData(this);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mActionBar.setTitle(mSectionsPagerAdapter.getPageTitle(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    public void loadView(ImageView imageView, int resId, TextView textView, String description) {
        imageView.setImageResource(resId);
        textView.setText(description);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private final int mSize;
        private ArrayList<Integer> questions = getQuestions();
        int counter = -1;

        public SectionsPagerAdapter(FragmentManager fm, int size) {
            super(fm);
            this.mSize = size;
        }

        @Override
        public Fragment getItem(int position) {
            counter ++;
            System.out.println("COUNTER: " + counter);
            System.out.println("QUESTION INDEX: " + questions.get(counter));

            return PlaceholderFragment.newInstance(position + 1, questions.get(counter));
        }

        @Override
        public int getCount() {
            return mSize;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            int imageNum = position + 1;
            if(imageNum == 7){
                return String.valueOf("Quiz Results");
            }
            return String.valueOf("Question " + imageNum);
        }
    }

    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final String ARG_Q = "quest";

        private int mImageNum;
        private int mIndex;
        private TextView mTextView;
        private ImageView mImageView;

        public static PlaceholderFragment newInstance(int sectionNumber, int question) {

            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putInt(ARG_Q,question);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                mImageNum = getArguments().getInt(ARG_SECTION_NUMBER);
                mIndex = getArguments().getInt(ARG_Q);

            } else {
                mImageNum = -1;
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.quiz_view_pager_layout, container, false);
            mTextView = (TextView) rootView.findViewById(R.id.section_label);
            mImageView = (ImageView) rootView.findViewById(R.id.image_view);
            return rootView;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            if (QuizActivity.class.isInstance(getActivity())) {

                System.out.println("NUM: " + mImageNum);
                final int resId = QuizActivity.imageIds[mIndex];
                final String description = imageDescriptions[mIndex];
                ((QuizActivity) getActivity()).loadView(mImageView, resId, mTextView, description);
            }
        }
    }

    public ArrayList<Integer> getQuestions(){

        ArrayList<Integer> toReturn = new ArrayList<Integer>();
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(50) + 1;
        toReturn.add(randomInt);

        while(toReturn.size() < 6){
            randomInt = randomGenerator.nextInt(50) + 1;
            if(!toReturn.contains(randomInt))
                toReturn.add(randomInt);
        }
        return toReturn;
    }

}
