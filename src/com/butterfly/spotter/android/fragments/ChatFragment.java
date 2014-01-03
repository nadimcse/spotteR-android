package com.butterfly.spotter.android.fragments;


import java.util.List;
import java.util.concurrent.BlockingQueue;

import com.butterfly.spotter.android.R;
import com.butterfly.spotter.android.activities.LoginActivity;
import com.butterfly.spotter.android.database.DatabaseHelper;
import com.butterfly.spotter.android.listener.SwitchFragmentListener;
import com.butterfly.spotter.android.model.Message;
import com.butterfly.spotter.android.singleton.SingletonAccess;
import com.butterfly.spotter.android.util.Utility;
import com.turbomanage.httpclient.AsyncCallback;
import com.turbomanage.httpclient.HttpResponse;
import com.turbomanage.httpclient.ParameterMap;
import com.turbomanage.httpclient.android.AndroidHttpClient;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.text.method.TextKeyListener;

/** 
*
* @author Nadim
* @since  Dec 2, 2013
*
*/


public class ChatFragment extends Fragment implements FragmentHandler {

	private DatabaseHelper databaseHelper;
	private BlockingQueue<String> queue;
    private SwitchFragmentListener fragmentListener;
	private Context context;
	
	private Button chatBtn;
	private Button mapBtn;
	private EditText myEditText;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        context = this.getActivity();
        Log.d("itemloadListener11...", "" + System.currentTimeMillis());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.chat_fragment, container, false);
        handleChatFunctionality(view);
        toMapEditorToggler(view);
    	return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        
        //is it the best place to refer DatabaseHelper????
        //databaseHelper = DatabaseHelper.getInstance(activity);
        databaseHelper = SingletonAccess.INSTANCE.getSingleton().getDatabaseHelper();
        try {
            fragmentListener = (SwitchFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement SwitchFragmentListener");
        }
        renderViewBasedOnEvent();
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }
    
	public String fragmentName() {
		return "chat";
	}
	
	public void renderViewBasedOnEvent() {
		Toast.makeText(this.getActivity(), 
             "Loading!!! ? " ,  Toast.LENGTH_LONG).show();
    
		TextView textView = (TextView) this.getActivity().findViewById(R.id.chatText);
		//textView.setText(textView.getText().toString() +"\n" + "hello1");
		List<Message> messageList = databaseHelper.getMessageList();	
		for (Message message : messageList) {
			textView.setText(textView.getText().toString() 
					+"\n" + message.getMessage() + "-" + message.getCreated());
		}
	}
	
	private void handleChatFunctionality(View view) {
        chatBtn = (Button) view.findViewById(R.id.chatButton);
    	chatBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
		        Toast.makeText(context, 
		                "Chat Button clicked!!!", Toast.LENGTH_LONG).show();
		        final EditText chatMsg = (EditText) ((Activity) context).findViewById(R.id.chatEditText);
		        Log.d("chat msg........", chatMsg.toString());
		        String groupId = LoginActivity.loginId + "@" + CallListFragment.currentPeer;
		        AndroidHttpClient httpClient = new AndroidHttpClient(Utility.SERVERPATH);
		        httpClient.setMaxRetries(2);
		        ParameterMap params = httpClient.newParams()
		                .add("groupId", groupId)
		                .add("message", chatMsg.getText().toString())
		                .add("authKey", "HRWSTDKJBEUEDF#UINUIT");
		        httpClient.post("/spotter/message", params, new AsyncCallback() {
		           
		            @Override
		            public void onError(Exception e) {
		                e.printStackTrace();
		            }
					@Override
					public void onComplete(HttpResponse httpResponse) {
				        databaseHelper.addMessage(new Message(CallListFragment.currentPeer, chatMsg.toString()));
				        TextView chatText = (TextView) ((Activity) context).findViewById(R.id.chatText);
						chatText.setText(chatText.getText().toString() + "\n" + chatMsg.getText().toString());
					}
		        });
				
			}
		});
	}
	
	private void toMapEditorToggler(View view) {
		   mapBtn = (Button) view.findViewById(R.id.mapFragmentBtn);
	    	mapBtn.setOnClickListener(new OnClickListener() {
	    		@Override
				public void onClick(View v) {
	    			fragmentListener.switchFragment(MapFragment.class);
	    		}
	    	});
	}
}
