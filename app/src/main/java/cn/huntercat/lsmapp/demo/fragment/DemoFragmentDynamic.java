package cn.huntercat.lsmapp.demo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import cn.huntercat.lsmapp.R;

public class DemoFragmentDynamic extends Fragment {

    public static DemoFragmentDynamic newInstance() {
        return new DemoFragmentDynamic();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dynamic, container, false);
    }
}
