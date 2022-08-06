package com.wujie.wanandroid.fragment.todo.addtodo;

import android.util.Log;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.wujie.wanandroid.BaseFragment;
import com.wujie.wanandroid.R;
import com.wujie.wanandroid.bean.TodoBean;
import com.wujie.wanandroid.databinding.FragmentAddTodoBinding;

/**
 * @Author：created by WuChen
 * @Time：2022/7/31 21:08
 * @Description：
 **/
public class AddTodoFragment extends BaseFragment<FragmentAddTodoBinding> {
    private static final String TAG = "AddTodoFragment";

    private AddTodoViewModel mAddTodoViewModel;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_add_todo;
    }

    @Override
    public void init(FragmentAddTodoBinding binding) {
        super.init(binding);

        mAddTodoViewModel = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(AddTodoViewModel.class);
        binding.setViewModel(mAddTodoViewModel);
        //通过以下逻辑来保证MutableLiveData变化时来更新UI
        //该方法中最终会调用到observe()方法
        binding.setLifecycleOwner(this);

        mAddTodoViewModel.getAdddata().observe(this, new Observer<TodoBean>() {
            @Override
            public void onChanged(TodoBean todoBean) {
                Log.d(TAG, "onChanged: todoBean=" + todoBean);
            }
        });

        binding.radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_study:
                    mAddTodoViewModel.mType.setValue(1);
                    break;
                case R.id.rb_work:
                    mAddTodoViewModel.mType.setValue(2);
                    break;
                case R.id.rb_live:
                    mAddTodoViewModel.mType.setValue(3);
                    break;
                case R.id.rb_other:
                    mAddTodoViewModel.mType.setValue(4);
                    break;
                default:
                    break;
            }
        });
    }
}
