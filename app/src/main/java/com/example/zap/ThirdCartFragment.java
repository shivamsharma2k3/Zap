package com.example.zap;

import static com.example.zap.SecondCartFragment.ORDER_KEY;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.lang.reflect.Type;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;

public class ThirdCartFragment extends Fragment implements PaymentResultListener{
    private static final String TAG = "ThirdCartFragment";

    private Button btnBack, btnCheckout;
    private TextView txtItems, txtAddress, txtPhoneNumber, txtTotalPrice;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart_third, container, false);

        initViews(view);

        System.out.println(getActivity());

        Checkout.preload(getActivity().getApplicationContext());
        Bundle bundle = getArguments();
        if (null != bundle) {
            final String jsonOrder = bundle.getString(ORDER_KEY);
            if (null != jsonOrder) {
                Gson gson = new Gson();
                Type type = new TypeToken<Order>() {
                }.getType();
                final Order order = gson.fromJson(jsonOrder, type);
                if (null != order) {
                    String items = "";
                    for (Items i : order.getItems()) {
                        items += "\n\t" + i.getName();
                    }

                    txtItems.setText(items);
                    txtAddress.setText(order.getAddress());
                    txtPhoneNumber.setText(order.getPhoneNumber());
                    txtTotalPrice.setText(String.valueOf(order.getTotalPrice()));


                    btnBack.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle backBundle = new Bundle();
                            backBundle.putString(ORDER_KEY, jsonOrder);
                            SecondCartFragment secondCartFragment = new SecondCartFragment();
                            secondCartFragment.setArguments(backBundle);
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.container, secondCartFragment);
                            transaction.commit();
                        }
                    });

                    btnCheckout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            makePayment();

                            order.setSuccess(true);


//                            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
//                                    .setLevel(HttpLoggingInterceptor.Level.BODY);
//
//                            OkHttpClient client = new OkHttpClient.Builder()
//                                    .addInterceptor(interceptor)
//                                    .build();
//
//                            Retrofit retrofit = new Retrofit.Builder()
//                                    .baseUrl("https://jsonplaceholder.typicode.com/")
//                                    .addConverterFactory(GsonConverterFactory.create())
//                                    .client(client)
//                                    .build();

//                            OrderEndPoint endPoint = retrofit.create(OrderEndPoint.class);
//                            Call<Order> call = endPoint.newOrder(order);
//                            call.enqueue(new Callback<Order>() {
//                                @Override
//                                public void onResponse(Call<Order> call, Response<Order> response) {
//                                    Log.d(TAG, "onResponse: code: " + response.code());
//                                    if (response.isSuccessful()) {
//                                        Bundle resultBundle = new Bundle();
//                                        resultBundle.putString(ORDER_KEY, gson.toJson(response.body()));
//                                        PaymentResultFragment paymentResultFragment = new PaymentResultFragment();
//                                        paymentResultFragment.setArguments(resultBundle);
//                                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                                        transaction.replace(R.id.container, paymentResultFragment);
//                                        transaction.commit();
//                                    }else {
//                                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                                        transaction.replace(R.id.container, new PaymentResultFragment());
//                                        transaction.commit();
//                                    }
//                                }

//                                @Override
//                                public void onFailure(Call<Order> call, Throwable t) {
//                                    t.printStackTrace();
//                                }
//                            });
                        }
                    });
                }
            }
        }

        return view;
    }

    private void makePayment() {
            Checkout checkout = new Checkout();
            checkout.setKeyID("rzp_test_djjuT3UB9JyD85");
            /**
             * Instantiate Checkout
             */

            /**
             * Set your logo here
             */
            checkout.setImage(R.drawable.logo);

            /**
             * Reference to current activity
             */
            final Activity activity = getActivity();

            /**
             * Pass your payment options to the Razorpay Checkout as a JSONObject
             */
            try {
                JSONObject options = new JSONObject();

                options.put("name", "ZAP");
                options.put("description", "Reference No. #123456");
                options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg");
//                options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
                options.put("theme.color", "#3399cc");
                options.put("currency", "INR");
                options.put("amount", "50000");//pass amount in currency subunits
                options.put("prefill.email", "sharmashivam2k3@gmail.com");
                options.put("prefill.contact","8100672344");
                JSONObject retryObj = new JSONObject();
                retryObj.put("enabled", true);
                retryObj.put("max_count", 4);
                options.put("retry", retryObj);

                checkout.open(activity, options);

            } catch(Exception e) {
                Log.e("TAG", "Error in starting Razorpay Checkout", e);
            }


    }

    @Override
    public void onPaymentSuccess(String s) {
    }

    @Override
    public void onPaymentError(int i, String s) {
    }

    private void initViews(View view) {
        txtAddress = view.findViewById(R.id.txtAddress);
        txtPhoneNumber = view.findViewById(R.id.txtPhoneNumber);
        txtItems = view.findViewById(R.id.txtItems);
        txtTotalPrice = view.findViewById(R.id.txtPrice);
        btnBack = view.findViewById(R.id.btnBack);
        btnCheckout = view.findViewById(R.id.btnCheckout);
    }


}