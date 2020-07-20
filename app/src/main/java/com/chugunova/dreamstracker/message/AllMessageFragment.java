package com.chugunova.dreamstracker.message;

import android.os.Bundle;
import android.os.Handler;
import android.view.*;
import android.widget.*;

import com.chugunova.dreamstracker.API.ConfigRetrofit;
import com.chugunova.dreamstracker.MainActivity;
import com.chugunova.dreamstracker.R;
import com.chugunova.dreamstracker.login.LoginFragment;
import com.chugunova.dreamstracker.model.Message;

import java.util.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.ResponseBody;
import retrofit2.*;

import static com.chugunova.dreamstracker.login.LoginFragment.ARG_TOKEN;
import static com.chugunova.dreamstracker.login.LoginFragment.ARG_USERNAME;

public class AllMessageFragment extends Fragment {

    private View view;
    private RecyclerView listMessages;
    private DataAdapterMessage adapterMessages;
    private List<Message> post;
    private List<Message> postNew;
    Toast toast;
    private String token;
    private String username;
    private ImageButton newMessage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        loadDataFromArgument();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MainActivity.needShowAlertDialog = false;

        view = inflater.inflate(R.layout.fragment_chat, container, false);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        listMessages = view.findViewById(R.id.listMessages);

        toast = Toast.makeText(getActivity(), getString(R.string.no_response_from_server), Toast.LENGTH_LONG);

        ImageButton message = view.findViewById(R.id.button_send);

        newMessage = view.findViewById(R.id.new_message);

        post = new ArrayList<>();
        postNew = new ArrayList<>();

        adapterMessages = new DataAdapterMessage(getContext(), post, username);

        ConfigRetrofit.getInstance()
                .getAllMessage(token)
                .enqueue(new Callback<List<Message>>() {
                    @Override
                    public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {

                        if (response.isSuccessful()) {
                            post = response.body();

                            adapterMessages.setMessagesList(post);
                            listMessages.setAdapter(adapterMessages);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Message>> call, Throwable t) {
                        adapterMessages.setMessagesList(post);
                        listMessages.setAdapter(adapterMessages);
                        Toast toastNew = Toast.makeText(getActivity(), getString(R.string.no_connection_to_server), Toast.LENGTH_LONG);
                        toastNew.show();
                        t.printStackTrace();
                    }
                });

        periodicUpdate.run();

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText textMessage = view.findViewById(R.id.messageTextContent);
                String textMes = textMessage.getText().toString();

                if (textMes.isEmpty()) {
                    textMessage.setHint("Пустое сообщение");
                } else {
                    Message message1 = new Message();

                    message1.setMesText(textMes);

                    ConfigRetrofit.getInstance()
                            .sendMessage(token, message1)
                            .enqueue(new Callback<ResponseBody>() {

                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Toast toast = Toast.makeText(requireContext(), getString(R.string.no_connection_to_server), Toast.LENGTH_LONG);
                                    toast.show();
                                    t.printStackTrace();
                                }
                            });

                    textMessage.setText("");
                    textMessage.setHint("Введите текст");
                }
            }
        });

        newMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listMessages.smoothScrollToPosition(adapterMessages.getItemCount() - 1);
            }
        });

        listMessages.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager)listMessages.getLayoutManager();
                int visibleItemCount = 0;
                int totalItemCount = 0;
                int firstVisibleItems = 0;
                if (layoutManager != null) {
                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    firstVisibleItems = layoutManager.findFirstVisibleItemPosition();
                }
                if ((visibleItemCount + firstVisibleItems) >= totalItemCount) {
                    newMessage.setVisibility(View.GONE);
                    newMessage.setImageResource(R.mipmap.down);
                } else {
                    newMessage.setVisibility(View.VISIBLE);
                }
            }
        });

        setHasOptionsMenu(true);
        return view;
    }

    public void updateChat() {
        ConfigRetrofit.getInstance()
                .getAllMessage(token)
                .enqueue(new Callback<List<Message>>() {
                    @Override
                    public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                        if (response.isSuccessful()) {
                            postNew = response.body();

                            if (post.size() == 0 && postNew != null) {
                                post.addAll(postNew);
                                adapterMessages.notifyDataSetChanged();
                            }

                            if (postNew.size() > post.size()) {
                                List<Message> buf = new ArrayList<>();
                                int newMes = postNew.size() - post.size();
                                for (int i = 0; i < newMes; i++) {
                                    buf.add(postNew.get(postNew.size() - 1 - i));
                                    Collections.reverse(buf);
                                }
                                post.addAll(buf);
                                adapterMessages.notifyDataSetChanged();

                                LinearLayoutManager layoutManager = (LinearLayoutManager)listMessages.getLayoutManager();

                                int totalItemCount = layoutManager.getItemCount();
                                int lastVisible = layoutManager.findLastVisibleItemPosition();
                                boolean endHasBeenReached = lastVisible + 2 >= totalItemCount;

                                if (totalItemCount - lastVisible > 2) {
                                    newMessage.setVisibility(View.VISIBLE);
                                    newMessage.setImageResource(R.drawable.new_mes);
                                }

                                if (totalItemCount > 0 && endHasBeenReached) {
                                    listMessages.smoothScrollToPosition(post.size());
                                }
                            }
                        } else {
                            periodicUpdate = null;

                            Toast toast = Toast.makeText(getActivity(), "JWT token is expired or invalid", Toast.LENGTH_LONG);
                            toast.show();

                            AppCompatActivity activity = (AppCompatActivity)view.getContext();

                            LoginFragment loginFragment = new LoginFragment();

                            FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction().replace(R.id.main, loginFragment);
                            fragmentTransaction.commit();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Message>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    Handler handler = new Handler();
    private Runnable periodicUpdate = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(periodicUpdate, 1000);
            updateChat();
        }
    };

    @Override
    public void onDestroy() {
        periodicUpdate = null;
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        super.onDestroy();
    }


    private void loadDataFromArgument() {
        assert getArguments() != null;
        token = getArguments().getString(ARG_TOKEN);
        username = getArguments().getString(ARG_USERNAME);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.action_send).setVisible(false);
        menu.findItem(R.id.smile).setVisible(false);
        menu.findItem(R.id.action_registration).setVisible(false);
        menu.findItem(R.id.delete).setVisible(false);
    }
}
